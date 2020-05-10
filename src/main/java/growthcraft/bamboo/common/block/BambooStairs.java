package growthcraft.bamboo.common.block;

import growthcraft.core.shared.Reference;
import growthcraft.core.shared.block.GrowthcraftStairsBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BambooStairs extends GrowthcraftStairsBlock {

    public BambooStairs(Block block, String unlocalizedName) {
        super(block, getInitProperties(), unlocalizedName);
        this.setRegistryName(Reference.MODID, unlocalizedName);
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.WOOD);
        properties.hardnessAndResistance(1.5F);
        properties.sound(SoundType.WOOD);
        return properties;
    }

}
