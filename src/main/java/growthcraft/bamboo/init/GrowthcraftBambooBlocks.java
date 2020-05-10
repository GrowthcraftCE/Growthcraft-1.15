package growthcraft.bamboo.init;

import growthcraft.Growthcraft;
import growthcraft.bamboo.common.block.BambooPlanks;
import growthcraft.bamboo.common.block.BambooStairs;
import growthcraft.core.shared.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class GrowthcraftBambooBlocks {

    public static BambooStairs bambooStairs = new BambooStairs(Blocks.OAK_PLANKS, "bamboo_stairs");
    public static BambooPlanks bambooPlank = new BambooPlanks("bamboo_plank");
    public static Block bambooSlabHalf;
    public static Block bambooSlabDouble;
    public static Block bambooFence;
    public static Block bambooFenceGate;
    public static Block bambooLeaves;
    public static Block bambooStalk;
    public static Block bambooShoot;
    public static Block blockBambooDoor;
    public static Block ropeKnotBamboo;

    private GrowthcraftBambooBlocks() { /* Prevent default public constuctor */ }

    public static void initBlocks() {
// Empty for now ...
    }

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        Growthcraft.LOGGER.debug("Registering blocks ...");

        registry.register(bambooStairs);
        registry.register(bambooPlank);

    }

    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        Growthcraft.LOGGER.debug("Registering itemBlocks ...");

        itemRegistry.register(
                new BlockItem(bambooStairs, properties)
                        .setRegistryName(Reference.MODID, bambooStairs.getUnlocalizedName())
        );
        itemRegistry.register(
                new BlockItem(bambooPlank, properties)
                        .setRegistryName(Reference.MODID, bambooPlank.getUnlocalizedName())
        );

    }

}
