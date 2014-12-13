package com._izen_.exterracraft.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes
{
	public static void init()
	{
		GameRegistry.addShapedRecipe(new ItemStack(ECBlocks.exterraniumBlock), "###", "###", "###", '#', new ItemStack(ECItems.exterraniumIngot));
	}
}
