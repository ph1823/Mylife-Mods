package fr.ph1823.mylife.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class GuiNewCharacter extends GuiScreen {
    private GuiTextField firstName;
    private int rationX = 8, rationY = 8;

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        firstName = new GuiTextField(1, this.fontRenderer, this.getStartX()  + 6, this.getStartY() + this.fontRenderer.FONT_HEIGHT + 6, 50, 25/2);
    }

    @Override
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void updateScreen()
    {
        this.firstName.updateCursorCounter();
    }

    //no slot:  no register in server
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawInput();
        this.drawString(this.fontRenderer, I18n.format("gui.firstname"), this.getStartX() + 6, this.getStartY() + 6, 0x696969);
        drawEntityOnScreen((this.getStartX() * (this.rationX - 1)) - 48, (this.getStartY() * (this.rationY - 1)) - 24, 60, (this.getStartX() * (this.rationX - 1)) - 48 - mouseX, ((this.getStartY() * (this.rationY - 1)) - 24) - 50 - mouseY, this.mc.player);
        //Minecraft.getMinecraft().entityRenderer.
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawInput() {
        firstName.drawTextBox();
    }

    @Override
    public void drawDefaultBackground() {
        drawRect(getStartX(), getStartY(), getStartX() * (this.rationX - 1)  , getStartY() * (this.rationY - 1), 0x61b8b8b8);
        //drawRect(this.width/4, this.height/4, (this.width * 3) / 4, (this.height * 3)/4, 13619151);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.firstName.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void keyTyped(char typedChar, int keyCode) throws IOException
    {
        this.firstName.textboxKeyTyped(typedChar, keyCode);
    }

    public int getStartX() {
        return this.width / this.rationX;
    }

    public int getStartY() {
        return this.height / this.rationY;
    }

    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
        ent.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
        ent.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}
