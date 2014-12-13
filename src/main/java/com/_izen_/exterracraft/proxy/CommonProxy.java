package com._izen_.exterracraft.proxy;

import net.minecraftforge.fml.common.registry.GameRegistry;

import com._izen_.exterracraft.reference.Reference;
import com._izen_.exterracraft.tileentity.TileEntityExterraniumOre;

public abstract class CommonProxy implements IProxy
{
	@Override
	public void registerTileEntitys()
	{
		//GameRegistry.registerTileEntity(TileEntityAtomizer.class, Reference.MOD_ID + ":" + "atomizer");
		GameRegistry.registerTileEntity(TileEntityExterraniumOre.class, Reference.MOD_ID + ":" + "exterraniumOre");
	}
	
}
