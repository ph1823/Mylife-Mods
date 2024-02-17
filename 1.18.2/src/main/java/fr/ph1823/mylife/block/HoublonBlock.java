package fr.ph1823.mylife.block;

import fr.ph1823.mylife.MyLifeBlocks;
import fr.ph1823.mylife.MyLifeItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class HoublonBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    private int ticks = 0;

    public HoublonBlock(Properties builder) {
        super(builder);
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_57040_) {
        p_57040_.add(AGE);
    }
    @Override
    public int getMaxAge() {
        return 2; // Set the maximum age to 3 for three growth stages
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return MyLifeItems.HOUBLON_SEED.get(); // Use wheat seeds as the seed item for your crop
    }


    public void randomTick(@NotNull BlockState blockState, ServerLevel serverLevel, @NotNull BlockPos blockPos, @NotNull Random Rand) {
        if (!serverLevel.isLoaded(blockPos)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light

        if (this.ticks > 20*60) {
            //if(serverLevel.getRawBrightness(blockPos, 0) >= 9);
            System.out.println("grow");
            //parWorld.func_72921_c(parX, parY, parZ, growStage, 2);
            //this.ticks = 0;
        }

        ++this.ticks;
    }
}