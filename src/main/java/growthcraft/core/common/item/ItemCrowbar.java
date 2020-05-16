package growthcraft.core.common.item;

import growthcraft.lib.common.item.GrowthcraftItem;

public class ItemCrowbar extends GrowthcraftItem {

    public static final String unlocalizedName = "crowbar";

    public ItemCrowbar(String colour) {
        super(unlocalizedName + "_" + colour, 1);
    }

}
