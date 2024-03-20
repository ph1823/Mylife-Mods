package fr.ph1823.mylife.gui.phone;

import fr.ph1823.mylife.utility.phone.SMS;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;

public class GuiSMS extends GuiScreen {

    private List<SMS> smsSendList = new ArrayList<>();
    public GuiSMS() {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
