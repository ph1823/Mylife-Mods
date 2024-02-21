package fr.ph1823.mylife;

import fr.ph1823.mylife.network.MoneyMessage;
import fr.ph1823.mylife.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(modid = MyLifeMod.MODID, name = MyLifeMod.NAME, version = MyLifeMod.VERSION)
public class MyLifeMod
{
    public static final String MODID = "mylife";
    public static final String NAME = "MyLife";
    public static final String VERSION = "6.0.0";

    public static final String SERVER_IP = "45.154.96.24:25565";//"127.0.0.1:25565"

    @SidedProxy(clientSide = "fr.ph1823.mylife.proxy.ClientProxy", serverSide = "fr.ph1823.mylife.proxy.ServerProxy")
    public static CommonProxy proxy;
    public static Logger LOGGER;

    public static SimpleNetworkWrapper MYIFE_NETWORK;

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void onConstruction(FMLConstructionEvent event) {
        Display.setTitle("Mylife - Minecraft 1.12.2");

        ByteBuffer[] icons = new ByteBuffer[4];

        icons[0] = loadIcon("/assets/mylife/textures/gui/logo-16x16.png");
        icons[1] = loadIcon("/assets/mylife/textures/gui/logo-32x32.png");
        icons[2] = loadIcon("/assets/mylife/textures/gui/logo-64x64.png");
        icons[3] = loadIcon("/assets/mylife/textures/gui/logo.png");

        Display.setIcon(icons);

    }
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MyLifeMod.LOGGER = event.getModLog();
        MyLifeMod.LOGGER.info("Pre-init event");
        MyLifeMod.proxy.preInit();

        //Network register
        MYIFE_NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("MyLife");
        MYIFE_NETWORK.registerMessage(MoneyMessage.Handler.class, MoneyMessage.class, 0, Side.SERVER);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MyLifeMod.proxy.init();
    }

    public ByteBuffer loadIcon(String path) {
        try {
            BufferedImage originalImage = ImageIO.read(getClass().getResourceAsStream(path));

            // Get the DataBuffer from the original image
            DataBuffer buffer = originalImage.getRaster().getDataBuffer();

            // Check if the buffer is of type DataBufferInt
            if (buffer instanceof DataBufferInt) {
                // Convert the DataBufferInt to a ByteBuffer
                DataBufferInt dataBufferInt = (DataBufferInt) buffer;
                int[] data = dataBufferInt.getData();
                ByteBuffer imageBuffer = ByteBuffer.allocateDirect(data.length * 4); // Assuming  4 bytes per pixel
                imageBuffer.order(ByteOrder.nativeOrder());

                for (int value : data) {
                    imageBuffer.putInt(value);
                }

                imageBuffer.flip();
                return imageBuffer;
            } else if (buffer instanceof DataBufferByte) {
                // Convert the DataBufferByte to a ByteBuffer
                DataBufferByte dataBufferByte = (DataBufferByte) buffer;
                byte[] data = dataBufferByte.getData();
                ByteBuffer imageBuffer = ByteBuffer.allocateDirect(data.length);
                imageBuffer.order(ByteOrder.nativeOrder());
                imageBuffer.put(data, 0, data.length);
                imageBuffer.flip();
                return imageBuffer;
            } else {
                throw new UnsupportedOperationException("Unsupported DataBuffer type: " + buffer.getClass().getName());
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unsupported");
        }
    }


}
