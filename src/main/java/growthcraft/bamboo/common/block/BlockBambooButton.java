package growthcraft.bamboo.common.block;

import growthcraft.lib.common.block.GrowthcraftButtonBlock;

public class BlockBambooButton extends GrowthcraftButtonBlock {

    private final String unlocalizedName;

    public BlockBambooButton(String unlocalizedName) {
        super(unlocalizedName);
        this.unlocalizedName = unlocalizedName;
    }

    public String getUnlocalizedName() { return unlocalizedName; }

}
