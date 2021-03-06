package minestrapteam.minestrappolation;

import minestrapteam.chunkster.CEventHandler;
import minestrapteam.minestrappolation.creativetab.MTab;
import minestrapteam.minestrappolation.handlers.MEventHandler;
import minestrapteam.minestrappolation.handlers.MFuelHandler;
import minestrapteam.minestrappolation.handlers.MGuiHandler;
import minestrapteam.minestrappolation.lib.MAchievements;
import minestrapteam.minestrappolation.lib.MBlocks;
import minestrapteam.minestrappolation.lib.MDictionary;
import minestrapteam.minestrappolation.lib.MDrops;
import minestrapteam.minestrappolation.lib.MFluid;
import minestrapteam.minestrappolation.lib.MItems;
import minestrapteam.minestrappolation.lib.MRecipes;
import minestrapteam.minestrappolation.lib.MReference;
import minestrapteam.minestrappolation.network.CommonProxy;
import minestrapteam.minestrappolation.network.MPackets;
import minestrapteam.minestrappolation.tileentity.TileEntityAlloy;
import minestrapteam.minestrappolation.tileentity.TileEntityBarrel;
import minestrapteam.minestrappolation.tileentity.TileEntityCrate;
import minestrapteam.minestrappolation.tileentity.TileEntityCrusher;
import minestrapteam.minestrappolation.tileentity.TileEntityMelter;
import minestrapteam.minestrappolation.tileentity.TileEntitySawMill;
import minestrapteam.minestrappolation.tileentity.TileEntitySplitter;
import minestrapteam.minestrappolation.tileentity.TileEntityStoneCutter;
import minestrapteam.minestrappolation.util.Tables;
import minestrapteam.minestrappolation.world.MBiomeManager;
import minestrapteam.minestrappolation.world.MGenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = MReference.MODID, name = MReference.NAME, version = MReference.VERSION)
public class Minestrappolation
{
	@Instance(MReference.MODID)
	public static Minestrappolation	instance;
	
	@SidedProxy(clientSide = MReference.CLIENT_PROXY_CLASS, serverSide = MReference.SERVER_PROXY_CLASS)
	public static CommonProxy		proxy;
	
	public static final MTab		tabMBuilding	= new MTab("tabMBuilding");
	public static final MTab		tabMDecor	    = new MTab("tabMDecor");
	public static final MTab		tabMTech	    = new MTab("tabMTech");
	public static final MTab		tabMMaterials	= new MTab("tabMMaterials");
	public static final MTab		tabMFood	    = new MTab("tabMFood");
	public static final MTab		tabMTools	    = new MTab("tabMTools");
	public static final MTab		tabMCombat	    = new MTab("tabMCombat");
	
	public static SimpleNetworkWrapper network;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{	
		Config.configInit(event);
		MFluid.init();
		MItems.init();
		MBlocks.init();
		MItems.addItemsToChests();
		
		MItems.register();
		MRecipes.register();
		MRecipes.removeRecipes();
		
		MDictionary.load();
		MBiomeManager.load();
		
		Tables.loadTables();
		
		MAchievements.load();
		MFluid.load();
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("Minestrap");
		MPackets.registerPackets(network);
		
		MinecraftForge.EVENT_BUS.register(new MEventHandler());
		MinecraftForge.EVENT_BUS.register(new MDrops());
		
		MinecraftForge.EVENT_BUS.register(new CEventHandler());
		
		proxy.preInit(event);
		if (Minestrappolation.proxy != null)
		{
			NetworkRegistry.INSTANCE.registerGuiHandler(this, new MGuiHandler());
		}
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRenders();
		proxy.init(event);
		MGenHandler.load();
		GameRegistry.registerTileEntity(TileEntityBarrel.class, "tile_entity_barrel");
		GameRegistry.registerTileEntity(TileEntityCrate.class, "tile_entity_crate");
		GameRegistry.registerTileEntity(TileEntityMelter.class, "tile_entity_melter");
		GameRegistry.registerTileEntity(TileEntityAlloy.class, "tile_entity_alloy");
		GameRegistry.registerTileEntity(TileEntityStoneCutter.class, "tile_entity_stonecutter");
		GameRegistry.registerTileEntity(TileEntitySawMill.class, "tile_entity_sawmill");
		GameRegistry.registerTileEntity(TileEntityCrusher.class, "tile_entity_crusher");
		GameRegistry.registerTileEntity(TileEntitySplitter.class, "tile_entity_splitter");
		
		GameRegistry.registerFuelHandler(new MFuelHandler());
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}
}
