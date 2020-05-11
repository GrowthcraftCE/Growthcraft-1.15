package growthcraft.apples.init;

import growthcraft.Growthcraft;
import growthcraft.apples.common.block.BlockAppleButton;
import growthcraft.apples.common.block.BlockApplePlanks;
import growthcraft.apples.common.block.BlockAppleStairs;
import growthcraft.apples.shared.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class GrowthcraftApplesBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);

    // public static Block definitions.
    public static final RegistryObject<BlockAppleButton> appleButton;
    public static final RegistryObject<BlockApplePlanks> applePlank;
    public static final RegistryObject<BlockAppleStairs> appleStairs;

    // Static initializer for Growthcraft Apples blocks.
    static {
        appleButton = BLOCKS.register(
                "apple_button",
                () -> new BlockAppleButton("apple_button"));
        applePlank = BLOCKS.register(
                "apple_plank",
                () -> new BlockApplePlanks("apple_plank"));
        appleStairs = BLOCKS.register(
                "apple_stairs",
                () -> new BlockAppleStairs(Blocks.OAK_PLANKS, "apple_stairs"));
    }

    private GrowthcraftApplesBlocks() { /* Prevent Default Public Constructor */ }

    /**
     * Legacy block registration. Use DeferredRegister<Block> and RegistryObject<T> instead.
     *
     * @param registry Forge Block Registry
     * @deprecated use GrowthcraftBambooBlocks.BLOCKS deferred registry instead.
     */
    @Deprecated
    public static void registerBlocks(IForgeRegistry<Block> registry) {
        Growthcraft.LOGGER.debug("Growthcraft Apples Registering blocks ...");
    }

    /**
     * Dynamically register Growthcraft Apples BlockItems.
     *
     * @param itemRegistry IForgeRegistry<Item> reference for registering items.
     * @param properties   Item properties with item group for creative tab.
     */
    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        Growthcraft.LOGGER.debug("Growthcraft Apples Registering itemBlocks ...");

        BLOCKS.getEntries().stream()
                .map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

    }
}
