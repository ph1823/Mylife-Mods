package fr.ph1823.mylife.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.ItemNameBlockItem;

public class MyLifeTitleScreen extends TitleScreen {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("mylife", "textures/gui/background.png");


    public void render(PoseStack poseStack, int mouseX, int mouseY, float p_96742_) {

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);


        // Draw the texture
        blit(poseStack, 0, 0, 0, 0, 1920, 1017, this.width, this.height);
            /*String s = "Minecraft " + SharedConstants.getCurrentVersion().getName() + " - MyLife";

            if (Minecraft.checkModStatus().shouldReportAsModified()) {
                s = s + I18n.get("menu.modded");
            }*/

            net.minecraftforge.internal.BrandingControl.forEachLine(true, true, (brdline, brd) ->
                    drawString(poseStack, this.font, brd, 2, this.height - ( 10 + brdline * (this.font.lineHeight + 1)), 16777215 )
            );

            net.minecraftforge.internal.BrandingControl.forEachAboveCopyrightLine((brdline, brd) ->
                    drawString(poseStack, this.font, brd, this.width - font.width(brd), this.height - (10 + (brdline + 1) * ( this.font.lineHeight + 1)), 16777215)
            );

            for(GuiEventListener guieventlistener : this.children()) {
                if (guieventlistener instanceof AbstractWidget) {
                    ((AbstractWidget)guieventlistener).setAlpha(1f);
                }
            }

            for(Widget widget : this.renderables) {
                widget.render(poseStack, mouseX, mouseY, p_96742_);
            }()

    }


}
