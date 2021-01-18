package growthcraft.grapes.init;

import growthcraft.core.Growthcraft;
import growthcraft.grapes.common.block.BlockGrapeVine;
import growthcraft.grapes.common.block.BlockGrapeVineLeaves;
import growthcraft.grapes.common.block.BlockGrapeVinesFruit;
import growthcraft.grapes.shared.Reference;
import growthcraft.grapes.shared.UnlocalizedName;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class GrowthcraftGrapesBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<BlockGrapeVinesFruit> GRAPE_VINE_PURPLE_FRUIT = BLOCKS.register(
            "grape_vine_purple_fruit",
            () -> new BlockGrapeVinesFruit()
    );

    public static final RegistryObject<BlockGrapeVineLeaves> GRAPE_VINE_PURPLE_LEAVES = BLOCKS.register(
            "grape_vine_purple_leaves",
            () -> new BlockGrapeVineLeaves(GRAPE_VINE_PURPLE_FRUIT.get())
    );

    public static final RegistryObject<BlockGrapeVine> GRAPE_VINE_PURPLE = BLOCKS.register(
            UnlocalizedName.GRAPE_VINE_PURPLE,
            () -> new BlockGrapeVine(GRAPE_VINE_PURPLE_LEAVES.get()));

    private GrowthcraftGrapesBlocks() { /* Prevent default public constructor */ }

    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        Growthcraft.LOGGER.debug("Growthcraft Bamboo Registering itemBlocks ...");

        BLOCKS.getEntries().stream()
                .map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null && !excludeBlockItemRegistry(block.getRegistryName())) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

    }

    private static boolean excludeBlockItemRegistry(@Nonnull ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        excludeBlocks.add(GRAPE_VINE_PURPLE.get().getRegistryName().toString());
        excludeBlocks.add(GRAPE_VINE_PURPLE_LEAVES.get().getRegistryName().toString());
        //excludeBlocks.add(GRAPE_VINE_PURPLE_FRUIT.get().getRegistryName().toString());
        return excludeBlocks.contains(registryName.toString());
    }

}
