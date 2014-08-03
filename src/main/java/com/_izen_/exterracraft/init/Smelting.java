package com._izen_.exterracraft.init;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Smelting
{
	public static void init()
	{
		GameRegistry.addSmelting(ModItems.exterraniumDust, new ItemStack(ModItems.exterraniumIngot), 0);
	}
}
