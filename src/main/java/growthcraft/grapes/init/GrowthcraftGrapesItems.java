package growthcraft.grapes.init;

import growthcraft.grapes.common.item.ItemGrape;
import growthcraft.grapes.shared.Reference;
import growthcraft.grapes.shared.UnlocalizedName;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftGrapesItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MODID);
    /**
     * Food items are special as you have to pass the item group when you instantiate it. We cannot
     * append it later.
     */
    public static final DeferredRegister<Item> FOODS = new DeferredRegister<>(ForgeRegistries.ITEMS,
            Reference.MODID);

    public static final RegistryObject<ItemGrape> grapesRed;
    public static final RegistryObject<ItemGrape> grapesWhite;
    public static final RegistryObject<ItemGrape> grapesPurple;

    static {
        /**
         * bambooCoal = ITEMS.register("bamboo_coal", () -> new ItemBambooCoal("bamboo_coal"));
         */

        grapesRed = FOODS.register(
                UnlocalizedName.GRAPES_RED,
                () -> new ItemGrape(1, 1.0F));
        grapesPurple = FOODS.register(
                UnlocalizedName.GRAPES_PURPLE,
                () -> new ItemGrape(1, 1.0F));
        grapesWhite = FOODS.register(
                UnlocalizedName.GRAPES_WHITE,
                () -> new ItemGrape(1, 1.0F));

    }

    private GrowthcraftGrapesItems() { /* Prevent default public constructor */ }

}
