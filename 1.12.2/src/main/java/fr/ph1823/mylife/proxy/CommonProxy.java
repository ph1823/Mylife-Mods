package fr.ph1823.mylife.proxy;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.capability.ProfileCapability;
import fr.ph1823.mylife.events.CapabilityListener;
import fr.ph1823.mylife.events.RegistryEventListener;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;

public class CommonProxy {

    public void registerItemRenderer(Item item, int meta, String id) {}
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(new MyLifeMod());
        MinecraftForge.EVENT_BUS.register(new RegistryEventListener());
        MinecraftForge.EVENT_BUS.register(new CapabilityListener());

        ProfileCapability.register();
    }

    public void init() {
        //Register permissions
        PermissionAPI.registerNode("mylife.admin", DefaultPermissionLevel.OP, "Permissions grant for admin");
        PermissionAPI.registerNode("mylife.mod", DefaultPermissionLevel.OP, "Permissions grant for moderator");
    }
}
