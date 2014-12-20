package com._izen_.exterracraft.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com._izen_.exterracraft.block.BlockEC;
import com._izen_.exterracraft.block.BlockExterranium;
import com._izen_.exterracraft.block.BlockExterraniumOre;
import com._izen_.exterracraft.block.BlockInfusedDirt;
import com._izen_.exterracraft.block.BlockLeadOre;
import com._izen_.exterracraft.block.BlockSlingPlant;
import com._izen_.exterracraft.item.ItemEC;
import com._izen_.exterracraft.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ECBlocks
{
	public static final BlockEC	exterraniumOre			= new BlockExterraniumOre("exterranium_ore");
	public static final BlockEC	exterraniumBlock		= new BlockExterranium("exterranium_block");
	public static final BlockEC leadOre					= new BlockLeadOre("lead_ore");
	public static final BlockEC infusedDirt				= new BlockInfusedDirt("infused_dirt");
	//public static final BlockEC	atomizer				= new BlockAtomizer("atomizer");
	public static final BlockEC slingPlant				= new BlockSlingPlant("sling_plant");

	private static List<BlockEC> blockList = new ArrayList<BlockEC>();
	
	public static void init()
	{
		registerBlock(exterraniumOre);
		registerBlock(exterraniumBlock);
		registerBlock(leadOre);
		registerBlock(infusedDirt);
		//registerBlock(atomizer);
		registerBlock(slingPlant);
	}
	
	private static void registerBlock(BlockEC block)
	{
		GameRegistry.registerBlock(block, block.getName());
		blockList.add(block);
	}
	
	public static List<BlockEC> getBlockList()
	{
		return blockList;
	}
}
