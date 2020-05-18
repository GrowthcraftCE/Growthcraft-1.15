package growthcraft.apples.init;

import growthcraft.apples.common.block.BlockAppleStairs;
import growthcraft.apples.shared.Reference;
import growthcraft.apples.shared.UnlocalizedName;
import growthcraft.core.Growthcraft;
import growthcraft.lib.common.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class GrowthcraftApplesBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);

    // public static Block definitions.
    public static final RegistryObject<GrowthcraftButtonBlock> applePlankButton;
    public static final RegistryObject<GrowthcraftPlankBlock> applePlank;
    // TODO[43]: Implement applePlankBoat;
    public static final RegistryObject<GrowthcraftFenceBlock> applePlankFence;
    public static final RegistryObject<GrowthcraftFenceGateBlock> applePlankFenceGate;
    // TODO[38]: Implement applePlankFenceRope;
    public static final RegistryObject<GrowthcraftPressurePlateBlock> applePlankPressurePlate;
    // TODO[41]: Implement applePlankSign;
    public static final RegistryObject<GrowthcraftSlabBlock> applePlankSlab;
    public static final RegistryObject<BlockAppleStairs> applePlankStairs;
    public static final RegistryObject<GrowthcraftTrapdoor> applePlankTrapdoor;
    public static final RegistryObject<GrowthcraftDoorBlock> applePlankDoor;
    // TODO[39]: **Implement appleTreeLeaves;
    // TODO[34]: **Implement appleTreeSapling;
    public static final RegistryObject<GrowthcraftLogBlock> appleWood;
    public static final RegistryObject<GrowthcraftLogBlock> appleWoodStripped;
    public static final RegistryObject<GrowthcraftLogBlock> appleWoodLog;
    public static final RegistryObject<GrowthcraftLogBlock> appleWoodLogStripped;

    // Static initializer for Growthcraft Apples blocks.
    static {
        applePlankButton = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_BUTTON,
                () -> new GrowthcraftButtonBlock(UnlocalizedName.APPLE_PLANK_BUTTON));
        applePlank = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK,
                () -> new GrowthcraftPlankBlock(UnlocalizedName.APPLE_PLANK));
        applePlankDoor = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_DOOR,
                () -> new GrowthcraftDoorBlock(UnlocalizedName.APPLE_PLANK_DOOR));
        applePlankFence = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_FENCE,
                () -> new GrowthcraftFenceBlock(UnlocalizedName.APPLE_PLANK_FENCE));
        applePlankFenceGate = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_FENCE_GATE,
                () -> new GrowthcraftFenceGateBlock(UnlocalizedName.APPLE_PLANK_FENCE_GATE));
        applePlankPressurePlate = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_PRESSURE_PLATE,
                () -> new GrowthcraftPressurePlateBlock(UnlocalizedName.APPLE_PLANK_PRESSURE_PLATE));
        applePlankSlab = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_SLAB,
                () -> new GrowthcraftSlabBlock(UnlocalizedName.APPLE_PLANK_SLAB));
        applePlankStairs = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_STAIRS,
                () -> new BlockAppleStairs(Blocks.OAK_PLANKS, UnlocalizedName.APPLE_PLANK_STAIRS));
        applePlankTrapdoor = BLOCKS.register(
                UnlocalizedName.APPLE_PLANK_TRAPDOOR,
                () -> new GrowthcraftTrapdoor(UnlocalizedName.APPLE_PLANK_TRAPDOOR, Material.WOOD));
        appleWood = BLOCKS.register(
                UnlocalizedName.APPLE_WOOD,
                () -> new GrowthcraftLogBlock(UnlocalizedName.APPLE_WOOD));
        appleWoodStripped = BLOCKS.register(
                UnlocalizedName.APPLE_WOOD_STRIPPED,
                () -> new GrowthcraftLogBlock(UnlocalizedName.APPLE_WOOD_STRIPPED));
        appleWoodLog = BLOCKS.register(
                UnlocalizedName.APPLE_WOOD_LOG,
                () -> new GrowthcraftLogBlock(UnlocalizedName.APPLE_WOOD_LOG));
        appleWoodLogStripped = BLOCKS.register(
                UnlocalizedName.APPLE_WOOD_LOG_STRIPPED,
                () -> new GrowthcraftLogBlock(UnlocalizedName.APPLE_WOOD_LOG_STRIPPED));
    }

    private GrowthcraftApplesBlocks() { /* Prevent Default Public Constructor */ }

    /**
     * Dynamically register Growthcraft Apples BlockItems.
     *
     * @param itemRegistry IForgeRegistry<Item> reference for registering items.
     * @param properties   Item properties with item group for creative tab.
     */
    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        Growthcraft.LOGGER.debug("Growthcraft Apples Registering itemBlocks ...");

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

        Growthcraft.LOGGER.debug("Growthcraft Apples itemBlocks Registered.");

    }
}
