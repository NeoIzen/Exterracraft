package com._izen_.exterracraft.proxy;

import com._izen_.exterracraft.reference.Reference;
import com._izen_.exterracraft.tileentity.TileEntityAtomizer;
import com._izen_.exterracraft.tileentity.TileEntityExterraniumOre;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
	@Override
	public void registerTileEntitys()
	{
		//GameRegistry.registerTileEntity(TileEntityAtomizer.class, Reference.MOD_ID + ":" + "atomizer");
		GameRegistry.registerTileEntity(TileEntityExterraniumOre.class, Reference.MOD_ID + ":" + "exterraniumOre");
	}
}
