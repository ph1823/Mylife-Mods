package fr.ph1823.mylife.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOverlayDebug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(GuiOverlayDebug.class)
public class MixinGuiDebug {
    // This method will be called by the original getDebugInfoRight method
    @Inject(method = "call", at = @At("RETURN"), cancellable = true)
    private void addCustomInfo(CallbackInfoReturnable<List<String>> cir) {
        // Get the original debug info
        List<String> originalList = cir.getReturnValue();

        // Add your custom info
        String customInfo = "\u00A7c\u00A7lMoney: \u00A7b0";

        // Combine the original and custom info
        originalList.add("");
        originalList.add(customInfo);
        originalList.subList(1, 14).clear();
        originalList.add(1, "FPS: " + Minecraft.getDebugFPS());
        originalList.removeIf(str -> str.contains("Looking"));
        // Set the return value to the combined info
        cir.setReturnValue(originalList);
    }
}
