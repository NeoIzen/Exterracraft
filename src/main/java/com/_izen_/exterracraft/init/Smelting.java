package com._izen_.exterracraft.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Smelting
{
	public static void init()
	{
		GameRegistry.addSmelting(ECBlocks.leadOre, new ItemStack(ECItems.leadIngot), 0);
	}
}
