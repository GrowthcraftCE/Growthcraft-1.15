package growthcraft.bamboo.client.proxy;

import growthcraft.bamboo.init.GrowthcraftBambooBlocks;
import growthcraft.lib.proxy.IProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {
    @Override
    public void init() {
        // Set client side initializations.
        getRenderedLayers();
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return null;
    }

    private void getRenderedLayers() {
        RenderTypeLookup.setRenderLayer(GrowthcraftBambooBlocks.bambooPlankDoor.get(),
                RenderType.getCutout());
    }

}
