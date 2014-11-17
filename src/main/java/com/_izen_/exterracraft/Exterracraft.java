package com._izen_.exterracraft;

import net.minecraft.launchwrapper.Launch;

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

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

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
		
		// Renderer
		proxy.registerRenderHandler();
		
		// Recipes
		Recipes.init();
		Smelting.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
