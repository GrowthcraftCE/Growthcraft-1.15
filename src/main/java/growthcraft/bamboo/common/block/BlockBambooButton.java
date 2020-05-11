package growthcraft.bamboo.common.block;

import growthcraft.core.shared.Reference;
import growthcraft.core.shared.block.GrowthcraftButtonBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockBambooButton extends GrowthcraftButtonBlock {

    private final String unlocalizedName;

    public BlockBambooButton(String unlocalizedName) {
        super(unlocalizedName);
        this.unlocalizedName = unlocalizedName;
    }

    public String getUnlocalizedName() { return unlocalizedName; }

}
