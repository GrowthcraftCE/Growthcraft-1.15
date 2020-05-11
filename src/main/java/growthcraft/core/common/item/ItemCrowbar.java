package growthcraft.core.common.item;

import growthcraft.Growthcraft;
import growthcraft.core.shared.Reference;
import growthcraft.core.shared.item.GrowthcraftItem;
import net.minecraft.item.Item;

public class ItemCrowbar extends GrowthcraftItem {

    public static final String unlocalizedName = "crowbar";

    public ItemCrowbar(String colour) {
        super(unlocalizedName + "_" + colour, 1);
    }

}
