package growthcraft.trapper.init.client;

import growthcraft.trapper.client.gui.ScreenFishtrap;
import growthcraft.trapper.init.GrowthcraftTrapperContainers;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrowthcraftTrapperScreenManager {
    private GrowthcraftTrapperScreenManager() { /* Disable Default Public Constructor */ }

    public static void registerFactories() {
        ScreenManager.registerFactory(GrowthcraftTrapperContainers.fishtrapContainer.get(), ScreenFishtrap::new);
    }
}
