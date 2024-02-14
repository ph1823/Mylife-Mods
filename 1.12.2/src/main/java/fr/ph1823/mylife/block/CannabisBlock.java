package fr.ph1823.mylife.block;

import fr.ph1823.mylife.MyLifeItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;


public class CannabisBlock extends BlockCrops {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 2);
    private int ticks = 0;

    public CannabisBlock() {
        super();
        //this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(0)));
    }

    public PropertyInteger getAgeProperty()
    {
        return AGE;
    }

    @Override
    public BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, AGE);
    }

    @Override
    public int getMaxAge() {
        return 2; // Set the maximum age to 3 for three growth stages
    }

    @Override
    protected Item getSeed() {
        return MyLifeItems.CANNABIS_SEED;
    }

    @Override
    protected Item getCrop() {
        return MyLifeItems.CANNABIS_ITEM;
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.GRASS;
    }

    /*@Override
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
    }*/
}