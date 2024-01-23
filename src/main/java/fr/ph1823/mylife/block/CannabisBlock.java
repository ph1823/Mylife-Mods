package fr.ph1823.mylife.block;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import org.jetbrains.annotations.NotNull;

public class CannabisBlock extends CropBlock {
    protected CannabisBlock(Properties builder) {
        super(builder);
    }

    @Override
    public int getMaxAge() {
        return 3; // Set the maximum age to 3 for three growth stages
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return Items.WHEAT_SEEDS; // Use wheat seeds as the seed item for your crop
    }
}