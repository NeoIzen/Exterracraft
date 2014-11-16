package com._izen_.exterracraft.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com._izen_.exterracraft.client.gui.GuiDataTablet;
import com._izen_.exterracraft.utility.LogHelper;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	public static enum GuiId
	{
		infoTablet;
		
		public static final GuiId[] VALUES = GuiId.values();
	}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		final GuiId guiId = getGuiId(id);
		if(guiId == null) return null;
		
		return null;
		
		/*switch(guiId)
		{
		case infoTablet:
			return new GuiDataTablet(player, world, x, y, z);
		default:
			return null;
		}*/
	}
	
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		final GuiId guiId = getGuiId(id);
		if(guiId == null) return null;
		
		switch(guiId)
		{
		case infoTablet:
			return new GuiDataTablet(player, world, x, y, z);
		default:
			return null;
		}
	}
	
	private static GuiId getGuiId(int id)
	{
		try
		{
			return GuiId.VALUES[id];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			LogHelper.warn("Invalid GUI id: " + id);
			return null;
		}
	}
}
