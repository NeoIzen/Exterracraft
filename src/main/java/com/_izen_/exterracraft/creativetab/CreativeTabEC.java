package com._izen_.exterracraft.creativetab;

import com._izen_.exterracraft.init.ECItems;
import com._izen_.exterracraft.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabEC
{
	public static final CreativeTabs EC_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
	{
		@Override
		public Item getTabIconItem()
		{
			return ECItems.exterraniumIngot;
		}
	};
}
