package fr.ph1823.mylife.utility;

import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.block.ATMBlock;
import fr.ph1823.mylife.block.CannabisBlock;
import fr.ph1823.mylife.block.HoublonBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
public class MyLifeBlocks {
     public static final Block CANNABIS_BLOCK = new CannabisBlock()
             .setRegistryName(MyLifeMod.MODID,"cannabis_block");
    public static final Block HOUBLON_BLOCK = new HoublonBlock()
            .setRegistryName(MyLifeMod.MODID,"houblon_block");
    public static final Block ATM_BLOCK = new ATMBlock(Material.WOOD).setLightLevel(4)
            .setRegistryName(MyLifeMod.MODID,"atm");

    public static void registerModels() {
    }
}
