package com._izen_.exterracraft.block;

import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com._izen_.exterracraft.client.renderer.RendererExterraniumOre;
import com._izen_.exterracraft.tileentity.TileEntityExterraniumOre;
import com._izen_.exterracraft.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockExterraniumOre extends BlockTileEntityEC
{	
	@SideOnly(Side.CLIENT)
	private IIcon iconCharged;
	
	public BlockExterraniumOre()
	{
		super();
		this.setBlockName("exterranium_ore");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityExterraniumOre();
	}
	
	/*@Override
	public int getLightValue(IBlockAccess blockAccess, int x, int y, int z)
	{
		TileEntity tileEntity = blockAccess.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityExterraniumOre)
		{
			TileEntityExterraniumOre tileEntityExterraniumOre = (TileEntityExterraniumOre)tileEntity;
			
			float lightLevel = ((float)tileEntityExterraniumOre.getPowerLevel() / (float)tileEntityExterraniumOre.maxPowerLevel) * 15F;
			return (int)lightLevel;
		}
		else
			return 0;
	}*/
	
	@Override
	public int getLightValue()
	{
		return 0;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return true;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return RendererExterraniumOre.renderId;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess blockAcces, int x, int y, int z, int side)
	{
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		super.registerBlockIcons(iconRegister);
		this.iconCharged = iconRegister.registerIcon(String.format("%s_%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()),"charged"));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAcces, int x, int y, int z, int meta)
	{
		TileEntity tileEntity = blockAcces.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityExterraniumOre)
		{
			if(((TileEntityExterraniumOre)tileEntity).getPowerLevel() == 0)
				return this.blockIcon;
			else
				return this.iconCharged;
		}
		
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityExterraniumOre)
		{
			if(((TileEntityExterraniumOre)tileEntity).getPowerLevel() > ((TileEntityExterraniumOre)tileEntity).maxPowerLevel / 2)
			{
				double d0 = 0.0625D;
				
				for(int l = 0; l < 6; ++l)
				{
					double d1 = (double)((float)x + random.nextFloat());
					double d2 = (double)((float)y + random.nextFloat());
					double d3 = (double)((float)z + random.nextFloat());
					
					if(l == 0 && !world.getBlock(x, y + 1, z).isOpaqueCube())
					{
						d2 = (double)(y + 1) + d0;
					}
					
					if(l == 1 && !world.getBlock(x, y - 1, z).isOpaqueCube())
					{
						d2 = (double)(y + 0) - d0;
					}
					
					if(l == 2 && !world.getBlock(x, y, z + 1).isOpaqueCube())
					{
						d3 = (double)(z + 1) + d0;
					}
					
					if(l == 3 && !world.getBlock(x, y, z - 1).isOpaqueCube())
					{
						d3 = (double)(z + 0) - d0;
					}
					
					if(l == 4 && !world.getBlock(x + 1, y, z).isOpaqueCube())
					{
						d1 = (double)(x + 1) + d0;
					}
					
					if(l == 5 && !world.getBlock(x - 1, y, z).isOpaqueCube())
					{
						d1 = (double)(x + 0) - d0;
					}
					
					if(d1 < (double)x || d1 > (double)(x + 1) || d2 < 0.0D || d2 > (double)(y + 1) || d3 < (double)z || d3 > (double)(z + 1))
					{
						world.spawnParticle("reddust", d1, d2, d3, 0.0D, 0.0D, 0.0D);
					}
				}
			}
		}
	}
}
