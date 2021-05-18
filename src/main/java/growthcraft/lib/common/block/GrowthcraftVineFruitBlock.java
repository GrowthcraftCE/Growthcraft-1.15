package growthcraft.lib.common.block;

import growthcraft.core.Growthcraft;
import growthcraft.core.init.config.GrowthcraftConfig;
import growthcraft.lib.utils.BlockStateUtils;
import growthcraft.lib.utils.BushUtils;
import growthcraft.grapes.init.config.GrowthcraftGrapesConfig;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

import java.util.Map;
import java.util.Random;

/**
 * A BushBlock that grows from a GrowthcraftVineBlock and will grow along a RopeBlock.
 *
 * @author Alatyami
 * @since 5.0.0
 */
@SuppressWarnings("java:S1874")
public class GrowthcraftVineFruitBlock extends BushBlock implements IGrowable {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;

    private long startTime = 0;
    private long configModifier = (long) (GrowthcraftConfig.getPointsToGrow() / GrowthcraftConfig.getVineGrowModifier());
    private int averageRandomTick = GrowthcraftConfig.getAverageRandomTick();

    protected static VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D)};

    private Item seedsItem;

    private Item vineFruitItem;
    private int minFruit;
    private int maxFruit;

    public GrowthcraftVineFruitBlock() {
        this(getInitProperties());
    }

    public void setupVineFruitItem(Item vineFruitItem, int min, int max ) {
        this.vineFruitItem = vineFruitItem;
        this.minFruit = min;
        this.maxFruit = max;
    }

    public GrowthcraftVineFruitBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(AGE, 0));
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.PLANTS);
        properties.tickRandomly();
        properties.hardnessAndResistance(0.2F, 0.2F);
        properties.sound(SoundType.PLANT);
        properties.notSolid();
        properties.doesNotBlockMovement();
        return properties;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (state.get(AGE) == this.getMaxAge()) {
            int count = RANDOM.nextInt(maxFruit - minFruit) + minFruit;
            // Spawn the random drop count
            ItemStack itemStack = new ItemStack(vineFruitItem, count);
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemStack);

            // Decrease age to 0
            worldIn.setBlockState(pos, this.getActualBlockStateWithAge(worldIn, pos, 0), 2);
        }
        return ActionResultType.PASS;
    }

    protected static float getGrowthChance(Block blockIn, IBlockReader worldIn, BlockPos pos) {
        return BushUtils.getGrowthChance(blockIn, worldIn, pos);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        worldIn.setBlockState(pos, getActualBlockState(worldIn, pos), 2);
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public void withSeedsItem(Item seedsItem) {
        this.seedsItem = seedsItem;
    }

    public IItemProvider getSeedsItem() {
        assert seedsItem != null;
        return seedsItem;
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(this.getSeedsItem());
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    public boolean isMaxAge(BlockState state) {
        return state.get(this.getAgeProperty()) >= this.getMaxAge();
    }

    public int getMaxAge() {
        return 3;
    }

    protected int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    public BlockState withAge(int age) {
        return this.getDefaultState().with(this.getAgeProperty(), age);
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.up()).getBlock() instanceof GrowthcraftVineLeavesBlock;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return !this.isMaxAge(state);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return !this.isMaxAge(state);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        if (!worldIn.isAreaLoaded(pos, 1))
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (!state.isValidPosition(worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
            return;
        }

        if(startTime == 0){
            startTime = worldIn.getGameTime();
        }

        long pointsToGrow = (long) (configModifier /(int)  (getGrowthChance(this, worldIn, pos)) * (1+worldIn.rand.nextInt() % 20 / 100.0));
        if (worldIn.getLightSubtracted(pos, 0) >= 9) {
            if((worldIn.getGameTime() - startTime)%pointsToGrow <= averageRandomTick*1.5) {
                if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
                    grow(worldIn, rand, pos, state);
                    ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        this.grow(worldIn, pos, state);
    }

    public void grow(World worldIn, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        worldIn.setBlockState(pos, getActualBlockStateWithAge(worldIn, pos, i), 2);
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return MathHelper.nextInt(worldIn.rand, 1, 3);
    }

    public BlockState getActualBlockStateWithAge(World world, BlockPos blockPos, int age) {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(world, blockPos);

        return this.getDefaultState().with(AGE, age);
    }

    public BlockState getActualBlockState(World world, BlockPos blockPos) {
        return getActualBlockStateWithAge(world, blockPos, world.getBlockState(blockPos).get(this.getAgeProperty()));
    }

    @Override
    public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction facing) {
        return false;
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        worldIn.setBlockState(pos, getActualBlockStateWithAge(worldIn, pos, worldIn.getBlockState(pos).get(this.getAgeProperty())), 3);
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
    }
}
