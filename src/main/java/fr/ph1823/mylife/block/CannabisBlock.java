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



    /*protected static float getGrowthSpeed(Block p_52273_, BlockGetter p_52274_, BlockPos p_52275_) {
        return 20 * 60;
    }*/

   /* public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, Random random) {
        if (!serverLevel.isAreaLoaded(blockPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (serverLevel.getRawBrightness(blockPos, 0) >= 9) {
            int i = this.getAge(blockState);
            if (i < this.getMaxAge()) {
                float f = getGrowthSpeed(this, p_52293_, p_52294_);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(serverLevel, blockPos, blockState, random.nextInt((int)(25.0F / f) + 1) == 0)) {
                    serverLevel.setBlock(blockPos, this.getStateForAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(p_52293_, p_52294_, p_52292_);
                }
            }
        }

    }*/

    public void randomTick(@NotNull BlockState blockState, ServerLevel serverLevel, @NotNull BlockPos blockPos, @NotNull Random Rand) {
        System.out.println("tick");
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