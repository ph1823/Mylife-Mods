package fr.ph1823.mylife.events;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.PairInt;
import fr.ph1823.mylife.gui.MyLifeMainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.server.permission.PermissionAPI;

@Mod.EventBusSubscriber(value = Side.CLIENT )
public class ScreenListener {
    private final ResourceLocation LOGO_TEXTURE = new ResourceLocation(MyLifeMod.MODID, "textures/gui/logo.png");
    private final ResourceLocation PHONE_TEXTURE = new ResourceLocation(MyLifeMod.MODID, "textures/gui/phone.png");

    public static boolean phoneOpen = false;
    public static PairInt pos = new PairInt(1, 1);

    @SubscribeEvent
    public static void onGuiOpen(GuiOpenEvent event) {
        if(event.getGui() != null && event.getGui().getClass() == GuiMainMenu.class)
            event.setGui(new MyLifeMainMenu());
    }

    @SubscribeEvent
    public void onGameRnender(RenderGameOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getMinecraft();
        if(event.getType().equals(RenderGameOverlayEvent.ElementType.ALL)) {
            //On réinitialise la couleur
            GlStateManager.enableAlpha();
            GlStateManager.color(1.0F,  1.0F,  1.0F,  1.0F);
            //On dessine ce que l'on souhaite
            mc.getTextureManager().bindTexture(LOGO_TEXTURE);
            Gui.drawModalRectWithCustomSizedTexture(4, 0,0,0, 16, 16, 16, 16);
            mc.fontRenderer.drawString("6.0.0" , 2, 18,  869646);

            if(PermissionAPI.hasPermission(mc.player, "mylife.admin")) {
                mc.fontRenderer.drawString("Admin mode: Disable", 2, 18 + mc.fontRenderer.FONT_HEIGHT + 2, 869646);
            }


            if(phoneOpen) {
                //x 8 y 20
                GlStateManager.color(1.0F,  1.0F,  1.0F,  1.0F);

                //Draw phone texture
                mc.getTextureManager().bindTexture(PHONE_TEXTURE);
                ScaledResolution scaledresolution = new ScaledResolution(mc);
                int width = scaledresolution.getScaledWidth();
                int height = scaledresolution.getScaledHeight();
                int ratio = 2;
                Gui.drawModalRectWithCustomSizedTexture(width - 64, height - 128, 0, 0, 120/ratio, 256/ratio, 120, 256/ratio);

                //Draw select app texture
                mc.getTextureManager().bindTexture(PHONE_TEXTURE);
                mc.ingameGUI.drawTexturedModalRect((width - 64) + (pos.getX() * 4) + ((pos.getX() - 1) * 12), (height - 128) + (pos.getY() * 12) + (pos.getY() - 1) * 6, 256-16, 0, 16, 16);
            }

            GlStateManager.disableAlpha();
        }
    }

}
