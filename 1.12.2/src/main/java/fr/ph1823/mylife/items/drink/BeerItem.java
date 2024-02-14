package fr.ph1823.mylife.items.drink;

import net.minecraft.item.ItemPotion;

public class BeerItem extends ItemPotion {
    public BeerItem() {
        super();
    }

    /*public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        Player player = livingEntity instanceof Player ? (Player)livingEntity : null;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, itemStack);
        }

        if (!level.isClientSide) {
            //apply effect
        }

        //if its a player
        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
                player.getInventory().add(new ItemStack(MyLifeItems.CHOPE_ITEM.get()));
            }
        }

        if (player == null && itemStack.isEmpty())
            return new ItemStack(Items.GLASS_BOTTLE);

        level.gameEvent(livingEntity, GameEvent.DRINKING_FINISH, livingEntity.eyeBlockPosition());
        return itemStack;
    }*/
}
