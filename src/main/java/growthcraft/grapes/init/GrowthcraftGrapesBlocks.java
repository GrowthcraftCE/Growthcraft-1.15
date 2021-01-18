package growthcraft.grapes.init;

import growthcraft.core.Growthcraft;
import growthcraft.grapes.common.block.BlockGrapeVine;
import growthcraft.grapes.shared.Reference;
import growthcraft.grapes.shared.UnlocalizedName;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class GrowthcraftGrapesBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<BlockGrapeVine> GRAPE_VINE_PURPLE = BLOCKS.register(
            UnlocalizedName.GRAPE_VINE_PURPLE,

            );

    static {
        /**
         * bambooButton = BLOCKS.register(
         *                 "bamboo_button",
         *                 () -> new BlockBambooButton("bamboo_button"));
         */
    }

    /**
     * public static final RegistryObject<BlockBambooStairs> bambooStairs;
     */

    private GrowthcraftGrapesBlocks() { /* Prevent default public constructor */ }

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
