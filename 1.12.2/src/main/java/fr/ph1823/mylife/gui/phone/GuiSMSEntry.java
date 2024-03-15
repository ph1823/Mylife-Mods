package fr.ph1823.mylife.gui.phone;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.lwjgl.opengl.GL11.*;

public class GuiSMSEntry {

    private final FontRenderer fontRender;
    private String sms;
    private Date date;
    private SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM");
    private SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm");

    public GuiSMSEntry(String sms, Date date, boolean limit) {
        this.fontRender = Minecraft.getMinecraft().fontRenderer;
        this.sms = sms;
        this.date = date;

        if(limit && this.sms.length() > 24)
            this.sms = this.sms.substring(0, 24);
    }

    public void drawEntry(int x, int y, int i ) {
        //
        GL11.glPushMatrix();
        GL11.glScalef(0.8F, 0.8F, 1F);
        this.fontRender.drawString("0000000", x, y + i * 8, 16777215);

        GL11.glScalef(1.8F, 1.8F, 1F);
        GL11.glScalef(0.6F, 0.6F, 1F);
        this.fontRender.drawSplitString(this.sms, x, y + this.fontRender.FONT_HEIGHT + i * 8 + 2, this.fontRender.getStringWidth("a") * 24, 16777215);

        //Reset scale and postion
        GL11.glScalef(1.6F, 1.6F, 1.6F);
        GL11.glScalef(0.5F, 0.5F, 1F);
        GL11.glTranslatef(0.0F, 2F, 0.0F);
        this.fontRender.drawString(hourFormat.format(this.date), ((x + this.fontRender.getStringWidth("a") * 16) * 2),  (y * 2) + i-8,16777215);
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }
}
