package com._izen_.exterracraft.init;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes
{
	public static void init()
	{
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.exterraniumBlock), "###", "###", "###", '#', new ItemStack(ModItems.exterraniumIngot));
	}
}
