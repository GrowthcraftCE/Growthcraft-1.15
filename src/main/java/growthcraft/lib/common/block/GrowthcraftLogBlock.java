package growthcraft.lib.common.block;

import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class GrowthcraftLogBlock extends LogBlock {

    private final String unlocalizedName;

    public GrowthcraftLogBlock(String unlocalizedName) {
        this(unlocalizedName, MaterialColor.WOOD, getInitProperties(Material.WOOD));
    }

    public GrowthcraftLogBlock(String unlocalizedName, MaterialColor verticalColorIn, Properties properties) {
        super(verticalColorIn, properties);
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
