package com._izen_.exterracraft.item;

import com._izen_.exterracraft.Exterracraft;
import com._izen_.exterracraft.handler.GuiHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDataTablet extends ItemEC
{
	public ItemDataTablet()
	{
		super();
		this.setUnlocalizedName("data_tablet");
		this.setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(world.isRemote)
			player.openGui(Exterracraft.instance, GuiHandler.GuiId.infoTablet.ordinal(), player.worldObj, 0, 0, 0);
		
		return stack;
	}
}
