package growthcraft.bamboo.common.block;

import growthcraft.lib.common.block.GrowthcraftLogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class BlockBambooLog extends GrowthcraftLogBlock {

    public BlockBambooLog() {
        super(MaterialColor.FOLIAGE, getInitProperties(Material.BAMBOO));
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.hardnessAndResistance(1.0F, 1.0F);
        properties.sound(SoundType.BAMBOO);
        properties.notSolid();
        return properties;
    }

}
