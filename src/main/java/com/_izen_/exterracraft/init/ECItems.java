package com._izen_.exterracraft.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.common.registry.GameRegistry;

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

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ECItems
{
	public static final ItemEC debugTool				= new ItemDebugTool("debug_tool");
	
	public static final ItemEC exterraniumIngot			= new ItemExterraniumIngot("exterranium_ingot");
	public static final ItemEC exterraniumDust			= new ItemExterraniumDust("exterranium_dust");
	public static final ItemEC leadIngot				= new ItemLeadIngot("lead_ingot");
	public static final ItemEC exChargeMeter			= new ItemExChargeMeter("ex_charge_meter");
	public static final ItemEC dataTablet				= new ItemDataTablet("data_tablet");
	public static final ItemEC blockAbsorber			= new ItemBlockAbsorber("block_absorber");
	public static final ItemEC slingPlantSeeds			= new ItemSlingPlantSeed("sling_plant_seeds");
	public static final ItemEC slingBlossom				= new ItemSlingBlossom("sling_blossom");
	
	private static List<ItemEC> itemList = new ArrayList<ItemEC>();
	
	public static void init()
	{
		if(Exterracraft.developmentEnvironment)
		{
			registerItem(debugTool);
		}

		registerItem(exterraniumIngot);
		registerItem(exterraniumDust);
		registerItem(leadIngot);
		registerItem(exChargeMeter);
		registerItem(dataTablet);
		registerItem(blockAbsorber);
		registerItem(slingPlantSeeds);
		registerItem(slingBlossom);
	}
	
	private static void registerItem(ItemEC item)
	{
		GameRegistry.registerItem(item, item.getName());
		itemList.add(item);
	}
	
	public static List<ItemEC> getItemList()
	{
		return itemList;
	}
}
