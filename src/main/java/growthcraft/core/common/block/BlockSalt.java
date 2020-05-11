package growthcraft.core.common.block;

import growthcraft.core.shared.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockSalt extends Block {

    public final String unlocalizedName;

    public BlockSalt(String unlocalizedName) {
        super(getInitProperties());
        this.setRegistryName(Reference.MODID, unlocalizedName);
        this.unlocalizedName = unlocalizedName;
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.create(Material.SAND);
        properties.sound(SoundType.SAND);
        return  properties;
    }

    public String getUnlocalizedName() { return unlocalizedName; }

}
