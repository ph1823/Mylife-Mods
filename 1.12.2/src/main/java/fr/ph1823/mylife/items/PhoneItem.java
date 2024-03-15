package fr.ph1823.mylife.items;

import fr.ph1823.mylife.data.PhoneSavedData;
import fr.ph1823.mylife.events.ScreenListener;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class PhoneItem extends ItemBase {

    private long latClick = System.currentTimeMillis() - 10000;
    public PhoneItem(String name) {
        super(name);
    }

    @SideOnly(Side.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if(System.currentTimeMillis() >= this.latClick + 100)
            ScreenListener.phoneOpen = true;

        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }


    @Override
    public void onUpdate(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected)
    {
        if(!worldIn.isRemote && getNumber(stack) == 0) {
            this.setNumber(stack, worldIn);
        }
    }
    public void setNumber(ItemStack itemStack, World world)
    {
        if (itemStack.getTagCompound() == null)
            itemStack.setTagCompound(new NBTTagCompound());

        int number = new Random().nextInt(900000);

        while(!PhoneSavedData.get(world).addNumber(number))
            number = new Random().nextInt(900000);

        itemStack.getTagCompound().setInteger("number", number);
    }

    public int getNumber(ItemStack itemStack)
    {
        //SplashProgress
        if (itemStack.getTagCompound() == null)
            return 0;

        return itemStack.getTagCompound().getInteger("number");
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add("Number: " + this.getNumber(stack));
    }
}
