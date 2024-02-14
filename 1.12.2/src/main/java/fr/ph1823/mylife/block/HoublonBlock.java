package fr.ph1823.mylife.block;

import fr.ph1823.mylife.MyLifeItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.item.Item;

public class HoublonBlock extends BlockCrops {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 2);
    private int ticks = 0;

    public HoublonBlock() {
        super();
    }

    public PropertyInteger getAgeProperty()
    {
        return AGE;
    }

    @Override
    public BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE});
    }

    @Override
    public int getMaxAge() {
        return 2;
    }

    protected Item getSeed()
    {
        return MyLifeItems.CANNABIS_SEED;
    }

    protected Item getCrop()
    {
        return MyLifeItems.CANNABIS_ITEM;
    }
}