package com._izen_.exterracraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com._izen_.exterracraft.tileentity.TileEntityExRadiationEmitter;
import com._izen_.exterracraft.utility.LogHelper;

public class ItemExChargeMeter extends ItemEC
{
	public ItemExChargeMeter(String name)
	{
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		// Schow the Ex-charge	
		Block block = world.getBlockState(pos).getBlock();
		if(block.hasTileEntity(world.getBlockState(pos)))
		{
			TileEntity tileEntity = world.getTileEntity(pos);
			if(tileEntity instanceof TileEntityExRadiationEmitter)
			{
				TileEntityExRadiationEmitter exRadEmitter = (TileEntityExRadiationEmitter)tileEntity;
				if(!world.isRemote)
					player.addChatMessage(new ChatComponentText(String.format("Power Level: %d / %d", exRadEmitter.getPowerLevel(), exRadEmitter.getMaxPowerLevel())));
				return true;
			}
		}
		
		return false;
    }
}
