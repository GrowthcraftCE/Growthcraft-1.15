package growthcraft.apples.init;

import growthcraft.Growthcraft;
import growthcraft.apples.common.block.BlockAppleButton;
import growthcraft.apples.common.block.BlockApplePlanks;
import growthcraft.apples.common.block.BlockAppleStairs;
import growthcraft.apples.shared.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class GrowthcraftApplesBlocks {

    // public static Block definitions.
    public static BlockAppleButton appleButton;
    public static BlockApplePlanks applePlank;
    public static BlockAppleStairs appleStairs;

    private GrowthcraftApplesBlocks() { /* Prevent Default Public Constructor */ }

    // static initialization of our block definitions
    static {
        appleButton = new BlockAppleButton("apple_button");
        applePlank = new BlockApplePlanks("apple_plank");
    }

    // Add the blocks to the registry.
    public static void registerBlocks(IForgeRegistry<Block> registry) {
        Growthcraft.LOGGER.debug("Growthcraft Apples Registering blocks ...");

        registry.registerAll(
                appleButton,
                applePlank
        );

    }

    // Register the block items.
    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        Growthcraft.LOGGER.debug("Growthcraft Bamboo Registering itemBlocks ...");

        itemRegistry.registerAll(
                new BlockItem(appleButton, properties)
                        .setRegistryName(Reference.MODID, appleButton.getUnlocalizedName()),
                new BlockItem(applePlank, properties)
                        .setRegistryName(Reference.MODID, applePlank.getUnlocalizedName())
        );

    }
}
