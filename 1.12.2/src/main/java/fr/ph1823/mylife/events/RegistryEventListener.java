package fr.ph1823.mylife.events;


import fr.ph1823.mylife.utility.MyLifeBlocks;
import fr.ph1823.mylife.utility.MyLifeItems;
import fr.ph1823.mylife.MyLifeMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryEventListener {
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        MyLifeMod.LOGGER.info("Register blocks");
        event.getRegistry().registerAll(
                MyLifeBlocks.ATM_BLOCK,
                MyLifeBlocks.CANNABIS_BLOCK,
                MyLifeBlocks.HOUBLON_BLOCK

        );
    }
    //@SubscribeEvent(priority = EventPriority.LOW)

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        MyLifeMod.LOGGER.info("Register items");
        event.getRegistry().registerAll(
                MyLifeItems.ATM_ITEM,
                MyLifeItems.BEER_ITEM,
                MyLifeItems.CANNABIS_ITEM,
                MyLifeItems.CHOPE_ITEM,
                MyLifeItems.HOUBLON_ITEM,
                MyLifeItems.CANNABIS_SEED,
                MyLifeItems.HOUBLON_SEED,
                MyLifeItems.JOINT_ITEM,
                MyLifeItems.PHONE_ITEM
        );
    }

    @SubscribeEvent
    public static void registerModel(ModelRegistryEvent event) {
        MyLifeMod.LOGGER.info("Register models");
        MyLifeItems.registerModels();
        MyLifeBlocks.registerModels();
    }
}
