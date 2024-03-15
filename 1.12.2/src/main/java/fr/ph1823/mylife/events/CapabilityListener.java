package fr.ph1823.mylife.events;

import fr.ph1823.mylife.utility.MyLifeItems;
import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.capability.IProfile;
import fr.ph1823.mylife.capability.ProfileCapability;
import fr.ph1823.mylife.capability.ProfileCapabilityProvider;
import fr.ph1823.mylife.data.PhoneSavedData;
import fr.ph1823.mylife.network.MoneyMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber
public class CapabilityListener {

    @SubscribeEvent
    public void attachOnPlyer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof EntityPlayer) {
            event.addCapability(new ResourceLocation("mylife", "profile"), new ProfileCapabilityProvider());
        }
    }

    @SubscribeEvent
    public void onPlayerCapabilityChange(PlayerEvent.Clone event) {
        EntityPlayer oldPlayer = event.getOriginal();
        EntityPlayer newPlayer = event.getEntityPlayer();
        IProfile oldCap = oldPlayer.getCapability(ProfileCapability.PROFILE_CAPABILITY, null);
        IProfile newCap = newPlayer.getCapability(ProfileCapability.PROFILE_CAPABILITY, null);

        // Update the new player's capability with the old player's capability
        newCap.update(oldCap);

        MyLifeMod.LOGGER.info("update");
        if(!oldPlayer.world.isRemote) {
            MyLifeMod.MYIFE_NETWORK.sendTo(new MoneyMessage(40), (EntityPlayerMP) newPlayer);
        }
        // If this is a respawn event, send a packet to the client to update their capability
        /*if (event.isWasDeath()) {
            NetworkHandler.sendUpdatePlayerCapabilityPacket(newCap.getCustomData(), (EntityPlayerMP) newPlayer);
        }*/
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        PhoneSavedData.get(event.getWorld());
    }

    @SubscribeEvent
    public void onWorldSave(WorldEvent.Save event) {
        PhoneSavedData.get(event.getWorld()).markDirty();
    }
}
