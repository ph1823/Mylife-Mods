package fr.ph1823.mylife.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;


public class ProfileStorage implements Capability.IStorage<IProfile> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IProfile> capability, IProfile instance, EnumFacing side) {
        // Create an NBTTagCompound to hold the data
        NBTTagCompound compound = new NBTTagCompound();

        // Write value in NBT
        compound.setDouble("money", instance.getMoney());
        compound.setString("firstname", instance.getFistname());
        compound.setString("lastname", instance.getLastname());
        return compound;
    }

    @Override
    public void readNBT(Capability<IProfile> capability, IProfile instance, EnumFacing side, NBTBase nbt) {
        if (nbt instanceof NBTTagCompound) {
            NBTTagCompound compound = (NBTTagCompound) nbt;
            instance.setMoney(compound.getDouble("money"));
            instance.setFistname(compound.getString("firstname"));
            instance.setLastname(compound.getString("lastname"));
        }
    }
}
