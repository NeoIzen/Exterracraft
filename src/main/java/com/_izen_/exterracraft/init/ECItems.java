package com._izen_.exterracraft.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com._izen_.exterracraft.Exterracraft;
import com._izen_.exterracraft.item.ItemBlockAbsorber;
import com._izen_.exterracraft.item.ItemDataTablet;
import com._izen_.exterracraft.item.ItemDebugTool;
import com._izen_.exterracraft.item.ItemEC;
import com._izen_.exterracraft.item.ItemExChargeMeter;
import com._izen_.exterracraft.item.ItemExterraniumDust;
import com._izen_.exterracraft.item.ItemExterraniumIngot;
import com._izen_.exterracraft.item.ItemLeadIngot;
import com._izen_.exterracraft.item.ItemSlingBlossom;
import com._izen_.exterracraft.item.ItemSlingPlantSeed;
import com._izen_.exterracraft.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ECItems
{
	public static final ItemEC debugTool				= new ItemDebugTool();
	
	public static final ItemEC exterraniumIngot			= new ItemExterraniumIngot();
	public static final ItemEC exterraniumIngotCharged	= new ItemExterraniumIngot();
	public static final ItemEC exterraniumDust			= new ItemExterraniumDust();
	public static final ItemEC exterraniumDustCharged	= new ItemExterraniumDust();
	public static final ItemEC leadIngot				= new ItemLeadIngot();
	public static final ItemEC exChargeMeter			= new ItemExChargeMeter();
	public static final ItemEC dataTablet				= new ItemDataTablet();
	public static final ItemEC blockAbsorber			= new ItemBlockAbsorber();
	public static final ItemEC slingPlantSeeds			= new ItemSlingPlantSeed();
	public static final ItemEC slingBlossom				= new ItemSlingBlossom();
	
	public static void init()
	{
		if(Exterracraft.developmentEnvironment)
		{
			GameRegistry.registerItem(debugTool, "debugTool");
		}

		GameRegistry.registerItem(exterraniumIngot, "exterraniumIngot");
		GameRegistry.registerItem(exterraniumIngotCharged, "exterraniumIngotCharged");
		GameRegistry.registerItem(exterraniumDust, "exterraniumDust");
		GameRegistry.registerItem(exterraniumDustCharged, "exterraniumDustCharged");
		GameRegistry.registerItem(leadIngot, "leadIngot");
		GameRegistry.registerItem(exChargeMeter, "exChargeMeter");
		GameRegistry.registerItem(dataTablet, "dataTablet");
		GameRegistry.registerItem(blockAbsorber, "blockAbsorber");
		GameRegistry.registerItem(slingPlantSeeds, "slingPlantSeeds");
		GameRegistry.registerItem(slingBlossom, "slingBlossom");
	}
}
