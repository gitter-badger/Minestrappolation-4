package minestrapteam.minestrappolation.item;

import java.util.ArrayList;

import minestrapteam.minestrappolation.Minestrappolation;
import minestrapteam.minestrappolation.lib.MAchievements;
import minestrapteam.minestrappolation.lib.MBlocks;
import minestrapteam.minestrappolation.lib.MItems;
import minestrapteam.minestrappolation.util.Chance;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSifter extends Item{
	
	public ItemSifter()
    {
        this.maxStackSize = 1;
        this.setMaxDamage(36);
        this.isDamageable();
        this.setCreativeTab(Minestrappolation.tabMTools);
    }
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
			
			if (worldIn.getBlockState(pos) == Blocks.sand.getStateFromMeta(0))
            {
                this.spawnDropFrom(stack, playerIn, worldIn, pos, "sifter_sand");
                playerIn.addStat(MAchievements.sifter, 1);
            }
			else if (worldIn.getBlockState(pos) == Blocks.sand.getStateFromMeta(1))
            {
				this.spawnDropFrom(stack, playerIn, worldIn, pos, "sifter_red_sand"); 
            }
			else if (worldIn.getBlockState(pos).getBlock() == Blocks.gravel)
            {
				this.spawnDropFrom(stack, playerIn, worldIn, pos, "sifter_gravel");
            }
			else if (worldIn.getBlockState(pos) == Blocks.dirt.getStateFromMeta(0) || worldIn.getBlockState(pos) == Blocks.dirt.getStateFromMeta(1) || worldIn.getBlockState(pos).getBlock() == Blocks.grass)
            {
				this.spawnDropFrom(stack, playerIn, worldIn, pos, "sifter_dirt");
            }
			else if (worldIn.getBlockState(pos) == Blocks.dirt.getStateFromMeta(2))
			{
				this.spawnDropFrom(stack, playerIn, worldIn, pos, "sifter_podzol");
			}
			else if (worldIn.getBlockState(pos).getBlock() == Blocks.mycelium)
            {
				this.spawnDropFrom(stack, playerIn, worldIn, pos, "sifter_mycelium");
            }
			else if (worldIn.getBlockState(pos).getBlock() == MBlocks.dirt_permafrost || worldIn.getBlockState(pos).getBlock() == MBlocks.lichen_permafrost)
            {
				this.spawnDropFrom(stack, playerIn, worldIn, pos, "sifter_permafrost");
            }
			else if (worldIn.getBlockState(pos).getBlock() == MBlocks.cold_sand)
            {
				this.spawnDropFrom(stack, playerIn, worldIn, pos, "sifter_cold_sand");
            }
			else if (worldIn.getBlockState(pos).getBlock() == Blocks.soul_sand)
            {
				this.spawnDropFrom(stack, playerIn, worldIn, pos, "sifter_soul_sand");
            }
            return true;
    }
	
	private void spawnDropFrom(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, String table)
	{
		 worldIn.destroyBlock(pos, false);       
         ItemStack drop = (ItemStack)Chance.getRandomFromTable(table);
         if(drop != null)
         {
        	 drop.stackSize = 1;
             playerIn.inventory.addItemStackToInventory(drop);	
         }	
         stack.damageItem(1, playerIn); 
	}
}
