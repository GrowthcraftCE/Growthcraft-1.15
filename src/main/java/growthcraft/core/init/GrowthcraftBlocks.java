package growthcraft.core.init;

import growthcraft.core.Growthcraft;
import growthcraft.core.common.block.BlockRockSaltOre;
import growthcraft.core.common.block.BlockSalt;
import growthcraft.core.shared.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class GrowthcraftBlocks {

    public static BlockRockSaltOre rockSaltOre;
    public static BlockSalt saltBlock;

    private GrowthcraftBlocks() { /* Disable default public constructor */ }

    static {
        rockSaltOre = new BlockRockSaltOre("rocksalt_ore");
        saltBlock = new BlockSalt("salt_block");
    }

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        Growthcraft.LOGGER.debug("Growthcraft-Core Registering blocks ...");

        registry.registerAll(
                saltBlock,
                rockSaltOre
        );

    }

    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        Growthcraft.LOGGER.debug("Growthcraft-Core Registering itemBlocks ...");

        itemRegistry.registerAll(
                new BlockItem(saltBlock, properties).setRegistryName(Reference.MODID, saltBlock.getUnlocalizedName()),
                new BlockItem(rockSaltOre, properties).setRegistryName(Reference.MODID, rockSaltOre.getUnlocalizedName())
        );

    }
}
