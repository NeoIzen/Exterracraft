package com._izen_.exterracraft;

import com._izen_.exterracraft.handler.ConfigurationHandler;
import com._izen_.exterracraft.init.ModBlocks;
import com._izen_.exterracraft.init.ModItems;
import com._izen_.exterracraft.init.Recipes;
import com._izen_.exterracraft.init.Smelting;
import com._izen_.exterracraft.proxy.IProxy;
import com._izen_.exterracraft.reference.Reference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Exterracraft
{
	@Mod.Instance(Reference.MOD_ID)
	public static Exterracraft instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		// Network handling
		// Config
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		
		// Items/Blocks
		ModItems.init();
		ModBlocks.init();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		// GUI
		// Tileentitys
		// Recipes
		Recipes.init();
		Smelting.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
