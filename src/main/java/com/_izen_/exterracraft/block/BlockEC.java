package com._izen_.exterracraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com._izen_.exterracraft.creativetab.CreativeTabEC;
import com._izen_.exterracraft.reference.Reference;

public class BlockEC extends Block
{
	private final String name;
	
	public BlockEC(String name)
	{
		this(Material.rock, name);
	}
	
	public BlockEC(Material material, String name)
	{
		super(material);
		this.setCreativeTab(CreativeTabEC.EC_TAB);
		this.name = name;
		this.setUnlocalizedName(this.name);
	}

	@Override
	public String getUnlocalizedName()
	{
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", this.name);
	}

	public String getName()
	{
		return this.name;
	}
}
