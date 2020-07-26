package growthcraft.trapper.init;

import growthcraft.trapper.common.block.BlockFishtrap;
import growthcraft.trapper.shared.Reference;
import growthcraft.trapper.shared.UnlocalizedName;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class GrowthcraftTrapperBlocks {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);

    /* TODO[13]: Implement oakFishtrap */
    public static final RegistryObject<BlockFishtrap> oakFishtrap;
    public static final RegistryObject<BlockFishtrap> acaciaFishtrap;
    // darkOakFishtrap
    // birchFishtrap
    // jungleFishtrap
    // spruceFishtrap

    static {
        oakFishtrap = BLOCKS.register(
                UnlocalizedName.FISHTRAP_OAK,
                () -> new BlockFishtrap()
        );
        acaciaFishtrap = BLOCKS.register(
                UnlocalizedName.FISHTRAP_ACACIA,
                () -> new BlockFishtrap()
        );
    }

    private GrowthcraftTrapperBlocks() { /* Prevent default public constructor */ }

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
