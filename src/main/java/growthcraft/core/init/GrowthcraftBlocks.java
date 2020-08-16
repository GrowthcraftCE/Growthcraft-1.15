package growthcraft.core.init;

import growthcraft.core.Growthcraft;
import growthcraft.core.common.block.BlockRockSaltOre;
import growthcraft.core.common.block.BlockSalt;
import growthcraft.core.shared.Reference;
import growthcraft.core.shared.UnlocalizedName;
import growthcraft.lib.common.block.RopeBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class GrowthcraftBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<BlockRockSaltOre> rock_salt_ore;
    public static final RegistryObject<BlockSalt> salt_block;
    public static final RegistryObject<RopeBlock> rope_linen;

    static {
        rock_salt_ore = BLOCKS.register(
                UnlocalizedName.ROCK_SALT_ORE,
                () -> new BlockRockSaltOre(UnlocalizedName.ROCK_SALT_ORE));
        salt_block = BLOCKS.register(
                UnlocalizedName.SALT_BLOCK,
                () -> new BlockSalt(UnlocalizedName.SALT_BLOCK)
        );
        rope_linen = BLOCKS.register(
                UnlocalizedName.ROPE_LINEN,
                () -> new RopeBlock());
    }

    private GrowthcraftBlocks() { /* Disable default public constructor */ }

    /**
     * Dynamically register Growthcraft Core BlockItems.
     *
     * @param itemRegistry IForgeRegistry<Item> reference for registering items.
     * @param properties   Item properties with item group for creative tab.
     */
    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        Growthcraft.LOGGER.debug("<Growthcraft-Core> Registration of itemBlocks started ...");

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final BlockItem blockItem = new BlockItem(block, properties);
            if (block.getRegistryName() != null) {
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });

        Growthcraft.LOGGER.debug("<Growthcraft-Core> Registration of itemBlocks finished.");
    }
}
