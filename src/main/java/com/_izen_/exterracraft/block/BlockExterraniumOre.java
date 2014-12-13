package com._izen_.exterracraft.block;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com._izen_.exterracraft.client.particle.EntityCyanDustFX;
import com._izen_.exterracraft.tileentity.TileEntityExterraniumOre;

public class BlockExterraniumOre extends BlockTileEntityEC
{	
	public BlockExterraniumOre(String name)
	{
		super(name);
		this.setHardness(3F);
		this.setResistance(5F);
		this.setStepSound(soundTypePiston);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityExterraniumOre();
	}
	
	// Updates	
	/*@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		int powerLevel = 0;
		
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityExterraniumOre)
			powerLevel = ((TileEntityExterraniumOre)tileEntity).getPowerLevel();
		
		if(powerLevel > 0)
		{
			if(InfusionHelper.InfuseDirtAndGrass(world, x, y, z, InfusionHelper.powerLevelToMeta(powerLevel)))
			{
				InfusionHelper.triggerUpdate(world, x - 1, y, z);
				InfusionHelper.triggerUpdate(world, x + 1, y, z);
				InfusionHelper.triggerUpdate(world, x, y - 1, z);
				InfusionHelper.triggerUpdate(world, x, y + 1, z);
				InfusionHelper.triggerUpdate(world, x, y, z - 1);
				InfusionHelper.triggerUpdate(world, x, y, z + 1);
			}
		}
	}*/
	
	// Render stuff	
	@Override
	public boolean isOpaqueCube()
	{
		return true;
	}
	
	/*@Override
	public int getRenderType()
	{
		return RendererExterraniumOre.renderId;
	}*/
	
	/*@Override
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
	}*/
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random random)
	{
		TileEntity tileEntity = world.getTileEntity(pos);
		if(tileEntity instanceof TileEntityExterraniumOre)
		{
			if(((TileEntityExterraniumOre)tileEntity).getPowerLevel() > ((TileEntityExterraniumOre)tileEntity).maxPowerLevel / 2)
			{
				double d0 = 0.0625D;
				
				for(int i = 0; i < 6; ++i)
				{
					double d1 = (double)((float)pos.getX() + random.nextFloat());
		            double d2 = (double)((float)pos.getY() + random.nextFloat());
		            double d3 = (double)((float)pos.getZ() + random.nextFloat());
	
		            if (i == 0 && !world.getBlockState(pos.offsetUp()).getBlock().isOpaqueCube())
		            {
		                d2 = (double)pos.getY() + d0 + 1.0D;
		            }
	
		            if (i == 1 && !world.getBlockState(pos.offsetDown()).getBlock().isOpaqueCube())
		            {
		                d2 = (double)pos.getY() - d0;
		            }
	
		            if (i == 2 && !world.getBlockState(pos.offsetSouth()).getBlock().isOpaqueCube())
		            {
		                d3 = (double)pos.getZ() + d0 + 1.0D;
		            }
	
		            if (i == 3 && !world.getBlockState(pos.offsetNorth()).getBlock().isOpaqueCube())
		            {
		                d3 = (double)pos.getZ() - d0;
		            }
	
		            if (i == 4 && !world.getBlockState(pos.offsetEast()).getBlock().isOpaqueCube())
		            {
		                d1 = (double)pos.getX() + d0 + 1.0D;
		            }
	
		            if (i == 5 && !world.getBlockState(pos.offsetWest()).getBlock().isOpaqueCube())
		            {
		                d1 = (double)pos.getX() - d0;
		            }
	
		            if (d1 < (double)pos.getX() || d1 > (double)(pos.getX() + 1) || d2 < 0.0D || d2 > (double)(pos.getY() + 1) || d3 < (double)pos.getZ() || d3 > (double)(pos.getZ() + 1))
		            {
						Minecraft.getMinecraft().effectRenderer.addEffect(new EntityCyanDustFX(world, d1, d2, d3, 0F, 0F, 0F));
					}
				}
			}
		}
	}
}
