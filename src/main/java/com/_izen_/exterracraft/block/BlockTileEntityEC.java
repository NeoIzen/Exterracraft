package com._izen_.exterracraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com._izen_.exterracraft.tileentity.TileEntityEC;

public abstract class BlockTileEntityEC extends BlockEC implements ITileEntityProvider
{
	private Random random = new Random();

    protected BlockTileEntityEC()
    {
    	this(Material.rock);
    }
    
    protected BlockTileEntityEC(Material material)
    {
        super(material);
        this.isBlockContainer = true;
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
    	// Drop Inventory on the ground
		TileEntityEC tileEntity = (TileEntityEC)world.getTileEntity(x, y, z);
		
		if(tileEntity != null)
		{
			if(tileEntity instanceof IInventory)
			{
				IInventory tileEntityInventory = (IInventory) tileEntity;
				
				for(int i = 0; i < tileEntityInventory.getSizeInventory(); ++i)
				{
					ItemStack itemStack = tileEntityInventory.getStackInSlot(i);
					
					if(itemStack != null)
					{
						float f = this.random.nextFloat() * 0.6F + 0.1F; // x
						float f1 = this.random.nextFloat() * 0.6F + 0.1F; // y
						float f2 = this.random.nextFloat() * 0.6F + 0.1F; // z
						
						while(itemStack.stackSize > 0)
						{
							int j = this.random.nextInt(21) + 10;
							
							if(j > itemStack.stackSize)
							{
								j = itemStack.stackSize;
							}
							
							itemStack.stackSize -= j;
							EntityItem entityItem = new EntityItem(world,
									(double)((float)x + f),
									(double)((float)y + f1),
									(double)((float)z + f2),
									new ItemStack(itemStack.getItem(), j, itemStack.getItemDamage()));
							
							if(itemStack.hasTagCompound())
							{
								entityItem.getEntityItem().setTagCompound((NBTTagCompound)itemStack.getTagCompound().copy());
							}
							
							float f3 = 0.025F;
							entityItem.motionX = (double)((float)this.random.nextGaussian() * f3);
							entityItem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.1F);
							entityItem.motionZ = (double)((float)this.random.nextGaussian() * f3);
							
							world.spawnEntityInWorld(entityItem);
						}
					}
					world.func_147453_f(x, y, z, block);
				}
			}
		}
		
    	// Vanilla implementation    	
        super.breakBlock(world, x, y, z, block, metadata);
        world.removeTileEntity(x, y, z);
    }
    
    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int p_149696_5_, int p_149696_6_)
    {
        super.onBlockEventReceived(world, x, y, z, p_149696_5_, p_149696_6_);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null ? tileentity.receiveClientEvent(p_149696_5_, p_149696_6_) : false;
    }
}
