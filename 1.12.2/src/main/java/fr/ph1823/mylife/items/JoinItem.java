package fr.ph1823.mylife.items;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JoinItem extends Item {
    public JoinItem() {
        super();
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemStack = player.getHeldItem(hand);
        player.playSound(new SoundEvent(new ResourceLocation("mylife", "smoke")), 1f, 1f);
        //player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20*60*2, 1));
        //player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 20*60*4,3));
        itemStack.shrink(1);
        //player.getItemInHand(hand).setCount(player.getItemInHand(hand).getCount() - 1);
        return EnumActionResult.SUCCESS;
    }

}
