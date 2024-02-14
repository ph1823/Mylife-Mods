package fr.ph1823.mylife.proxy;

import fr.ph1823.mylife.events.RegistryEventListener;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

    public void registerItemRenderer(Item item, int meta, String id) {}
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new RegistryEventListener());
    }
}
