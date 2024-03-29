package fr.ph1823.mylife.events;

import com.mojang.logging.LogUtils;
import fr.ph1823.mylife.ExampleMod;
import fr.ph1823.mylife.gui.MyLifeTitleScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ScreenListener {
    private static final Logger LOGGER = LogUtils.getLogger();
   @SubscribeEvent
    public void onOpenScreen(net.minecraftforge.client.event.ScreenOpenEvent event)
    {
        if(event.getScreen() != null && event.getScreen().getClass() == TitleScreen.class)
        {
            event.setScreen(new MyLifeTitleScreen());
        }
    }
}
