package growthcraft.deco.common.block;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;

public class StoneStairs extends StairsBlock {

    public StoneStairs() {
        super(Blocks.STONE::getDefaultState, getInitProperties());
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.ROCK);
        properties.hardnessAndResistance(1.5F, 6.0F);
        properties.sound(SoundType.CLOTH);
        return properties;
    }

}
