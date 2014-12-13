package com._izen_.exterracraft.utility;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com._izen_.exterracraft.block.BlockInfusedDirt;
import com._izen_.exterracraft.init.ECBlocks;
import com._izen_.exterracraft.tileentity.TileEntityExterraniumOre;

public class InfusionHelper
{
	public static boolean InfuseDirtAndGrass(World world, BlockPos pos, int meta)
	{
		boolean changed = false;
		
		if(Infuse(world, pos.offsetWest(), meta))
			changed = true;
		if(Infuse(world, pos.offsetEast(), meta))
			changed = true;
		if(Infuse(world, pos.offsetDown(), meta))
			changed = true;
		if(Infuse(world, pos.offsetUp(), meta))
			changed = true;
		if(Infuse(world, pos.offsetSouth(), meta))
			changed = true;
		if(Infuse(world, pos.offsetNorth(), meta))
			changed = true;
		
		return changed;
	}
	
	private static boolean Infuse(World world, BlockPos pos, int newMeta)
	{
		IBlockState state = world.getBlockState(pos);
		// look for Dirt
		if(state.getBlock() == Blocks.dirt)
		{
			world.setBlockState(pos, ECBlocks.infusedDirt.getDefaultState());
			return true;
		}
		else
			return false;
	}
	
	public static void triggerUpdate(World world, BlockPos pos)
	{
		/*Block block = world.getBlockState(pos).getBlock();
		if(block == ECBlocks.infusedDirt)
			((BlockInfusedDirt)block).onBlockUpdate(world, pos);*/
	}
	
	public static int getHighestNeighborMeta(World world, BlockPos pos)
	{
		int highestMeta = 0;
		
		highestMeta = Math.max(highestMeta, getMetaForInfusion(world, pos.offsetWest()));
		highestMeta = Math.max(highestMeta, getMetaForInfusion(world, pos.offsetEast()));
		highestMeta = Math.max(highestMeta, getMetaForInfusion(world, pos.offsetDown()));
		highestMeta = Math.max(highestMeta, getMetaForInfusion(world, pos.offsetUp()));
		highestMeta = Math.max(highestMeta, getMetaForInfusion(world, pos.offsetSouth()));
		highestMeta = Math.max(highestMeta, getMetaForInfusion(world, pos.offsetNorth()));
		
		return highestMeta;
	}
	
	public static int getMetaForInfusion(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);
		
		if(state.getBlock() == ECBlocks.exterraniumOre)
		{
			TileEntity tileEntity = world.getTileEntity(pos);
			
			if(tileEntity instanceof TileEntityExterraniumOre)
				return powerLevelToMeta(((TileEntityExterraniumOre)tileEntity).getPowerLevel());
			else
				return 0;
		}
		else if(state.getBlock() == ECBlocks.infusedDirt)
		{
			return ((Integer)state.getValue(((BlockInfusedDirt)state.getBlock()).RANGE)).intValue();
		}
		else
			return 0;
	}
	
	public static int powerLevelToMeta(int powerLevel)
	{
		return (int)((float)powerLevel / ((float)TileEntityExterraniumOre.maxPowerLevel / 8.F));
	}
}
