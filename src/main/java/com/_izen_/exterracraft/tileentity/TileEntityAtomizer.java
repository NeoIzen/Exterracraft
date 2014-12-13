package com._izen_.exterracraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	public ItemStack decrStackSize(int slot, int par2)
	{
		if(this.itemStacks[slot] != null)
		{
			ItemStack itemStack;
			
			if(this.itemStacks[slot].stackSize <= par2)
			{
				itemStack = this.itemStacks[slot];
				this.itemStacks[slot] = null;
			}
			else
			{
				itemStack = this.itemStacks[slot].splitStack(par2);
				
				if(this.itemStacks[slot].stackSize == 0)
				{
					this.itemStacks[slot] = null;
				}
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
			this.itemStacks[slot] = null;
			
			return itemStack;
		}
		
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack)
	{
		this.itemStacks[slot] = itemStack;
		
		if(itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
		{
			itemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	/*@Override
	public String getInventoryName()
	{
		return this.hasCustomInventoryName()? this.name : "Atomizer";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return this.name != null && this.name.length() > 0;
	}*/

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64.0D;
	}

	/*@Override
	public void openInventory()
	{		
	}

	@Override
	public void closeInventory()
	{		
	}*/

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		return slot == 2? false : (slot == 1? isItemFuel(itemStack) : true);
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
		return null;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStack, EnumFacing side)
	{
		return this.isItemValidForSlot(slot, itemStack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, EnumFacing side)
	{
		//return side != 0 || slot != 1 || itemStack.getItem() == Items.bucket;
		return false;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		
		NBTTagList tagList = tagCompound.getTagList("items", 9);
		this.itemStacks = new ItemStack[this.getSizeInventory()];
		
		for(int i = 0; i < tagList.tagCount(); ++i)
		{
			NBTTagCompound tagCompound1 = tagList.getCompoundTagAt(i);
			byte slot = tagCompound1.getByte("slot");
			
			if(slot >= 0 && slot < this.itemStacks.length)
			{
				this.itemStacks[slot] = ItemStack.loadItemStackFromNBT(tagCompound1);
			}
		}
		
		this.burnTime = tagCompound.getShort("burnTime");
		this.cookTime = tagCompound.getShort("cookTime");
		this.currentBurnTime = getItemBurnTime(this.itemStacks[1]);
		
		if(tagCompound.hasKey("customName", 8))
		{
			this.name = tagCompound.getString("customName");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		
		NBTTagList tagList = new NBTTagList();
		
		for(int i = 0; i < this.itemStacks.length; ++i)
		{
			if(this.itemStacks[i] != null)
			{
				NBTTagCompound tagCompound1 = new NBTTagCompound();
				
				tagCompound1.setByte("slot", (byte)i);
				this.itemStacks[i].writeToNBT(tagCompound1);
				tagList.appendTag(tagCompound1);
			}
		}
		
		tagCompound.setTag("items", tagList);

		tagCompound.setShort("burnTime", (short)this.burnTime);
		tagCompound.setShort("cookTime", (short)this.cookTime);
		
		if(this.hasCustomName())
		{
			tagCompound.setString("customName", this.name);
		}
	}
	
	/*@Override
	public void updateEntity()
	{
		boolean flag = this.burnTime > 0;
		boolean flag1 = false;
		
		if(this.burnTime > 0)
		{
			--this.burnTime;
		}
		
		if(!this.worldObj.isRemote)
		{
			if(this.burnTime == 0 && this.canSmelt())
			{
				this.currentBurnTime = this.burnTime = getItemBurnTime(this.itemStacks[1]);
				
				if(this.burnTime > 0)
				{
					flag1 = true;
					if(this.itemStacks[1] != null)
					{
						--this.itemStacks[1].stackSize;
						
						if(this.itemStacks[1].stackSize == 0)
						{
							this.itemStacks[1] = this.itemStacks[1].getItem().getContainerItem(this.itemStacks[1]);
						}
					}
				}
			}
			
			if(this.isBurning() && this.canSmelt())
			{
				++this.cookTime;
				
				if(this.cookTime == 200)
				{
					this.cookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			}
			else
			{
				this.cookTime = 0;
			}
		}
		
		if(flag != this.burnTime > 0)
		{
			flag1 = true;
			//BlockAtomizer.updateBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		}
		
		if(flag1)
		{
			this.markDirty();
		}
	}*/

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1)
	{
		return this.cookTime * par1 / 200;
	}
	
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1)
	{
		if(this.currentBurnTime == 0)
		{
			this.currentBurnTime = 200;
		}
		
		return this.burnTime * par1 / this.currentBurnTime;
	}
	
	public boolean isBurning()
	{
		return this.burnTime > 0;
	}
	
	/*private boolean canSmelt()
	{
		if(this.itemStacks[0] != null)
		{
			return false;
		}
		else
		{
			ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(this.itemStacks[0]);
			
			if(itemStack == null) return false;
			if(this.itemStacks[2] == null) return true;
			if(!this.itemStacks[2].isItemEqual(itemStack)) return false;
			
			int result = this.itemStacks[2].stackSize + itemStack.stackSize;
			return result <= this.getInventoryStackLimit() && result <= this.itemStacks[2].getMaxStackSize();
		}
	}*/
	
	/*public void smeltItem()
	{
		if(this.canSmelt())
		{
			ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(this.itemStacks[0]);
			
			if(this.itemStacks[2] == null)
			{
				this.itemStacks[2] = itemStack.copy();
			}
			else if(this.itemStacks[2].getItem() == itemStack.getItem())
			{
				this.itemStacks[2].stackSize += itemStack.stackSize;
			}
		}
	}*/
	
	private static int getItemBurnTime(ItemStack itemStack)
	{
		if(itemStack == null)
		{
			return 0;
		}
		else
		{
			Item item = itemStack.getItem();
			
			if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
			{
				Block block = Block.getBlockFromItem(item);
			}
			
			return GameRegistry.getFuelValue(itemStack);
		}
	}
	
	public static boolean isItemFuel(ItemStack itemStack)
	{
		return getItemBurnTime(itemStack) > 0;
	}

	@Override
	public void openInventory(EntityPlayer playerIn)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer playerIn)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getField(int id)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomName()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IChatComponent getDisplayName()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
