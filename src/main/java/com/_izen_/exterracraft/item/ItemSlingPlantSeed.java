package com._izen_.exterracraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import com._izen_.exterracraft.block.BlockPlant;
import com._izen_.exterracraft.init.ECBlocks;

public class ItemSlingPlantSeed extends ItemEC implements IPlantable
{	
	public ItemSlingPlantSeed()
	{
		super();
		setUnlocalizedName("sling_plant_seeds");
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
	{
		return EnumPlantType.Crop;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z)
	{
		return ECBlocks.slingPlant;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
	{
		return 0;
	}
	
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
	{
		if(ForgeDirection.getOrientation(side) != ForgeDirection.UP)
			return false;
		else if(player.canPlayerEdit(x, y, z, side, itemStack) && player.canPlayerEdit(x, y + 1, z, side, itemStack))
		{
			if(world.getBlock(x, y, z) == Blocks.dirt || world.getBlock(x, y, z) == Blocks.grass)
			{
				if(world.isAirBlock(x, y + 1, z))
				{
					world.setBlock(x, y + 1, z, ECBlocks.slingPlant);
					--itemStack.stackSize;
					return true;
				}
				return false;
			}
			return false;
		}
		else
			return false;
	}
}
