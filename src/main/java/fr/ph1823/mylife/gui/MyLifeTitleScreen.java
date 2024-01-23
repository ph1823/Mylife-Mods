package fr.ph1823.mylife.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemNameBlockItem;

public class MyLifeTitleScreen extends TitleScreen {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("mylife", "textures/gui/background.png");


    @Override
    public void render(PoseStack matrixStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(matrixStack, pMouseX, pMouseY, pPartialTick);
        // Bind the texture
        Minecraft.getInstance().getTextureManager().bindForSetup(BACKGROUND_TEXTURE);

        // Draw the texture
        blit(matrixStack, 0,0, 0, 0, this.width, this.height);
    }


}
