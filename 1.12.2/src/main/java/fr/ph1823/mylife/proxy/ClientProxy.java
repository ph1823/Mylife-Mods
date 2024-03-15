package fr.ph1823.mylife.proxy;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.utility.MylifeKeyBindings;
import fr.ph1823.mylife.events.ScreenListener;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy{

    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(MyLifeMod.MODID + ":" + id, "inventory"));
    }

    public void preInit() {

        //call to super instance and register obj dmomain (for 3d)
        super.preInit();
        OBJLoader.INSTANCE.addDomain(MyLifeMod.MODID);

        //register event
        MinecraftForge.EVENT_BUS.register(new ScreenListener());
        MinecraftForge.EVENT_BUS.register(new MylifeKeyBindings());

        //Register key
        ClientRegistry.registerKeyBinding(MylifeKeyBindings.EXAMPLE_KEY);
        ClientRegistry.registerKeyBinding(MylifeKeyBindings.PHONE_DOWN);
        ClientRegistry.registerKeyBinding(MylifeKeyBindings.PHONE_UP);
        ClientRegistry.registerKeyBinding(MylifeKeyBindings.PHONE_RIGHT);
        ClientRegistry.registerKeyBinding(MylifeKeyBindings.PHONE_LEFT);
        ClientRegistry.registerKeyBinding(MylifeKeyBindings.PHONE_SELECT);
    }
}