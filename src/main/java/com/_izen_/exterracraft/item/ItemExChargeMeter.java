package com._izen_.exterracraft.item;

import com._izen_.exterracraft.tileentity.TileEntityExRadiationEmitter;
import com._izen_.exterracraft.utility.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemExChargeMeter extends ItemEC
{
	public ItemExChargeMeter()
	{
		super();
		this.setUnlocalizedName("ex_charge_meter");
		this.setMaxStackSize(1);
	}

	@Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int xBlock, int yBlock, int zBlock, int blockSide, float par8, float par9, float par10)
    {
		// Schow the Ex-charge	
		Block block = world.getBlock(xBlock, yBlock, zBlock);
		if(block.hasTileEntity(world.getBlockMetadata(xBlock, yBlock, zBlock)))
		{
			TileEntity tileEntity = world.getTileEntity(xBlock, yBlock, zBlock);
			if(tileEntity instanceof TileEntityExRadiationEmitter)
			{
				TileEntityExRadiationEmitter exRadEmitter = (TileEntityExRadiationEmitter)tileEntity;
				if(world.isRemote)
					LogHelper.info(String.format("Power Level: %d / %d", exRadEmitter.getPowerLevel(), exRadEmitter.getMaxPowerLevel()));
				return true;
			}
		}
		
		return false;
    }
}
