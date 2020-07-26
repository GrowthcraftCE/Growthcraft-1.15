package growthcraft.trapper.common.block;

import growthcraft.lib.common.block.GrowthcraftFishtrapBlock;
import growthcraft.trapper.init.GrowthcraftTrapperTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

/**
 * e
 * Customized Fishtrap Block - Inherit everything except createTileEntity.
 */
public class BlockAcaciaFishtrap extends GrowthcraftFishtrapBlock {

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return GrowthcraftTrapperTileEntities.acaciaFishtrapTileEntity.get().create();
    }

}
