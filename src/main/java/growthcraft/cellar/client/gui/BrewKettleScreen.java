package growthcraft.cellar.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import growthcraft.cellar.client.container.BrewKettleContainer;
import growthcraft.cellar.shared.Reference;
import growthcraft.cellar.shared.UnlocalizedName;
import growthcraft.lib.utils.ClientUtils;
import growthcraft.lib.utils.TextureHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

public class BrewKettleScreen extends ContainerScreen<BrewKettleContainer> {

    private static final ResourceLocation TEXTURE = TextureHelper.getTextureGui(Reference.MODID, UnlocalizedName.BREW_KETTLE);

    public BrewKettleScreen(BrewKettleContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);

        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        MatrixStack matrixStack = new MatrixStack();
        matrixStack.push();
        IRenderTypeBuffer.Impl renderTypeBuffer = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        // Copy the image from the texture to the background layer
        int baseX = 0;
        int baseY = 0;
        int maxX = 256;
        int maxY = 256;

        blit(this.guiLeft, this.guiTop, baseX, baseY, this.xSize, this.ySize, maxX, maxY);

        // Progress bar
        int guiProgressX = this.guiLeft + 98;
        int guiProgressY = this.guiTop + 30;

        this.blit(guiProgressX, guiProgressY, 176, 0, 9, this.container.getSmeltProgressionScaled(28));

        // Heated
        int guiHeatLevelX = this.guiLeft + 68;
        int guiHeatLevelY = this.guiTop + 53;

        if (this.container.isBurning()) {
            this.blit(guiHeatLevelX, guiHeatLevelY, 176, 28, 13, 13);
        }

        // InputFluidTank Render
        int guiInputTankX = guiLeft + 46;
        int guiInputTankY = guiTop + 17;
        int guiInputTankHeight = 52;

        if (this.container.getInputFluidTank().getFluidAmount() > 0) {
            FluidStack fluidStack = this.container.getInputFluidTank().getFluid();

            int scaledFluidH = getScaledFluid(fluidStack.getAmount(), this.container.getInputFluidTank().getCapacity(), guiInputTankHeight);
            ClientUtils.drawRepeatedFluidSpriteGui(renderTypeBuffer, matrixStack, fluidStack,
                    guiInputTankX, guiInputTankY + (guiInputTankHeight - scaledFluidH), 16, scaledFluidH);
        }

        renderTypeBuffer.finish();
    }

    private static TextureAtlasSprite getStillFluidSprite(FluidStack fluidStack) {
        Minecraft minecraft = Minecraft.getInstance();
        Fluid fluid = fluidStack.getFluid();
        FluidAttributes attributes = fluid.getAttributes();
        ResourceLocation fluidStill = attributes.getStillTexture(fluidStack);
        return minecraft.getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(fluidStill);
    }

    private int getScaledFluid(float amount, float capacity, int maxPixelSize) {
        float ratio = amount / capacity;
        float scaled = maxPixelSize * ratio;

        return (int) scaled;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        this.font.drawString(this.title.getFormattedText(), 8.0F, 6.0F, 0x404040);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, 73.0F, 0x404040);

    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        // background before super
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        // text after super
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
