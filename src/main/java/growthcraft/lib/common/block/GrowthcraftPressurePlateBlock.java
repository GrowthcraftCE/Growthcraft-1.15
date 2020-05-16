package growthcraft.lib.common.block;

import growthcraft.core.shared.Reference;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GrowthcraftPressurePlateBlock extends PressurePlateBlock {

    private final String unlocalizedName;

    public GrowthcraftPressurePlateBlock(String unlocalizedName, Sensitivity sensitivity) {
        super(sensitivity, getInitProperties());
        this.unlocalizedName = unlocalizedName;
        this.setRegistryName(Reference.MODID, unlocalizedName);
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.WOOD);
        properties.hardnessAndResistance(1.5F);
        properties.sound(SoundType.WOOD);
        return properties;
    }

    public String getUnlocalizedName() { return unlocalizedName; }
}
