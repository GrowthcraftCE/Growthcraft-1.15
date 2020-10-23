package growthcraft.cellar.init;

import growthcraft.cellar.common.block.BrewKettle;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class GrowthcraftCellarBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<BrewKettle> brewKettle;

    static {
        brewKettle = BLOCKS.register(
                UnlocalizedName.BREW_KETTLE,
                () -> new BrewKettle());
    }

    private GrowthcraftCellarBlocks() { /* Prevent default public constructor */ }

    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {

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
