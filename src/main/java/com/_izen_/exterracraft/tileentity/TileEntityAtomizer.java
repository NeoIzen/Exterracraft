package com._izen_.exterracraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAtomizer extends TileEntityEC implements ISidedInventory
{
	private static final int[] slotsTop = new int[]{0};
	private static final int[] slotsBottom = new int[]{2, 1};
	private static final int[] slotsSides = new int[]{1};
	
	private ItemStack[] itemStacks = new ItemStack[3];
	
	public int burnTime;
	public int currentBurnTime;
	
	public int cookTime;
	
	private String name;
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Override
	public int getSizeInventory()
	{
		return this.itemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.itemStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int par1)
	{
		if(this.itemStacks[slot] != null)
		{
			ItemStack itemStack;
			
			if(this.itemStacks[slot].stackSize <= par1)
			{
				itemStack = this.itemStacks[slot];
				this.itemStacks[slot] = null;
			}
			else
			{
				itemStack = this.itemStacks[slot].splitStack(par1);
			}
			
			return itemStack;
		}
		
		return null;	
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if(this.itemStacks[slot] != null)
		{
			ItemStack itemStack = this.itemStacks[slot];
			
			return itemStack;
		}
		
		return null;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInventoryName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}
