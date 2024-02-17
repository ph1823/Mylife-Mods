package fr.ph1823.mylife.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ProfileCapability {
    @CapabilityInject(IProfile.class)
    public static final Capability<IProfile> PROFILE_CAPABILITY = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IProfile.class, new ProfileStorage(), Profile::new);
    }
}
