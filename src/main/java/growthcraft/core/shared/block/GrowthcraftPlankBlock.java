package growthcraft.core.shared.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class GrowthcraftPlankBlock extends Block {

    private final String unlocalizedName;

    public GrowthcraftPlankBlock(Material material, String unlocalizedName) {
        super(getInitProperties(material));
        this.unlocalizedName = unlocalizedName;
    }

    public GrowthcraftPlankBlock(Properties properties, String unlocalizedName) {
        super(properties);
        this.unlocalizedName = unlocalizedName;
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.hardnessAndResistance(2.0F, 3.0F);
        properties.harvestTool(ToolType.AXE);
        properties.harvestLevel(1);
        properties.sound(SoundType.WOOD);
        return properties;
    }

    public String getUnlocalizedName() { return unlocalizedName; }

}
