package com._izen_.exterracraft.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com._izen_.exterracraft.reference.Reference;

public class ConfigurationHandler
{
	public static Configuration configuration;
	public static boolean enableLeadOreGeneration = true;
	public static int meteoriteSpawnRate = 1;
	
	public static void init(File configFile)
	{
		// Create the configuration object from the given configuration file
		if(configuration == null)
		{
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}
	
	private static void loadConfiguration()
	{
		enableLeadOreGeneration = configuration.getBoolean("leadOreGeneration", "worldgen", true, "Enables the generation of Lead Ore");
		meteoriteSpawnRate = configuration.getInt("meteoriteSpawnRate", "worldgen", 1, 0, 1, "Sets the rate at which Meteorites can be generated");
		
		if(configuration.hasChanged())
		{
			configuration.save();
		}
	}
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.modID.equalsIgnoreCase(Reference.MOD_ID))
		{
			loadConfiguration();
		}
	}
}