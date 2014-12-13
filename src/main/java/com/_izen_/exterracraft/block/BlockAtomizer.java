package com._izen_.exterracraft.block;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com._izen_.exterracraft.Exterracraft;
import com._izen_.exterracraft.tileentity.TileEntityAtomizer;

// Its basically like an pulverizer or macerator
public class BlockAtomizer extends BlockTileEntityEC
{
	private Random random = new Random();
	
	public BlockAtomizer(String name)
	{
		super(name);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityAtomizer();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		player.openGui(Exterracraft.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
		
		return true;
	}
}
