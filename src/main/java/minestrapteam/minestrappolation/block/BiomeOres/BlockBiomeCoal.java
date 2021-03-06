package minestrapteam.minestrappolation.block.BiomeOres;

import java.util.List;

import minestrapteam.minestrappolation.Minestrappolation;
import minestrapteam.minestrappolation.block.ore.MBlockOre;
import minestrapteam.minestrappolation.enumtypes.MStoneType;
import minestrapteam.minestrappolation.lib.MReference;
import net.minecraft.block.material.MapColor;
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
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBiomeCoal extends MBlockOre
{
	
	private static final PropertyEnum	VARIANT	= PropertyEnum.create("type", MStoneType.class);
	
	public BlockBiomeCoal(int range, int rate, Material material, MapColor mapColor, Item itemDrop, int meta, int expMin, int expMax, int dropAmount, int bonusAmount, String tool, int level, boolean silkHarvest)
	{
		super(material, mapColor, itemDrop, meta, expMin, expMax, dropAmount, bonusAmount, tool, level, silkHarvest);
		this.setCreativeTab(Minestrappolation.tabMBuilding);
		this.setUnlocalizedName("biome_coal");
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { VARIANT });
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{
		MStoneType[] aenumtype = MStoneType.values();
		int i = aenumtype.length;
		
		for (int j = 0; j < i; ++j)
		{
			MStoneType enumtype = aenumtype[j];
			list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
		}
		
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, MStoneType.byMetadata(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((MStoneType) state.getValue(VARIANT)).getMetadata();
	}
	
	public static void inventoryRender()
	{
		Item itemBlockBrickVariants = GameRegistry.findItem(MReference.MODID, "biome_coal");
		
		ModelBakery.addVariantName(itemBlockBrickVariants, "ministrapp:deepstone_coal");
		ModelBakery.addVariantName(itemBlockBrickVariants, "ministrapp:coldstone_coal");
		ModelBakery.addVariantName(itemBlockBrickVariants, "ministrapp:icestone_coal");
		ModelBakery.addVariantName(itemBlockBrickVariants, "ministrapp:glacierrock_coal");
		ModelBakery.addVariantName(itemBlockBrickVariants, "ministrapp:deep_coldstone_coal");
		ModelBakery.addVariantName(itemBlockBrickVariants, "ministrapp:red_rock_coal");
		ModelBakery.addVariantName(itemBlockBrickVariants, "ministrapp:deep_redrock_coal");
		ModelBakery.addVariantName(itemBlockBrickVariants, "ministrapp:oceanstone_coal");
		ModelBakery.addVariantName(itemBlockBrickVariants, "ministrapp:pressurized_oceanstone_coal");
		
		Item itemBlockVariants = GameRegistry.findItem(MReference.MODID, "biome_coal");
		MStoneType[] aenumtype = MStoneType.values();
		int i = aenumtype.length;
		
		for (int j = 0; j < i; ++j)
		{
			MStoneType enumtype = aenumtype[j];
			ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(MReference.MODID + ":" + enumtype.getUnlocalizedName() + "_coal", "inventory");
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlockVariants, enumtype.getMetadata(), itemModelResourceLocation);
		}
	}
	
	@Override
	public float getBlockHardness(World worldIn, BlockPos pos)
	{
		IBlockState state = worldIn.getBlockState(pos);
		if (state == this.getStateFromMeta(MStoneType.DEEPSTONE.getMetadata()) || state == this.getStateFromMeta(MStoneType.DEEPCOLDSTONE.getMetadata()) || state == this.getStateFromMeta(MStoneType.DEEPREDROCK.getMetadata()) || state == this.getStateFromMeta(MStoneType.GLACIERSTONE.getMetadata()) || state == this.getStateFromMeta(MStoneType.POCEANSTONE.getMetadata()))
			return 1.5F * this.blockHardness;
		else
			return this.blockHardness;
	}
}
