package fr.ph1823.mylife.proxy;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.capability.ProfileCapability;
import fr.ph1823.mylife.events.CapabilityListener;
import fr.ph1823.mylife.events.RegistryEventListener;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

    public void registerItemRenderer(Item item, int meta, String id) {}
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(new MyLifeMod());
        MinecraftForge.EVENT_BUS.register(new RegistryEventListener());
        MinecraftForge.EVENT_BUS.register(new CapabilityListener());

        ProfileCapability.register();
    }
}
