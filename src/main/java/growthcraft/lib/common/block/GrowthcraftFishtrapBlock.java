package growthcraft.lib.common.block;

import growthcraft.core.init.GrowthcraftSoundsEvents;
import growthcraft.trapper.common.tileentity.TileEntityFishtrap;
import growthcraft.trapper.init.GrowthcraftTrapperTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class GrowthcraftFishtrapBlock extends Block {

    public GrowthcraftFishtrapBlock() {
        this(getInitProperties(Material.WOOD));
    }

    public GrowthcraftFishtrapBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.hardnessAndResistance(2.0F, 3.0F);
        properties.harvestTool(ToolType.AXE);
        properties.harvestLevel(1);
        properties.notSolid();
        properties.sound(SoundType.WOOD);
        return properties;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftTrapperTileEntities.oakFishtrapTileEntity.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof TileEntityFishtrap) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (TileEntityFishtrap) tile, pos);
                ((TileEntityFishtrap) tile).playSound(null, GrowthcraftSoundsEvents.fishtrapOpen.get());
                return ActionResultType.SUCCESS;
            }
        } else {
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof TileEntityFishtrap) {
                // Drop the inventory contents into the world.
                InventoryHelper.dropItems(worldIn, pos, ((TileEntityFishtrap) tile).getItems());
            }
        }
    }
}
