package growthcraft.core;

import growthcraft.bamboo.init.GrowthcraftBambooBlocks;
import growthcraft.core.client.proxy.ClientProxy;
import growthcraft.core.common.proxy.CommonProxy;
import growthcraft.core.init.GrowthcraftBlocks;
import growthcraft.core.init.GrowthcraftItems;
import growthcraft.core.init.config.GrowthcraftConfig;
import growthcraft.core.shared.Reference;
import growthcraft.lib.proxy.IProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod("growthcraft")
public class Growthcraft {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final ItemGroup itemGroup = new ItemGroup(Reference.MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(GrowthcraftBambooBlocks.bambooPlank.get());
        }
    };
    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public Growthcraft() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        GrowthcraftConfig.loadConfig();

        // Mod event bus context for deferred registries
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Add DeferredRegister<Item> to the mod event bus.
        GrowthcraftItems.ITEMS.register(modEventBus);

        // Add DeferredRegister<Block> to the mod event bus.

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("growthcraft", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> blockRegistry = event.getRegistry();

            GrowthcraftBlocks.registerBlocks(blockRegistry);
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> itemRegistry = event.getRegistry();
            final Item.Properties properties = new Item.Properties().group(itemGroup);
            GrowthcraftBlocks.registerBlockItems(itemRegistry, properties);

        }
    }
}
