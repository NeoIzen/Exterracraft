package com._izen_.exterracraft.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ECOreDictionary
{
	public static void init()
	{
		OreDictionary.registerOre("oreLead", ECBlocks.leadOre);
		OreDictionary.registerOre("ingotLead", ECItems.leadIngot);
	}
}
