package com._izen_.exterracraft;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com._izen_.exterracraft.client.handler.KeyInputEventHandler;
import com._izen_.exterracraft.handler.ConfigurationHandler;
import com._izen_.exterracraft.handler.GuiHandler;
import com._izen_.exterracraft.init.ECBlocks;
import com._izen_.exterracraft.init.ECItems;
import com._izen_.exterracraft.init.ECOreDictionary;
import com._izen_.exterracraft.init.Recipes;
import com._izen_.exterracraft.init.Smelting;
import com._izen_.exterracraft.proxy.IProxy;
import com._izen_.exterracraft.reference.Reference;
import com._izen_.exterracraft.worldgen.WorldGenerator;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Exterracraft
{
	public static final boolean developmentEnvironment = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");

	@Mod.Instance(Reference.MOD_ID)
	public static Exterracraft instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	WorldGenerator worldGenerator = new WorldGenerator();
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		// Network handling
		// Config
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		
		// KeyBindings
		proxy.registerKeyBindings();
		
		GameRegistry.registerWorldGenerator(this.worldGenerator, 0);
		// Items/Blocks
		ECItems.init();
		ECBlocks.init();
		ECOreDictionary.init();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
		// GUI
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		
		// Tileentitys
		proxy.registerTileEntitys();
		
		// Rendererstuff
		proxy.registerItemModels();
		proxy.registerRenderHandler();
		
		// Recipes
		Recipes.init();
		Smelting.init();
		
		//FMLInterModComms.sendMessage("Waila", "register", WailaRegistrar.classPath);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}
}
