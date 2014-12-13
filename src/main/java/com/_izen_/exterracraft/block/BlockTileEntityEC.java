package com._izen_.exterracraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com._izen_.exterracraft.tileentity.TileEntityEC;

public abstract class BlockTileEntityEC extends BlockEC implements ITileEntityProvider
{
	private Random random = new Random();

    protected BlockTileEntityEC(String name)
    {
    	this(Material.rock, name);
    }
    
    protected BlockTileEntityEC(Material material, String name)
    {
        super(material, name);
        this.isBlockContainer = true;
    }
    
    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = world.getTileEntity(pos);

        if (tileentity instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(world, pos, (IInventory)tileentity);
            world.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(world, pos, state);
    }
    
    @Override
    public boolean onBlockEventReceived(World world, BlockPos pos, IBlockState state, int eventID, int eventParam)
    {
        super.onBlockEventReceived(world, pos, state, eventID, eventParam);
        TileEntity tileentity = world.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
    }
}
