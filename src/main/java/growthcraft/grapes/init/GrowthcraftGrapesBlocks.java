package growthcraft.grapes.init;

import growthcraft.core.Growthcraft;
import growthcraft.grapes.common.block.BlockGrapeVine;
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

import java.util.ArrayList;

public class GrowthcraftGrapesBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<BlockGrapeVine> GRAPE_VINE_PURPLE = BLOCKS.register(
            UnlocalizedName.GRAPE_VINE_PURPLE,
            BlockGrapeVine::new);

    public static final RegistryObject<BlockGrapeVine> GRAPE_VINE_RED = BLOCKS.register(
            UnlocalizedName.GRAPE_VINE_RED,
            BlockGrapeVine::new);

    public static final RegistryObject<BlockGrapeVine> GRAPE_VINE_WHITE = BLOCKS.register(
            UnlocalizedName.GRAPE_VINE_WHITE,
            BlockGrapeVine::new);

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

    private static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        excludeBlocks.add(GRAPE_VINE_PURPLE.get().getRegistryName().toString());
        excludeBlocks.add(GRAPE_VINE_RED.get().getRegistryName().toString());
        excludeBlocks.add(GRAPE_VINE_WHITE.get().getRegistryName().toString());
        return excludeBlocks.contains(registryName.toString());
    }

}
