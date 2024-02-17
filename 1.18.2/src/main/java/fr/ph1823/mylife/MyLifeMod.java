package fr.ph1823.mylife;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import fr.ph1823.mylife.events.ScreenListener;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("mylife")
public class MyLifeMod
{
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final ResourceLocation LOGO_TEXTURE = new ResourceLocation("mylife", "textures/gui/logo.png");
    public MyLifeMod()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ScreenListener());
        MyLifeBlocks.register(eventBus);
        MyLifeItems.register(eventBus);

        IIngameOverlay logoOverlay = OverlayRegistry.registerOverlayTop("mylife_logo", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
            PoseStack poseStack = new PoseStack();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, LOGO_TEXTURE);

            GuiComponent.blit(poseStack, 4, 0,0,0, 16, 16, 16, 16);
            gui.getFont().draw(poseStack, "6.0.0" , 2, 18,  869646);
        });

        OverlayRegistry.enableOverlay(logoOverlay, true);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("Cannabis >> {}", MyLifeBlocks.CANNABIS_BLOCK.get().getRegistryName());
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Start change render");
        ItemBlockRenderTypes.setRenderLayer(MyLifeBlocks.CANNABIS_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MyLifeBlocks.HOUBLON_BLOCK.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(MyLifeBlocks.ATM_BLOCK.get(), RenderType.cutoutMipped());
    }

}
