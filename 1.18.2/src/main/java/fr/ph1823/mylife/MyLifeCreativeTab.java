package fr.ph1823.mylife;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MyLifeCreativeTab {
    public static CreativeModeTab MYLIFE_TAB = new CreativeModeTab("mylife") {
        @Override
        public ItemStack makeIcon() {
            return MyLifeItems.JOINT_ITEM.get().getDefaultInstance();
        }
    };
}
