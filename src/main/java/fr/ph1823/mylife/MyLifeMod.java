package fr.ph1823.mylife;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import fr.ph1823.mylife.events.ScreenListener;
import fr.ph1823.mylife.items.JoinItem;
import fr.ph1823.mylife.items.drink.BeerItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BottleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.lwjgl.system.CallbackI;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("mylife")
public class MyLifeMod
{
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "mylife");
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "mylife");
    public static final RegistryObject<Item> CHOPE_ITEM = ITEMS.register("chope", () -> new BottleItem(new Item.Properties()));
    public static final RegistryObject<Item> BEER_ITEM = ITEMS.register("beer", () -> new BeerItem((new Item.Properties())));

    private static final ResourceLocation LOGO_TEXTURE = new ResourceLocation("mylife", "textures/gui/logo.png");

    public MyLifeMod()
    {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ScreenListener());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        IIngameOverlay logoOverlay = OverlayRegistry.registerOverlayTop("mylife_logo", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
            PoseStack poseStack = new PoseStack();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, LOGO_TEXTURE);

            gui.blit(poseStack, 0, 0,0,0, 16, 16, 16, 16);
            gui.getFont().draw(poseStack, "6.0.0" , 16, 32,  869646);
        });

        OverlayRegistry.enableOverlay(logoOverlay, true);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
        {
            // Register a new block here
            LOGGER.info("HELLO from Register Block");
        }

        @SubscribeEvent
        public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegistryEvent)
        {
            // Register a new item here
            itemRegistryEvent.getRegistry().register(new JoinItem((new Item.Properties())));
            //new PotionItem()
        }
    }

}
