package growthcraft.bamboo.init;

import growthcraft.bamboo.common.block.BlockBambooLog;
import growthcraft.bamboo.common.block.BlockBambooStairs;
import growthcraft.bamboo.shared.Reference;
import growthcraft.bamboo.shared.UnlocalizedName;
import growthcraft.core.Growthcraft;
import growthcraft.lib.common.block.*;
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

    public static final RegistryObject<GrowthcraftButtonBlock> bambooPlankButton;
    public static final RegistryObject<GrowthcraftPlankBlock> bambooPlank;
    public static final RegistryObject<GrowthcraftFenceBlock> bambooPlankFence;
    public static final RegistryObject<GrowthcraftFenceGateBlock> bambooPlankFenceGate;
    // TODO[25]: Implement bambooPlankFenceRope
    public static final RegistryObject<GrowthcraftPressurePlateBlock> bambooPlankPressurePlate;
    public static final RegistryObject<GrowthcraftSlabBlock> bambooPlankSlab;
    public static final RegistryObject<GrowthcraftStairsBlock> bambooPlankStairs;
    // TODO[19]: Implement bambooPlankTrapdoor
    public static final RegistryObject<GrowthcraftDoorBlock> bambooPlankDoor;
    public static final RegistryObject<GrowthcraftTreeLeaves> bambooTreeLeaves;
    // TODO[22]: Implement bambooTreeSapling
    public static final RegistryObject<BlockBambooLog> bambooWood;
    public static final RegistryObject<BlockBambooLog> bambooWoodStripped;
    public static final RegistryObject<BlockBambooLog> bambooWoodLog;
    public static final RegistryObject<BlockBambooLog> bambooWoodLogStripped;

    static {
        bambooPlankButton = BLOCKS.register(
                UnlocalizedName.BAMBOO_PLANK_BUTTON,
                () -> new GrowthcraftButtonBlock(UnlocalizedName.BAMBOO_PLANK_BUTTON));
        bambooPlank = BLOCKS.register(
                UnlocalizedName.BAMBOO_PLANK,
                () -> new GrowthcraftPlankBlock(UnlocalizedName.BAMBOO_PLANK));
        bambooPlankDoor = BLOCKS.register(
                UnlocalizedName.BAMBOO_PLANK_DOOR,
                () -> new GrowthcraftDoorBlock(UnlocalizedName.BAMBOO_PLANK_DOOR));
        bambooPlankFence = BLOCKS.register(
                UnlocalizedName.BAMBOO_PLANK_FENCE,
                () -> new GrowthcraftFenceBlock(UnlocalizedName.BAMBOO_PLANK_FENCE));
        bambooPlankFenceGate = BLOCKS.register(
                UnlocalizedName.BAMBOO_PLANK_FENCE_GATE,
                () -> new GrowthcraftFenceGateBlock(UnlocalizedName.BAMBOO_PLANK_FENCE_GATE));
        bambooPlankPressurePlate = BLOCKS.register(
                UnlocalizedName.BAMBOO_PLANK_PRESSURE_PLATE,
                () -> new GrowthcraftPressurePlateBlock(UnlocalizedName.BAMBOO_PLANK_PRESSURE_PLATE));
        bambooPlankSlab = BLOCKS.register(
                UnlocalizedName.BAMBOO_PLANK_SLAB,
                () -> new GrowthcraftSlabBlock(UnlocalizedName.BAMBOO_PLANK_SLAB));
        bambooPlankStairs = BLOCKS.register(
                UnlocalizedName.BAMBOO_PLANK_STAIRS,
                () -> new BlockBambooStairs(Blocks.OAK_PLANKS, UnlocalizedName.BAMBOO_PLANK_STAIRS));
        bambooTreeLeaves = BLOCKS.register(
                UnlocalizedName.BAMBOO_TREE_LEAVES,
                () -> new GrowthcraftTreeLeaves());
        bambooWood = BLOCKS.register(
                UnlocalizedName.BAMBOO_WOOD,
                () -> new BlockBambooLog());
        bambooWoodStripped = BLOCKS.register(
                UnlocalizedName.BAMBOO_WOOD_STRIPPED,
                () -> new BlockBambooLog());
        bambooWoodLog = BLOCKS.register(
                UnlocalizedName.BAMBOO_WOOD_LOG,
                () -> new BlockBambooLog());
        bambooWoodLogStripped = BLOCKS.register(
                UnlocalizedName.BAMBOO_WOOD_LOG_STRIPPED,
                () -> new BlockBambooLog());
    }

    private GrowthcraftBambooBlocks() { /* Prevent default public constructor */ }

    /**
     * Dynamically register Growthcraft Bamboo BlockItems.
     *
     * @param itemRegistry IForgeRegistry<Item> reference for registering items.
     * @param properties   Item properties with item group for creative tab.
     */
    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        Growthcraft.LOGGER.debug("Growthcraft Bamboo Registering itemBlocks ...");

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

        Growthcraft.LOGGER.debug("Growthcraft Bamboo itemBlocks registered.");
    }

}
