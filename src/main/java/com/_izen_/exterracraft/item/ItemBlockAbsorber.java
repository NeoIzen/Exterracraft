package com._izen_.exterracraft.item;

import com._izen_.exterracraft.utility.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemBlockAbsorber extends ItemEC
{
	public ItemBlockAbsorber(String name)
	{
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(world.isRemote)
			LogHelper.info("onItemRightClick");
		
		return stack;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		if(world.isRemote)
		{
			LogHelper.info(String.format("onItemUse: %d | %d | %d || %d || %f | %f | %f [%s]", pos.getX(), pos.getY(), pos.getZ(), side, hitX, hitY, hitZ, world.getBlockState(pos).getBlock().getLocalizedName()));
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
			
	        return true;
		}
		else
			return false;
    }
}
