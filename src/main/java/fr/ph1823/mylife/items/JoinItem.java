package fr.ph1823.mylife.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class JoinItem extends Item {
    public JoinItem(Properties properties) {
        super(properties);
        setRegistryName("mylife", "joint");
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        player.playSound(new SoundEvent(new ResourceLocation("mylife", "smoke")), 1f, 1f);
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2));
        player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 4));
        itemStack.shrink(1);
        //player.getItemInHand(hand).setCount(player.getItemInHand(hand).getCount() - 1);
        return InteractionResultHolder.consume(itemStack);
    }

}
