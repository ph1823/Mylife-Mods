package fr.ph1823.mylife.capability;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;

public class ProfileCapabilityProvider implements ICapabilityProvider {

    private final IProfile profile;

    public ProfileCapabilityProvider() {
        this.profile = new Profile();
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {
        return capability == ProfileCapability.PROFILE_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == ProfileCapability.PROFILE_CAPABILITY ? (T) profile : null;
    }
}
