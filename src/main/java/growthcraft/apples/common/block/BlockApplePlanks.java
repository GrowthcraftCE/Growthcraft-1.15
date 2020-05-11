package growthcraft.apples.common.block;

import growthcraft.apples.shared.Reference;
import growthcraft.core.shared.block.GrowthcraftPlankBlock;
import net.minecraft.block.material.Material;

public class BlockApplePlanks extends GrowthcraftPlankBlock {
    public BlockApplePlanks(String unlocalizedName) {
        super(Material.WOOD, unlocalizedName);
        this.setRegistryName(Reference.MODID, unlocalizedName);
    }
}
