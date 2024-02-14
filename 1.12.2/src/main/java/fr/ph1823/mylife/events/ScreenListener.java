package fr.ph1823.mylife.events;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.gui.MyLifeMainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT )
public class ScreenListener {
    private final ResourceLocation LOGO_TEXTURE = new ResourceLocation(MyLifeMod.MODID, "textures/gui/logo.png");

    @SubscribeEvent
    public static void onGuiOpen(GuiOpenEvent event) {

        if(event.getGui() != null && event.getGui().getClass() == GuiMainMenu.class)
            event.setGui(new MyLifeMainMenu());
    }

    @SubscribeEvent
    public void onGameRnender(RenderGameOverlayEvent.Post event) {
        if(event.getType().equals(RenderGameOverlayEvent.ElementType.HOTBAR)) {
            Minecraft mc = Minecraft.getMinecraft();
            //On réinitialise la couleur
            GlStateManager.enableAlpha();
            //On dessine ce que l'on souhaite
            mc.getTextureManager().bindTexture(LOGO_TEXTURE);
            Gui.drawModalRectWithCustomSizedTexture(4, 0,0,0, 16, 16, 16, 16);
            mc.fontRenderer.drawString("6.0.0" , 2, 18,  869646);
            GlStateManager.disableAlpha();
        }
    }

}
