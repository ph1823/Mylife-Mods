package fr.ph1823.mylife.utility;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.events.ScreenListener;
import fr.ph1823.mylife.gui.GuiNewCharacter;
import fr.ph1823.mylife.network.phone.SMSListMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(modid = MyLifeMod.MODID, value = Side.CLIENT)
public class MylifeKeyBindings {
    private static final String CATEGORY = "category.mylife";
    public static final KeyBinding EXAMPLE_KEY = new KeyBinding("key.dev", Keyboard.KEY_NUMPAD1, CATEGORY);
    public static final KeyBinding PHONE_UP = new KeyBinding("key.phone_up", Keyboard.KEY_UP, CATEGORY);
    public static final KeyBinding PHONE_DOWN = new KeyBinding("key.phone_down", Keyboard.KEY_DOWN, CATEGORY);
    public static final KeyBinding PHONE_RIGHT = new KeyBinding("key.phone_right", Keyboard.KEY_RIGHT, CATEGORY);
    public static final KeyBinding PHONE_LEFT = new KeyBinding("key.phone_left", Keyboard.KEY_LEFT, CATEGORY);

    public static final KeyBinding PHONE_SELECT = new KeyBinding("key.phone_select_app", Keyboard.KEY_RETURN, CATEGORY);
    private long lastPressPhone = System.currentTimeMillis() - 10000;

    public static int num = -1;
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (EXAMPLE_KEY.isPressed()) {
            // Perform action when key is pressed
            Minecraft.getMinecraft().displayGuiScreen(new GuiNewCharacter());
        }

        if(ScreenListener.phoneOpen && System.currentTimeMillis() >= lastPressPhone + 100) {
            lastPressPhone = System.currentTimeMillis();
            if (PHONE_UP.isKeyDown()) ScreenListener.pos.up();
            if (PHONE_DOWN.isKeyDown()) ScreenListener.pos.down();
            if (PHONE_LEFT.isKeyDown()) ScreenListener.pos.left();
            if (PHONE_RIGHT.isKeyDown()) ScreenListener.pos.right();
            if (PHONE_SELECT.isKeyDown()) {
                //Prmeière ligne du téléphone
                if(ScreenListener.pos.getY() == 1) {
                    if(ScreenListener.pos.getX() == 1) MyLifeMod.MYIFE_NETWORK.sendToServer(new SMSListMessage(num));
                    else if(ScreenListener.pos.getX() == 2) Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Nos meilleur scientifique sont sur le coup pour vous faire écoutez toutes la musique sur un seul endroit !"));
                    else if(ScreenListener.pos.getX() == 3) Minecraft.getMinecraft().player.sendMessage(new TextComponentString("L'application prise de note va bientôt s'ouvrir"));
                }
            }
        }
    }
}