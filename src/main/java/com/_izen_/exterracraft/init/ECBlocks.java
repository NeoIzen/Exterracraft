package com._izen_.exterracraft.init;

import net.minecraft.block.Block;

import com._izen_.exterracraft.block.BlockEC;
import com._izen_.exterracraft.block.BlockExterranium;
import com._izen_.exterracraft.block.BlockExterraniumOre;
import com._izen_.exterracraft.block.BlockInfusedDirt;
import com._izen_.exterracraft.block.BlockLeadOre;
import com._izen_.exterracraft.block.BlockSlingPlant;
import com._izen_.exterracraft.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ECBlocks
{
	public static final BlockEC	exterraniumOre			= new BlockExterraniumOre();
	public static final BlockEC	exterraniumBlock		= new BlockExterranium();
	public static final BlockEC	exterraniumBlockCharged	= new BlockExterranium();
	public static final BlockEC leadOre					= new BlockLeadOre();
	public static final BlockEC infusedDirt				= new BlockInfusedDirt();
	//public static final BlockEC	atomizer				= new BlockAtomizer();
	public static final BlockEC slingPlant				= new BlockSlingPlant();
	
	public static void init()
	{
		GameRegistry.registerBlock(exterraniumOre, "exterraniumOre");
		GameRegistry.registerBlock(exterraniumBlock, "exterraniumBlock");
		GameRegistry.registerBlock(exterraniumBlockCharged, "exterraniumBlockCharged");
		GameRegistry.registerBlock(leadOre, "leadOre");
		GameRegistry.registerBlock(infusedDirt, "infusedDirt");
		//GameRegistry.registerBlock(atomizer, "atomizer");
		GameRegistry.registerBlock(slingPlant, "slingPlant");
	}
}
