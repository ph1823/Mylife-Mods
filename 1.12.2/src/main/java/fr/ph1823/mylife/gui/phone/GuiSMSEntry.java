package fr.ph1823.mylife.gui.phone;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GuiSMSEntry {

    private final FontRenderer fontRender;
    private final String num;
    private String sms;
    private final Date date;
    private final SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM");
    private final SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm");
    private final boolean limit;

    public GuiSMSEntry(String sms, Date date, boolean limit, String num) {
        this.fontRender = Minecraft.getMinecraft().fontRenderer;
        this.sms = sms;
        this.date = date;
        this.limit = limit;
        this.num = num;

        if(limit && this.sms.length() > 24)
            this.sms = this.sms.substring(0, 24);
    }

    public void drawEntry(int x, int y, int i ) {
        // ouverture de la matrix opengl
        GL11.glPushMatrix();
        // On est dans la liste des SMS pour tout les num, on affiche le num
        if(limit) {
            GL11.glScalef(0.8F, 0.8F, 1F);
            this.fontRender.drawString(this.num, x, y + i * 8, 16777215);
            GL11.glScalef(1.8F, 1.8F, 1F);
        }
        GL11.glScalef(0.6F, 0.6F, 1F);
        this.fontRender.drawSplitString(this.sms, x, y + this.fontRender.FONT_HEIGHT + i * 8 + 2, this.fontRender.getStringWidth("a") * 24, 16777215);

        // get format use in sms date
        // Create a Calendar instance and set it to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.date);
        // Add one day to the current date
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date oneDayLater = calendar.getTime();
        SimpleDateFormat format = this.hourFormat;
        if(this.date.after(oneDayLater)) format = this.dayFormat;

        //Reset scale and postion
        GL11.glScalef(1.6F, 1.6F, 1.6F);
        GL11.glScalef(0.5F, 0.5F, 1F);
        GL11.glTranslatef(0.0F, 2F, 0.0F);
        this.fontRender.drawString(format.format(this.date), ((x + this.fontRender.getStringWidth("a") * 16) * 2),  (y * 2) + i-8,16777215);
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }
}
