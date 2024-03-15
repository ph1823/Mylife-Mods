package fr.ph1823.mylife.gui.phone;

import fr.ph1823.mylife.MyLifeMod;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuiSMS extends GuiScreen {

    private final String num;
    private List<GuiSMSEntry> smsList = new ArrayList<>();
    //private HashMap<String, List<SMS>> smsList;

    public GuiSMS(String num) {
        this.num = num;
        this.smsList.add(new GuiSMSEntry("Ceci est un très long message de plus de 24 charactères.",  new Date(), true));
        this.smsList.add(new GuiSMSEntry("test",  new Date(), true));
        //
        //smsList = data.getDataFromPhone(this.num);
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.buttonList.clear();
        //for (String sender: smsList.keySet())

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
