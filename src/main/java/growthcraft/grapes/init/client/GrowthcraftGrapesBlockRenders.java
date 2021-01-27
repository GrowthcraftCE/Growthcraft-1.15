package growthcraft.grapes.init.client;

import growthcraft.grapes.init.GrowthcraftGrapesBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class GrowthcraftGrapesBlockRenders {

    private GrowthcraftGrapesBlockRenders() { /* Disable default public constructor */ }

    public static void setRenderLayers() {
        RenderTypeLookup.setRenderLayer(GrowthcraftGrapesBlocks.GRAPE_VINE_PURPLE_FRUIT.get(),
                RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftGrapesBlocks.GRAPE_VINE_PURPLE_LEAVES.get(),
                RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GrowthcraftGrapesBlocks.GRAPE_VINE_PURPLE.get(),
                RenderType.getCutout());
    }

}
