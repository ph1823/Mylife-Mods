package fr.ph1823.mylife;

import fr.ph1823.mylife.items.JoinItem;
import fr.ph1823.mylife.items.drink.BeerItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MyLifeItems {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "mylife");
    public static final RegistryObject<Item> CHOPE_ITEM = ITEMS.register("chope", () -> new BottleItem(new Item.Properties()));
    public static final RegistryObject<Item> BEER_ITEM = ITEMS.register("beer", () -> new BeerItem((new Item.Properties())));
    public static final RegistryObject<Item> JOINT_ITEM = ITEMS.register("joint", () -> new JoinItem((new Item.Properties())));
    public static final RegistryObject<Item> CANNABIS_ITEM = ITEMS.register("cannabis", () -> new Item((new Item.Properties())));
    public static final RegistryObject<Item> CANNABIS_SEED_ITEM = ITEMS.register("cannabis_seed", () -> new ItemNameBlockItem(MyLifeBlocks.CANNABIS_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> ATM_ITEM  = ITEMS.register("atm", () -> new BlockItem(MyLifeBlocks.ATM_BLOCK.get(),new Item.Properties()));
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
