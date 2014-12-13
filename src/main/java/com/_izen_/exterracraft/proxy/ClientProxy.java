package com._izen_.exterracraft.proxy;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import com._izen_.exterracraft.block.BlockEC;
import com._izen_.exterracraft.client.settings.KeyBindings;
import com._izen_.exterracraft.init.ECBlocks;
import com._izen_.exterracraft.init.ECItems;
import com._izen_.exterracraft.item.ItemEC;
import com._izen_.exterracraft.reference.Reference;

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
		//RenderingRegistry.registerBlockHandler(new RendererExterraniumOre());
	}

	@Override
	public void registerItemModels()
	{
		List<ItemEC> itemList = ECItems.getItemList();
		for(ItemEC item : itemList)
		{
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID.toLowerCase() + ":" + item.getName(), "inventory"));
		}
	}
}
