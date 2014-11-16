package com._izen_.exterracraft.item;

import com._izen_.exterracraft.utility.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockAbsorber extends ItemEC
{
	public ItemBlockAbsorber()
	{
		super();
		this.setUnlocalizedName("block_absorber");
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
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		if(world.isRemote)
		{
			LogHelper.info(String.format("onItemUse: %d | %d | %d || %d || %f | %f | %f [%s]", x, y, z, side, hitX, hitY, hitZ, world.getBlock(x, y, z).getLocalizedName()));
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
			
	        return true;
		}
		else
			return false;
    }
}
