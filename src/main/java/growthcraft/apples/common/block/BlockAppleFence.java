package growthcraft.apples.common.block;

import growthcraft.lib.common.block.GrowthcraftFenceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockAppleFence extends GrowthcraftFenceBlock {

    public BlockAppleFence(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction facing) {
        if ( state.getBlock() instanceof FenceBlock ) { return true; }
        return false;
    }

}
