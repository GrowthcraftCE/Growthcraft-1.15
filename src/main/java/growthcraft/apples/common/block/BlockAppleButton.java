package growthcraft.apples.common.block;

import growthcraft.apples.shared.Reference;
import growthcraft.core.shared.block.GrowthcraftButtonBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockAppleButton extends GrowthcraftButtonBlock {

    private final String unlocalizedName;

    public BlockAppleButton(String unlocalizedName) {
        super(unlocalizedName);
        this.unlocalizedName = unlocalizedName;
        this.setRegistryName(Reference.MODID, unlocalizedName);
    }

    public String getUnlocalizedName() { return unlocalizedName; }

}
