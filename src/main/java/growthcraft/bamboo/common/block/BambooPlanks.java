package growthcraft.bamboo.common.block;

import growthcraft.core.shared.Reference;
import growthcraft.core.shared.block.GrowthcraftPlankBlock;
import net.minecraft.block.material.Material;

public class BambooPlanks extends GrowthcraftPlankBlock {
    public BambooPlanks(String unlocalizedName) {
        super(Material.WOOD, unlocalizedName);
        this.setRegistryName(Reference.MODID, unlocalizedName);
    }
}
