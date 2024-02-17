package fr.ph1823.mylife.block;

import fr.ph1823.mylife.MyLifeBlocks;
import fr.ph1823.mylife.MyLifeItems;
import fr.ph1823.mylife.MyLifeMod;
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

public class CannabisBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    private int ticks = 0;

    public CannabisBlock(Properties builder) {
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

    protected boolean mayPlaceOn(BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos) {
        return blockState.is(Blocks.DIRT) || blockState.is(Blocks.GRASS_BLOCK);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return MyLifeItems.CANNABIS_SEED.get(); // Use wheat seeds as the seed item for your crop
    }

    public void destroy(LevelAccessor level, BlockPos blockPos, BlockState blockState) {
        System.out.println("destroy ");
        level.setBlock(blockPos, MyLifeBlocks.CANNABIS_BLOCK.get().defaultBlockState(), 0);
    }



    @Override
    public void randomTick(@NotNull BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, Random random) {
        ++this.ticks;
        MyLifeMod.LOGGER.info("tick = " + this.ticks);
        if (!serverLevel.isAreaLoaded(blockPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (serverLevel.getRawBrightness(blockPos, 0) >= 9) {
            int i = this.getAge(blockState);
            if (i < this.getMaxAge()) {

                if (this.ticks == new Random().nextInt(60, 120)) {
                    this.ticks = 0;
                    serverLevel.setBlock(blockPos, this.getStateForAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(serverLevel, blockPos, blockState);
                }
            }
        }
    }
}