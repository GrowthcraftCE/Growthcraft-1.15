package growthcraft.lib.common.block;

import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GrowthcraftFenceBlock extends FenceBlock {

    private final String unlocalizedName;

    public GrowthcraftFenceBlock(String unlocalizedName) {
        super(getInitProperties(Material.WOOD));
        this.unlocalizedName = unlocalizedName;
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.hardnessAndResistance(0.5F);
        properties.doesNotBlockMovement();
        properties.sound(SoundType.WOOD);
        return properties;
    }

}
