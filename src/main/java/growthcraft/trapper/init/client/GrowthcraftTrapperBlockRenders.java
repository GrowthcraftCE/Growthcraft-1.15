package growthcraft.trapper.init.client;

import growthcraft.trapper.init.GrowthcraftTrapperBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class GrowthcraftTrapperBlockRenders {

    private GrowthcraftTrapperBlockRenders() { /* Disable default public constructor */ }

    public static void setRenderLayers() {
        RenderTypeLookup.setRenderLayer(GrowthcraftTrapperBlocks.oakFishtrap.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftTrapperBlocks.acaciaFishtrap.get(), RenderType.getCutout());
    }

}
