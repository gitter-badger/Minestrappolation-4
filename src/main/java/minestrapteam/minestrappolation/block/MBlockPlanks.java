package minestrapteam.minestrappolation.block;

import java.util.List;

import minestrapteam.minestrappolation.Minestrappolation;
import minestrapteam.minestrappolation.lib.MReference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



public class MBlockPlanks extends Block
{
	public static final PropertyEnum	VARIANT	= PropertyEnum.create("type", MWoodType.class);
	
	public MBlockPlanks()
	{
		super(Material.wood);
		this.setCreativeTab(Minestrappolation.tabMinistrappolation);
		this.setUnlocalizedName("ministrapp_planks");
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		return ((MWoodType) state.getValue(VARIANT)).getMetadata();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{
		MWoodType[] aenumtype = MWoodType.values();
		int i = aenumtype.length;
		
		for (int j = 0; j < i; ++j)
		{
			MWoodType enumtype = aenumtype[j];
			list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, MWoodType.byMetadata(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((MWoodType) state.getValue(VARIANT)).getMetadata();
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { VARIANT });
	}
	
	public static void preinventoryRender()
	{
		Item itemBlockBrickVariants = GameRegistry.findItem(MReference.MODID, "ministrapp_planks");
		
		ModelBakery.addVariantName(itemBlockBrickVariants, "ministrapp:redwood_planks");
	}
	
	public static void inventoryRender()
	{
		Item itemBlockVariants = GameRegistry.findItem(MReference.MODID, "ministrapp_planks");
		MWoodType[] aenumtype = MWoodType.values();
		int i = aenumtype.length;
		
		for (int j = 0; j < i; ++j)
		{
			MWoodType enumtype = aenumtype[j];
			ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(MReference.MODID + ":"+enumtype.getUnlocalizedName()+"_planks", "inventory");
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlockVariants, enumtype.getMetadata(), itemModelResourceLocation);
		}
	}
	
	
}