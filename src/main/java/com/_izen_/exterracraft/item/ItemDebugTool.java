package com._izen_.exterracraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com._izen_.exterracraft.tileentity.TileEntityExRadiationEmitter;

public class ItemDebugTool extends ItemEC
{
	public ItemDebugTool(String name)
	{
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		Block block = world.getBlockState(pos).getBlock();
		if(block.hasTileEntity(world.getBlockState(pos)))
		{
			TileEntity tileEntity = world.getTileEntity(pos);
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
