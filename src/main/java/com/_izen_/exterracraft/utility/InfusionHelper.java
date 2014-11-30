package com._izen_.exterracraft.utility;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com._izen_.exterracraft.block.BlockEC;
import com._izen_.exterracraft.block.BlockExterraniumOre;
import com._izen_.exterracraft.block.BlockInfusedDirt;
import com._izen_.exterracraft.init.ECBlocks;
import com._izen_.exterracraft.tileentity.TileEntityExterraniumOre;

public class InfusionHelper
{	
	public static boolean InfuseDirtAndGrass(World world, int x, int y, int z, int meta)
	{
		boolean changed = false;
		
		if(Infuse(world, x - 1, y, z, meta)) changed = true;
		if(Infuse(world, x + 1, y, z, meta)) changed = true;
		if(Infuse(world, x, y - 1, z, meta)) changed = true;
		if(Infuse(world, x, y + 1, z, meta)) changed = true;
		if(Infuse(world, x, y, z - 1, meta)) changed = true;
		if(Infuse(world, x, y, z + 1, meta)) changed = true;
		
		return changed;
	}
	
	private static boolean Infuse(World world, int x, int y, int z, int newMeta)
	{
		Block block = world.getBlock(x, y, z);
		// look for Dirt
		if(block == Blocks.dirt)
		{
			world.setBlock(x, y, z, ECBlocks.infusedDirt, newMeta, 2);
			return true;
		}
		else
			return false;
	}
	
	public static void triggerUpdate(World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		if(block == ECBlocks.infusedDirt)
			((BlockInfusedDirt)block).onBlockUpdate(world, x, y, z);
	}
	
	public static int getHighestNeighborMeta(World world, int x, int y, int z)
	{
		int highestMeta = 0;

		highestMeta = Math.max(highestMeta, getMetaForInfusion(world, x - 1, y, z));
		highestMeta = Math.max(highestMeta, getMetaForInfusion(world, x + 1, y, z));
		highestMeta = Math.max(highestMeta, getMetaForInfusion(world, x, y - 1, z));
		highestMeta = Math.max(highestMeta, getMetaForInfusion(world, x, y + 1, z));
		highestMeta = Math.max(highestMeta, getMetaForInfusion(world, x, y, z - 1));
		highestMeta = Math.max(highestMeta, getMetaForInfusion(world, x, y, z + 1));
		
		return highestMeta;
	}
	
	public static int getMetaForInfusion(World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		
		if(block == ECBlocks.exterraniumOre)
		{
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			
			if(tileEntity instanceof TileEntityExterraniumOre)
				return powerLevelToMeta(((TileEntityExterraniumOre)tileEntity).getPowerLevel());
			else
				return 0;
		}
		else if(block == ECBlocks.infusedDirt)
		{
			return world.getBlockMetadata(x, y, z);
		}
		else
			return 0;
	}
	
	public static int powerLevelToMeta(int powerLevel)
	{
		return (int)((float)powerLevel / ((float)TileEntityExterraniumOre.maxPowerLevel / 8.F));
	}
}
