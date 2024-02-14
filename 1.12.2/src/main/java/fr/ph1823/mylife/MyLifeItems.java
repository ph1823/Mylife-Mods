package fr.ph1823.mylife;

import fr.ph1823.mylife.items.ItemBase;
import fr.ph1823.mylife.items.JoinItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSeeds;

public class MyLifeItems {

    public static final ItemBase CHOPE_ITEM = new ItemBase("chope");
    public static final ItemBase BEER_ITEM = new ItemBase("beer");
    public static final Item JOINT_ITEM = new JoinItem().setRegistryName("mylife","joint");
    public static final ItemBase CANNABIS_ITEM = new ItemBase("cannabis");
    public static final ItemBase HOUBLON_ITEM = new ItemBase("houblon");


    public static final Item CANNABIS_SEED = new ItemSeeds(MyLifeBlocks.CANNABIS_BLOCK, Blocks.GRASS).setRegistryName("mylife","cannabis_block");
    public static final Item HOUBLON_SEED = new ItemSeeds(MyLifeBlocks.HOUBLON_BLOCK, Blocks.DIRT).setRegistryName("mylife","houblon_seed");

    public static final Item ATM_ITEM  = new ItemBlock(MyLifeBlocks.ATM_BLOCK).setRegistryName("mylife","atm");


    public static void registerModels() {
        BEER_ITEM.registerItemModel();
        CHOPE_ITEM.registerItemModel();
        CANNABIS_ITEM.registerItemModel();
        HOUBLON_ITEM.registerItemModel();

        MyLifeMod.proxy.registerItemRenderer(JOINT_ITEM, 0, "joint");
        MyLifeMod.proxy.registerItemRenderer(CANNABIS_SEED, 0, "cannabis_seed");
        MyLifeMod.proxy.registerItemRenderer(HOUBLON_SEED, 0, "houblon_seed");
        MyLifeMod.proxy.registerItemRenderer(ATM_ITEM, 0, "atm");
    }
}
