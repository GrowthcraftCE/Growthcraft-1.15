package growthcraft.trapper.init;

import growthcraft.lib.common.block.GrowthcraftFishtrapBlock;
import growthcraft.trapper.common.block.BlockAcaciaFishtrap;
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
    public static final RegistryObject<GrowthcraftFishtrapBlock> oakFishtrap;
    public static final RegistryObject<BlockAcaciaFishtrap> acaciaFishtrap;
    // darkOakFishtrap
    // birchFishtrap
    // jungleFishtrap
    // spruceFishtrap

    static {
        oakFishtrap = BLOCKS.register(
                UnlocalizedName.FISHTRAP_OAK,
                () -> new GrowthcraftFishtrapBlock()
        );
        acaciaFishtrap = BLOCKS.register(
                UnlocalizedName.FISHTRAP_ACACIA,
                () -> new BlockAcaciaFishtrap()
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
