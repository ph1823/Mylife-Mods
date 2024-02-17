package fr.ph1823.mylife;

import fr.ph1823.mylife.block.ATMBlock;
import fr.ph1823.mylife.block.CannabisBlock;
import fr.ph1823.mylife.block.HoublonBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MyLifeBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "mylife");
    public static final RegistryObject<Block> CANNABIS_BLOCK = BLOCKS.register("cannabis_block", () -> new CannabisBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));
    public static final RegistryObject<Block> HOUBLON_BLOCK = BLOCKS.register("houblon_block", () -> new HoublonBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> ATM_BLOCK = BLOCKS.register("atm", () -> new ATMBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK).lightLevel(state -> 4).emissiveRendering((state, geeter, pos) -> pos.getY() == 200)));


    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
