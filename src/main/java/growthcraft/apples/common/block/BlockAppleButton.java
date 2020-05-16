package growthcraft.apples.common.block;

import growthcraft.lib.common.block.GrowthcraftButtonBlock;

public class BlockAppleButton extends GrowthcraftButtonBlock {

    private final String unlocalizedName;

    public BlockAppleButton(String unlocalizedName) {
        super(unlocalizedName);
        this.unlocalizedName = unlocalizedName;
    }

    public String getUnlocalizedName() { return unlocalizedName; }

}
