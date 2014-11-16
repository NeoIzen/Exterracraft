package com._izen_.exterracraft.init;

import com._izen_.exterracraft.block.BlockEC;
import com._izen_.exterracraft.block.BlockExterranium;
import com._izen_.exterracraft.block.BlockExterraniumOre;
import com._izen_.exterracraft.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
	public static final BlockEC	exterraniumOre			= new BlockExterraniumOre();
	public static final BlockEC	exterraniumBlock		= new BlockExterranium();
	public static final BlockEC	exterraniumBlockCharged	= new BlockExterranium();
	//public static final BlockEC	atomizer				= new BlockAtomizer();
	
	public static void init()
	{
		GameRegistry.registerBlock(exterraniumOre, "exterraniumOre");
		GameRegistry.registerBlock(exterraniumBlock, "exterraniumBlock");
		GameRegistry.registerBlock(exterraniumBlockCharged, "exterraniumBlockCharged");
		//GameRegistry.registerBlock(atomizer, "atomizer");
	}
}
