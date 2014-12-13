package com._izen_.exterracraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import com._izen_.exterracraft.init.ECBlocks;

public class ItemSlingPlantSeed extends ItemEC implements IPlantable
{	
	public ItemSlingPlantSeed(String name)
	{
		super(name);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
	{
		return EnumPlantType.Crop;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos)
	{
		return ECBlocks.slingPlant.getDefaultState();
	}
	
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(side != EnumFacing.UP)
			return false;
		else if(!player.func_175151_a(pos.offset(side), side, itemStack))
		{
			return false;
		}
		else if(world.getBlockState(pos).getBlock() == Blocks.dirt || world.getBlockState(pos).getBlock() == Blocks.grass)
		{
			if(world.isAirBlock(pos.offsetUp()))
			{
				world.setBlockState(pos.offsetUp(), ECBlocks.slingPlant.getDefaultState());
				--itemStack.stackSize;
				return true;
			}
			return false;
		}
		else
			return false;
	}
}
