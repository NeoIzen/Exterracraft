package com._izen_.exterracraft.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com._izen_.exterracraft.creativetab.CreativeTabEC;
import com._izen_.exterracraft.reference.Reference;

public class ItemEC extends Item
{
	private final String name;
	
	public ItemEC(String name)
	{
		super();
		this.setCreativeTab(CreativeTabEC.EC_TAB);
		this.name = name;
		this.setUnlocalizedName(this.name);
	}

	@Override
	public String getUnlocalizedName()
	{
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", this.name);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return this.getUnlocalizedName();
	}

	public String getName()
	{
		return this.name;
	}
}
