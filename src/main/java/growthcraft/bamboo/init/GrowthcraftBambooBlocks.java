package growthcraft.bamboo.init;

import growthcraft.bamboo.common.block.BlockBambooButton;
import growthcraft.bamboo.common.block.BlockBambooPlanks;
import growthcraft.bamboo.common.block.BlockBambooStairs;
import growthcraft.bamboo.shared.Reference;
import growthcraft.core.Growthcraft;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class GrowthcraftBambooBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<BlockBambooStairs> bambooStairs;
    public static final RegistryObject<BlockBambooPlanks> bambooPlank;
    public static final RegistryObject<BlockBambooButton> bambooButton;
    public static Block bambooSlabHalf;
    public static Block bambooSlabDouble;
    public static Block bambooFence;
    public static Block bambooFenceGate;
    public static Block bambooLeaves;
    public static Block bambooStalk;
    public static Block bambooShoot;
    public static Block blockBambooDoor;
    public static Block ropeKnotBamboo;

    static {
        bambooButton = BLOCKS.register(
                "bamboo_button",
                () -> new BlockBambooButton("bamboo_button"));
        bambooPlank = BLOCKS.register(
                "bamboo_plank",
                () -> new BlockBambooPlanks("bamboo_plank"));
        bambooStairs = BLOCKS.register(
                "bamboo_stairs",
                () -> new BlockBambooStairs(Blocks.OAK_PLANKS, "bamboo_stairs"));
    }

    private GrowthcraftBambooBlocks() { /* Prevent default public constructor */ }

    /**
     * Legacy block registration. Use DeferredRegister<Block> and RegistryObject<T> instead.
     *
     * @param registry Forge Block Registry
     * @deprecated use GrowthcraftBambooBlocks.BLOCKS deferred registry instead.
     */
    @Deprecated
    public static void registerBlocks(IForgeRegistry<Block> registry) {
        Growthcraft.LOGGER.debug("Growthcraft Bamboo Registering blocks ...");
    }

    /**
     * Dynamically register Growthcraft Apples BlockItems.
     *
     * @param itemRegistry IForgeRegistry<Item> reference for registering items.
     * @param properties   Item properties with item group for creative tab.
     */
    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        Growthcraft.LOGGER.debug("Growthcraft Bamboo Registering itemBlocks ...");

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
