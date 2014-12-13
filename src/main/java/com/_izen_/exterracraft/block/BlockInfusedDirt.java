package com._izen_.exterracraft.block;

import java.util.Random;

import scala.actors.threadpool.Arrays;

import com._izen_.exterracraft.init.ECBlocks;
import com._izen_.exterracraft.tileentity.TileEntityExterraniumOre;
import com._izen_.exterracraft.utility.InfusionHelper;
import com._izen_.exterracraft.utility.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockInfusedDirt extends BlockEC
{
	public static final PropertyInteger RANGE = PropertyInteger.create("range", 0, 8);
	
	public BlockInfusedDirt(String name)
	{
		super(Material.ground, name);
	}
	
	/*@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		onBlockUpdate(world, x, y, z);
	}*/
	
	/*public void onBlockUpdate(World world, BlockPos pos)
	{
		boolean changed = false;
		int meta = world.getBlockMetadata();
		
		// Adjust own metadata
		int highestNeibhorMeta = InfusionHelper.getHighestNeighborMeta(world, x, y, z);
		
		if(meta > 0)
		{
			if(highestNeibhorMeta <= meta)
			{
				world.setBlockMetadataWithNotify(x, y, z, meta - 1, 2);
				changed = true;
			}
			else if(highestNeibhorMeta > meta + 1)
			{
				world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
				changed = true;
			}
		}
		else
		{
			if(highestNeibhorMeta <= meta)
			{
				world.setBlock(x, y, z, Blocks.dirt);
				changed = true;
			}
			else if(highestNeibhorMeta > meta + 1)
			{
				world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
				changed = true;
			}
		}
		
		// Infuse others
		meta = world.getBlockMetadata(x, y, z);
		if(meta > 0)
		{
			if(InfusionHelper.InfuseDirtAndGrass(world, x, y, z, meta - 1)) changed = true;
		}
		
		// Update
		if(changed)
		{
			InfusionHelper.triggerUpdate(world, x - 1, y, z);
			InfusionHelper.triggerUpdate(world, x + 1, y, z);
			InfusionHelper.triggerUpdate(world, x, y - 1, z);
			InfusionHelper.triggerUpdate(world, x, y + 1, z);
			InfusionHelper.triggerUpdate(world, x, y, z - 1);
			InfusionHelper.triggerUpdate(world, x, y, z + 1);
		}
	}*/
}
