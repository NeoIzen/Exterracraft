package com._izen_.exterracraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com._izen_.exterracraft.tileentity.TileEntityExRadiationEmitter;
import com._izen_.exterracraft.utility.LogHelper;

public class ItemDebugTool extends ItemEC
{
	public ItemDebugTool()
	{
		super();
		this.setUnlocalizedName("debug_tool");
		this.setMaxStackSize(1);
	}

	@Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int xBlock, int yBlock, int zBlock, int blockSide, float par8, float par9, float par10)
    {	
		Block block = world.getBlock(xBlock, yBlock, zBlock);
		if(block.hasTileEntity(world.getBlockMetadata(xBlock, yBlock, zBlock)))
		{
			TileEntity tileEntity = world.getTileEntity(xBlock, yBlock, zBlock);
			if(tileEntity instanceof TileEntityExRadiationEmitter)
			{
				TileEntityExRadiationEmitter exRadEmitter = (TileEntityExRadiationEmitter)tileEntity;
				
				if(player.isSneaking())
				{
					//exRadEmitter.setPowerLevel(0);
					exRadEmitter.setPowerLevel(exRadEmitter.getPowerLevel() - 65535/4);
				}
				else
				{
					//exRadEmitter.setPowerLevel(exRadEmitter.getMaxPowerLevel());
					exRadEmitter.setPowerLevel(exRadEmitter.getPowerLevel() + 65535/4);
				}
				
				return true;
			}
		}
		
		return false;
    }
}
