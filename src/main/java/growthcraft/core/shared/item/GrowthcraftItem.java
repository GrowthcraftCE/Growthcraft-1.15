package growthcraft.core.shared.item;

import growthcraft.Growthcraft;
import net.minecraft.item.Item;

public class GrowthcraftItem extends Item {

    private final String unlocalizedName;

    public GrowthcraftItem(String unlocalizedName) {
        super(getInitProperties(64));
        this.unlocalizedName = unlocalizedName;
    }

    public GrowthcraftItem(String unlocalizedName, int maxStackSize) {
        super(getInitProperties(maxStackSize));
        this.unlocalizedName = unlocalizedName;
    }

    /**
     * Setup properties that we want all Growthcraft Items to have, like the
     * creative tab.
     * @return
     */
    private static Properties getInitProperties(int maxStackSize) {
        Properties properties = new Properties();
        properties.group(Growthcraft.itemGroup);
        return properties;
    }

    public String getUnlocalizedName() { return unlocalizedName; }

}
