package com._izen_.exterracraft.init;

import com._izen_.exterracraft.item.ItemEC;
import com._izen_.exterracraft.item.ItemExterraniumDust;
import com._izen_.exterracraft.item.ItemExterraniumIngot;
import com._izen_.exterracraft.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
	public static final ItemEC	exterraniumIngot	= new ItemExterraniumIngot();
	public static final ItemEC	exterraniumDust		= new ItemExterraniumDust();
	
	public static void init()
	{
		GameRegistry.registerItem(exterraniumIngot, "exterraniumIngot");
		GameRegistry.registerItem(exterraniumDust, "exterraniumDust");
	}
}
