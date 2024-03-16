package fr.ph1823.mylife.gui.phone;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.utility.SMS;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiSMS extends GuiScreen {
    private final List<GuiSMSEntry> smsList = new ArrayList<>();

    public GuiSMS(HashMap<String, List<SMS>> smsList) {
       for(Map.Entry<String, List<SMS>> sms: smsList.entrySet())
            this.smsList.add(new GuiSMSEntry(sms.getValue().get(0).getMessage(), sms.getValue().get(0).getDate(), true, sms.getKey()));
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.buttonList.clear();

        for(int i = 0; i < smsList.size(); ++i) {
            GuiSMSEntry smsEntry = smsList.get(i);
            smsEntry.drawEntry(16, 16 + 20 * i, i);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        int ration = this.mc.displayWidth / this.width;
        if(mouseY * ration >= 26) {
            int index = (mouseY * ration) / 26;
            MyLifeMod.LOGGER.info("Clicked on sms");
            MyLifeMod.LOGGER.info("index: " + index);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void drawBackground(int tint) {
    }
}
