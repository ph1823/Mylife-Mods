package fr.ph1823.mylife.gui;

import fr.ph1823.mylife.capability.IProfile;
import fr.ph1823.mylife.capability.ProfileCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

public class GuiDebugMyLife extends GuiOverlayDebug {

    public GuiDebugMyLife(Minecraft mc) {
        super(mc);
    }

    @Override
    protected <T extends Comparable<T>> List<String> getDebugInfoRight() {
        List<String> debugs = super.getDebugInfoRight();
        EntityPlayer player = Minecraft.getMinecraft().player;
        if(player.hasCapability(ProfileCapability.PROFILE_CAPABILITY, null)) {
            IProfile profile = player.getCapability(ProfileCapability.PROFILE_CAPABILITY, null);
            // Create a new text component with the message
           /* TextComponentString textComponent = new TextComponentString("Money: §e§l" + profile.getMoney());
            // Apply formatting codes to the message
            textComponent.getStyle().setBold(true);
            //mc.fontRenderer.drawString(textComponent.getText(), mc.displayWidth - mc.fontRenderer.getStringWidth(textComponent.getText()), 18, 869646);
            mc.fontRenderer.drawString("prout", mc.displayWidth - 100, mc.displayHeight - mc.fontRenderer.FONT_HEIGHT * 2, 869646);*/
            debugs.add("Money: ");
        } else {
            //mc.fontRenderer.drawString("§4§lErrror: no player capacibility", mc.displayWidth - mc.fontRenderer.getStringWidth("§4§lErrror: no player capacibility"), 18, 869646);
        }
        return new ArrayList<>();
    }
}
