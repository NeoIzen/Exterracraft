package com._izen_.exterracraft.intermod.waila;

import java.util.List;

import com._izen_.exterracraft.block.BlockEC;
import com._izen_.exterracraft.block.BlockExterraniumOre;
import com._izen_.exterracraft.block.BlockInfusedDirt;
import com._izen_.exterracraft.init.ECBlocks;
import com._izen_.exterracraft.tileentity.TileEntityExterraniumOre;
import com._izen_.exterracraft.utility.LogHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

public class WailaRegistrar
{	
	public static final String classPath = "com._izen_.exterracraft.intermod.waila.WailaRegistrar.callbackRegister";
	
	public static void callbackRegister(IWailaRegistrar registrar)
	{
		IWailaDataProvider grassAndDirtProvider = new WailaGrassAndDirtProvider();
		
		registrar.registerBodyProvider(new WailaExterraniumOreProvider(), BlockExterraniumOre.class);
		registrar.registerBodyProvider(grassAndDirtProvider , BlockInfusedDirt.class);
	}
}
