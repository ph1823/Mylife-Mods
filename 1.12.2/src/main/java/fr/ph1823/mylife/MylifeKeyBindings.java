package fr.ph1823.mylife;

import fr.ph1823.mylife.gui.GuiNewCharacter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(modid = MyLifeMod.MODID, value = Side.CLIENT)
public class MylifeKeyBindings {
    private static final String CATEGORY = "category.mylife";
    public static final KeyBinding EXAMPLE_KEY = new KeyBinding("key.dev", Keyboard.KEY_1, CATEGORY);

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (EXAMPLE_KEY.isPressed()) {
            // Perform action when key is pressed
            Minecraft.getMinecraft().displayGuiScreen(new GuiNewCharacter());
        }
    }
}