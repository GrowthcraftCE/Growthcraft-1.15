package growthcraft.lib.common.block;

import growthcraft.core.init.config.GrowthcraftConfig;
import growthcraft.core.shared.Reference;
import growthcraft.grapes.init.config.GrowthcraftGrapesConfig;
import growthcraft.lib.common.block.rope.IBlockRope;
import growthcraft.lib.utils.BlockStateUtils;
import growthcraft.lib.utils.BushUtils;
import growthcraft.lib.utils.RopeUtils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
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
 * A BushBlock that grows from a GrowthcraftVineLavesBlock and produces a fruit.
 *
 * @author Alatyami
 * @since 5.0.0
 */
@SuppressWarnings("java:S1874")
public class GrowthcraftVineLeavesBlock extends BushBlock implements IBlockRope, IGrowable {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_7;
    public static final BooleanProperty TRUNK_CONNECTED = BooleanProperty.create("trunk_connected");

    private long randomTickCount = 0;
    private long pointsToGrow = 0;

    protected static VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    private Item seedsItem;

    private GrowthcraftVineFruitBlock vineFruitBlock;

    public GrowthcraftVineLeavesBlock(GrowthcraftVineFruitBlock vineFruitBlock) {
        this(getInitProperties());
        this.vineFruitBlock = vineFruitBlock;
    }

    public GrowthcraftVineLeavesBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(AGE, 0)
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false)
                .with(TRUNK_CONNECTED, false));
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.PLANTS);
        properties.tickRandomly();
        properties.hardnessAndResistance(0.2F, 0.2F);
        properties.sound(SoundType.PLANT);
        properties.notSolid();
        return properties;
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
        builder.add(AGE, NORTH, EAST, SOUTH, WEST, UP, DOWN, TRUNK_CONNECTED);
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
        return 7;
    }

    protected int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    public BlockState withAge(int age) {
        return this.getDefaultState().with(this.getAgeProperty(), age);
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() == Blocks.FARMLAND || state.getBlock() instanceof GrowthcraftVineLeavesBlock || state.getBlock() == Blocks.AIR || state.getBlock() instanceof GrowthcraftVineFruitBlock;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        if ( this.isMaxAge(state) ) {
            Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(worldIn, pos);
            if (RopeUtils.isRopeBlock(blockMap.get("north"))
                    || RopeUtils.isRopeBlock(blockMap.get("east"))
                    || RopeUtils.isRopeBlock(blockMap.get("south"))
                    || RopeUtils.isRopeBlock(blockMap.get("west"))
                    || blockMap.get("down") == Blocks.AIR) {
                return true;
            }
        }

        return !this.isMaxAge(state);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        Tag<Block> tagRope = BlockTags.getCollection().getOrCreate(Reference.TAG_ROPE);

        Map<String, Block> blockMap = BlockStateUtils.getHorizontalBlocks(worldIn, pos);
        for(Map.Entry<String, Block> entry : blockMap.entrySet() ) {
            if(tagRope.contains(entry.getValue()) && !(entry.getValue() instanceof GrowthcraftVineLeavesBlock)) {
                return true;
            }
        }
        return (worldIn.getBlockState(pos.down()).getBlock() == Blocks.AIR);
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
        if(pointsToGrow == 0){
            pointsToGrow = (long) ((GrowthcraftConfig.getPointsToGrow() /(int)  (getGrowthChance(this, worldIn, pos)* GrowthcraftGrapeConfig.getGrapeGrowModifier())) * (1+worldIn.rand.nextInt() % 20 / 100.0));
        }
        if (worldIn.getLightSubtracted(pos, 0) >= 9) {
            randomTickCount++;
            if(randomTickCount * 1365 >=  pointsToGrow) {
                // 1365 is the average ticks between two random tick
                if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
                    grow(worldIn, rand, pos, state);
                    ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
                randomTickCount = 0;
                pointsToGrow = (long) ((GrowthcraftConfig.getPointsToGrow() /(int)  (getGrowthChance(this, worldIn, pos)* GrowthcraftGrapesConfig.getGrapeGrowModifier())) * (1+worldIn.rand.nextInt() % 20 / 100.0));
            }
        }
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        this.grow(worldIn, pos, state);
    }

    /* Increment AGE, Extend the Grape Vine Leaves, or spawn fruit leaves. */
    public void grow(World worldIn, BlockPos pos, BlockState state) {

        int i = this.getAge(state);
        if (i != this.getMaxAge()){
            //age up
            worldIn.setBlockState(pos, getActualBlockStateWithAge(worldIn, pos, i+1), 2);
            randomTickCount = 0;
            Growthcraft.LOGGER.debug(randomTickCount);
        }

        if (i == this.getMaxAge()) {
            boolean trySpawnFruitBlock = true;
            Tag<Block> tagRope = BlockTags.getCollection().getOrCreate(Reference.TAG_ROPE);

            Map<BlockPos, BlockState> blockMap = BlockStateUtils.getHorizontalBlockPos(worldIn, pos);
            for(Map.Entry<BlockPos, BlockState> entry : blockMap.entrySet() ) {
                if(tagRope.contains(entry.getValue().getBlock()) ) {
                    worldIn.setBlockState(entry.getKey(), this.getActualBlockStateWithAge(worldIn, entry.getKey(), 0));
                    trySpawnFruitBlock = false;
                    return;
                }
            }

            if(trySpawnFruitBlock) {
                if(worldIn.getBlockState(pos.down()).getBlock() == Blocks.AIR) {
                    worldIn.setBlockState(pos.down(), vineFruitBlock.getDefaultState());
                }
            }

        }
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return MathHelper.nextInt(worldIn.rand, 1, 3);
    }

    public BlockState getActualBlockStateWithAge(World world, BlockPos blockPos, int age) {
        Map<String, Block> blockMap = BlockStateUtils.getSurroundingBlocks(world, blockPos);

        return this.getDefaultState()
                .with(NORTH, RopeUtils.isRopeBlock(blockMap.get("north")))
                .with(EAST, RopeUtils.isRopeBlock(blockMap.get("east")))
                .with(SOUTH, RopeUtils.isRopeBlock(blockMap.get("south")))
                .with(WEST, RopeUtils.isRopeBlock(blockMap.get("west")))
                .with(UP, RopeUtils.isRopeBlock(blockMap.get("up")))
                .with(DOWN, RopeUtils.isRopeBlock(blockMap.get("down")))
                .with(AGE, age)
                .with(TRUNK_CONNECTED, blockMap.get("down").getBlock() instanceof GrowthcraftVineBlock);
    }

    public BlockState getActualBlockState(World world, BlockPos blockPos) {
        return getActualBlockStateWithAge(world, blockPos, world.getBlockState(blockPos).get(this.getAgeProperty()));
    }

    @Override
    public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction facing) {
        return RopeUtils.isRopeBlock(state.getBlock());
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        worldIn.setBlockState(pos, getActualBlockState(worldIn, pos), 3);
        pointsToGrow = (long) ((GrowthcraftConfig.getPointsToGrow() /(int)  (getGrowthChance(this, worldIn, pos)* GrowthcraftGrapesConfig.getGrapeGrowModifier())) * (1+worldIn.rand.nextInt() % 20 / 100.0));
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

}
