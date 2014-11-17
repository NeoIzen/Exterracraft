package com._izen_.exterracraft.block;

import java.util.Random;

import com._izen_.exterracraft.Exterracraft;
import com._izen_.exterracraft.init.ECBlocks;
import com._izen_.exterracraft.tileentity.TileEntityAtomizer;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

// Its basically like an pulverizer or macerator
public class BlockAtomizer extends BlockTileEntityEC
{
	private Random random = new Random();
	
	public BlockAtomizer()
	{
		super();
		this.setBlockName("atomizer");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityAtomizer();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		player.openGui(Exterracraft.instance, 0, world, x, y, z);
		
		return true;
	}
}
