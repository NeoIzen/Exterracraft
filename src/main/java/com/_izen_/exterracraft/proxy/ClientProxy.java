package com._izen_.exterracraft.proxy;

import com._izen_.exterracraft.client.renderer.RendererExterraniumOre;
import com._izen_.exterracraft.client.settings.KeyBindings;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerKeyBindings()
	{
		ClientRegistry.registerKeyBinding(KeyBindings.charge);
		ClientRegistry.registerKeyBinding(KeyBindings.release);
	}

	@Override
	public void registerRenderHandler()
	{
		RenderingRegistry.registerBlockHandler(new RendererExterraniumOre());
	}
}
