package minestrapteam.minestrappolation.block;

import minestrapteam.minestrappolation.Minestrappolation;
import minestrapteam.minestrappolation.lib.MBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class MBlock extends Block
{
	private final MapColor	mapColor;
	
	public MBlock(Material materialIn, MapColor mapColorIn)
	{
		super(materialIn);
		this.mapColor = mapColorIn;
		this.setCreativeTab(Minestrappolation.tabMBuilding);
	}
	
	@Override
	public MapColor getMapColor(IBlockState state)
	{
		return this.mapColor;
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
	{
		if (this == MBlocks.bronze_block || this == MBlocks.steel_block || this == MBlocks.meurodite_block || this == MBlocks.torite_block || this == MBlocks.titanium_block || this == MBlocks.blazium_block || this == MBlocks.soul_gem_block || this == MBlocks.radiant_block || this == MBlocks.radiant_chiseled)
			return true;
		else
			return false;
	}
}
