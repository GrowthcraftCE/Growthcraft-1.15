package growthcraft.bamboo.init;

import growthcraft.bamboo.common.block.BlockBambooButton;
import growthcraft.bamboo.common.block.BlockBambooPlanks;
import growthcraft.bamboo.common.block.BlockBambooStairs;
import growthcraft.bamboo.shared.Reference;
import growthcraft.bamboo.shared.UnlocalizedName;
import growthcraft.core.Growthcraft;
import growthcraft.lib.common.block.GrowthcraftButtonBlock;
import growthcraft.lib.common.block.GrowthcraftFenceBlock;
import growthcraft.lib.common.block.GrowthcraftPlankBlock;
import growthcraft.lib.common.block.GrowthcraftStairsBlock;
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
    // TODO[]: Implement bambooPlankBoat
    public static final RegistryObject<GrowthcraftFenceBlock> bambooPlankFence;
    // TODO[21]: Implement bambooPlankFenceGate;
    // TODO[25]: Implement bambooPlankFenceRope
    // TODO[]: Implement bambooPlankPressurePlate
    // TODO[23]: Implement bambooPlankSlab
    public static final RegistryObject<GrowthcraftStairsBlock> bambooStairs;
    // TODO[19]: Implement bambooPlankTrapdoor
    // TODO[]: Implement bambooPlankDoor
    // TODO[]: Implement bambooTreeLeaves
    // TODO[22]: Implement bambooTreeSapling
    // TODO[]: Implement bambooWood
    // TODO[]: Implement bambooWoodStripped
    // TODO[24]: Implement bambooWoodLog
    // TODO[]: Implement bambooWoodLogStripped

    static {
        bambooPlankButton = BLOCKS.register(
                UnlocalizedName.BAMBOO_PLANK_BUTTON,
                () -> new BlockBambooButton(UnlocalizedName.BAMBOO_PLANK_BUTTON));
        bambooPlank = BLOCKS.register(
                UnlocalizedName.BAMBOO_PLANK,
                () -> new BlockBambooPlanks(UnlocalizedName.BAMBOO_PLANK));
        bambooPlankFence = BLOCKS.register(
                UnlocalizedName.BAMBOO_PLANK_FENCE,
                () -> new GrowthcraftFenceBlock(UnlocalizedName.BAMBOO_PLANK_FENCE));
        bambooStairs = BLOCKS.register(
                UnlocalizedName.BAMBOO_PLANK_STAIRS,
                () -> new BlockBambooStairs(Blocks.OAK_PLANKS, UnlocalizedName.BAMBOO_PLANK_STAIRS));
    }

    private GrowthcraftBambooBlocks() { /* Prevent default public constructor */ }

    /**
     * Dynamically register Growthcraft Apples BlockItems.
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
