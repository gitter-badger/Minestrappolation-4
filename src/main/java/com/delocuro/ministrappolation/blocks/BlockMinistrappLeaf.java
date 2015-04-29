package com.delocuro.ministrappolation.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.delocuro.ministrappolation.init.MinestrappolationBlocks;
import com.google.common.base.Predicate;

public class BlockMinistrappLeaf extends BlockMinistrappolationLeavesBase
{
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockMinistrappPlanks.EnumType.class, new Predicate()
    {
        private static final String __OBFID = "CL_00002085";
        public boolean apply(BlockMinistrappPlanks.EnumType type)
        {
            return type.getMetadata() < 4;
        }
        public boolean apply(Object p_apply_1_)
        {
            return this.apply((BlockMinistrappPlanks.EnumType)p_apply_1_);
        }
    });
    private static final String __OBFID = "CL_00000280";

    public BlockMinistrappLeaf()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockMinistrappPlanks.EnumType.REDWOOD).withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        	return super.getRenderColor(state);      
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        return super.colorMultiplier(worldIn, pos, renderPass);
    }

    protected int getSaplingDropChance(IBlockState state)
    {
        return super.getSaplingDropChance(state);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
    	 BlockMinistrappPlanks.EnumType[] aenumtype = BlockMinistrappPlanks.EnumType.values();
         int i = aenumtype.length;

         for (int j = 0; j < i; ++j) {
             BlockMinistrappPlanks.EnumType enumtype = aenumtype[j];
             list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
         }
    }

    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, ((BlockMinistrappPlanks.EnumType)state.getValue(VARIANT)).getMetadata());
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, this.getCustomWoodType(meta)).withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        byte b0 = 0;
        int i = b0 | ((BlockMinistrappPlanks.EnumType)state.getValue(VARIANT)).getMetadata();

        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
        {
            i |= 4;
        }

        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
        {
            i |= 8;
        }

        return i;
    }
    
    public BlockMinistrappPlanks.EnumType getCustomWoodType(int meta)
    {
    	return BlockMinistrappPlanks.EnumType.byMetadata((meta & 3) % 4);
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {VARIANT, CHECK_DECAY, DECAYABLE});
    }

    /**
     * Get the damage value that this Block should drop
     */
    public int damageDropped(IBlockState state)
    {
        return ((BlockMinistrappPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te)
    {
        if (!worldIn.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.shears)
        {
            player.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
            spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(this), 1, ((BlockMinistrappPlanks.EnumType) state.getValue(VARIANT)).getMetadata()));
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te);
        }
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(MinestrappolationBlocks.ministrapp_sapling);
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        IBlockState state = world.getBlockState(pos);
        return new java.util.ArrayList(java.util.Arrays.asList(new ItemStack(this, 0, ((BlockMinistrappPlanks.EnumType)state.getValue(VARIANT)).getMetadata())));
    }

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		IBlockState state2 = worldIn.getBlockState(pos);
        return true;
    }

	@Override
	public com.delocuro.ministrappolation.blocks.BlockMinistrappPlanks.EnumType getWoodType(
			int meta) {
		// TODO Auto-generated method stub
		return null;
	}
}
