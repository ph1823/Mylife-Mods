package fr.ph1823.mylife.block;


import fr.ph1823.mylife.MyLifeBlocks;
import fr.ph1823.mylife.MyLifeItems;
import fr.ph1823.mylife.MyLifeMod;
import fr.ph1823.mylife.capability.IProfile;
import fr.ph1823.mylife.capability.ProfileCapability;
import fr.ph1823.mylife.gui.GuiNewCharacter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ATMBlock extends Block {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool UPPER = PropertyBool.create("upper");
    public ATMBlock(Material material) {
        super(material);
        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(FACING, EnumFacing.NORTH)
                .withProperty(UPPER, false));
        //this.collisionTab.set(0, new double[]{0, 0.8125, 0.40625, 1, 1, 0.59375});
       // this.collisionTab.set(1, new double[]{0.40625, 0, 0.40625, 0.59375, 0.8125, 0.59375});
        //ItemArmor.ArmorMaterial
        //BlockDoor
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return true;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state
                .withProperty(FACING, rot.rotate(state.getValue(FACING)))
                .withProperty(UPPER, state.getValue(UPPER));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, UPPER);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = state.getValue(FACING).getIndex();
        if (state.getValue(UPPER)) {
            meta |=  1 <<  3; // Shift UPPER bit to the  4th position
        }
        return meta;
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getHorizontal(meta &  7); // Extract the first three bits
        boolean upper = (meta &  8) ==  8; // Check if the fourth bit is set
        return getDefaultState().withProperty(FACING, facing).withProperty(UPPER, upper);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {

        IBlockState iblockstate = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
        iblockstate = iblockstate.withProperty(FACING, placer.getHorizontalFacing());
        return iblockstate;
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        if (pos.getY() >= worldIn.getHeight() - 1)
        {
            return false;
        }
        else
        {
            IBlockState state = worldIn.getBlockState(pos.down());
            return (state.isTopSolid() || state.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID) && super.canPlaceBlockAt(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos.up());
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        IBlockState newState = state.withProperty(UPPER, true);
        MyLifeMod.LOGGER.info(worldIn.setBlockState(pos.up(), newState, 2));
        worldIn.notifyNeighborsOfStateChange(pos, state.getBlock(), false);
        worldIn.notifyNeighborsOfStateChange(pos, newState.getBlock(), false);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if(worldIn.getBlockState(pos.down()).getBlock().equals(MyLifeBlocks.ATM_BLOCK) && state.getValue(UPPER))
            worldIn.destroyBlock(pos.down(), false);
        else if(worldIn.getBlockState(pos.up()).getBlock().equals(MyLifeBlocks.ATM_BLOCK)) worldIn.destroyBlock(pos.up(), false);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(MyLifeItems.ATM_ITEM);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
       MyLifeMod.LOGGER.info("try open atm");
       if (!world.isRemote) {
            // This is the server side, so we don't want to open the GUI here
            return true;
        } else {
            // This is the client side, so we open the GUI
            //player.openGui(MyMod.INSTANCE, MyGuiHandler.MY_GUI_ID, world, pos.getX(), pos.getY(), pos.getZ()); -> register gui ? with open item
           Minecraft.getMinecraft().displayGuiScreen(new GuiNewCharacter());
            return true;
        }
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return state.getValue(UPPER) ? EnumBlockRenderType.INVISIBLE : super.getRenderType(state);
    }
}
