package growthcraft.apples.client.render;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class GrowthcraftApplesBlockRenders {

    private GrowthcraftApplesBlockRenders() { /* Disable default public constructor */ }

    public static void setRenderLayer() {
        RenderTypeLookup.setRenderLayer(GrowthcraftApplesBlocks.applePlankTrapdoor.get(), RenderType.getCutout());
    }
}
