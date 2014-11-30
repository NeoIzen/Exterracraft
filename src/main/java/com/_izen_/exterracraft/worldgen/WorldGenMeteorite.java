package com._izen_.exterracraft.worldgen;

import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com._izen_.exterracraft.init.ECBlocks;
import com._izen_.exterracraft.tileentity.TileEntityExterraniumOre;
import com._izen_.exterracraft.utility.ECMath;

public class WorldGenMeteorite extends net.minecraft.world.gen.feature.WorldGenerator
{
	public enum MeteoriteSize
	{
		FRAGMENT,
		TINY,
		SMALL,
		MEDIUM,
		LARGE,
		HUGE;
		
		public static MeteoriteSize getSize(int size)
		{
			assert size > 0 && size <= 5: "size must be between 0 and 5";
			
			switch(size)
			{
			case 0:
				return FRAGMENT;
			case 1:
				return TINY;
			case 2:
				return SMALL;
			case 3:
				return MEDIUM;
			case 4:
				return LARGE;
			case 5:
				return HUGE;
			default:
				return TINY;
			}
		}
	}
	
	private MeteoriteSize size = MeteoriteSize.TINY;
	
	WorldGenMeteorite(MeteoriteSize size)
	{
		this.size = size;
	}
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		// Exit on client worlds
		if(world.isRemote)
			return false;
		
		double radius = 0.0;
		int powerLevel = TileEntityExterraniumOre.maxPowerLevel;
		
		switch(this.size)
		{
		case FRAGMENT:
			radius = 1.0;
			powerLevel = 0;
			break;
		case HUGE:
			radius = 17.0;
			powerLevel /= 1;
			break;
		case LARGE:
			radius = 12.0;
			powerLevel /= 2;
			break;
		case MEDIUM:
			radius = 8.0;
			powerLevel /= 4;
			break;
		case SMALL:
			radius = 5.0;
			powerLevel /= 8;
			break;
		case TINY:
			radius = 3.0;
			powerLevel /= 16;
			break;
		default:
			break;
		}
		
		double radiusX = radius * MathHelper.getRandomDoubleInRange(random, 0.9, 1.1);
		double radiusY = radius * MathHelper.getRandomDoubleInRange(random, 0.9, 1.1);
		double radiusZ = radius * MathHelper.getRandomDoubleInRange(random, 0.9, 1.1);

		createCrater(world, x, y, z, radiusX * 3.0, radiusY * 2.0, radiusZ * 3.0);
		createMeteorite(world, powerLevel, x, y - (int)radiusY, z, radiusX, radiusY, radiusZ);
		
		return true;
	}
	
	private void setBlockWithPowerLevel(World world, int x, int y, int z, int powerLevel)
	{
		if(world.getBlock(x, y, z) != ECBlocks.exterraniumOre)
		{
			world.setBlock(x, y, z, ECBlocks.exterraniumOre, 0, 2);
			
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			if(tileEntity instanceof TileEntityExterraniumOre)
				((TileEntityExterraniumOre)tileEntity).setPowerLevel(powerLevel);
		}
	}
	
	private void replaceBlock(World world, int x, int y, int z)
	{
		Block oldBlock = world.getBlock(x, y, z);
		Block newBlock = world.getBiomeGenForCoords(x, z).topBlock; // Detect biome and set block depending on biome
		
		//if(oldBlock != Blocks.air && oldBlock != Blocks.water && oldBlock != Blocks.lava)
			world.setBlock(x, y, z, Blocks.bedrock);
	}
	
	private void createMeteorite(World world, int powerLevel, int centerX, int centerY, int centerZ, double radiusX, double radiusY, double radiusZ)
	{		
		radiusX += 0.5;
		radiusY += 0.5;
		radiusZ += 0.5;
		
		final double invRadiusX = 1 / radiusX;
		final double invRadiusY = 1 / radiusY;
		final double invRadiusZ = 1 / radiusZ;

		final int ceilRadiusX = (int)Math.ceil(radiusX);
		final int ceilRadiusY = (int)Math.ceil(radiusY);
		final int ceilRadiusZ = (int)Math.ceil(radiusZ);
		
		double nextXn = 0.;
		forX: for(int x = 0; x <= ceilRadiusX; ++x)
		{
			final double xn = nextXn;
			nextXn = (x + 1) * invRadiusX;
			double nextYn = 0;
			forY: for(int y = 0; y <= ceilRadiusY; ++y)
			{
				final double yn = nextYn;
				nextYn = (y + 1) * invRadiusY;
				double nextZn = 0;
				forZ: for(int z = 0; z < ceilRadiusZ; ++z)
				{
					final double zn = nextZn;
					nextZn = (z + 1) * invRadiusZ;
					
					double distanceSq = ECMath.lengthSq(xn, yn, zn);
					if(distanceSq > 1)
					{
						if(z == 0)
						{
							if(y == 0)
							{
								break forX;
							}
							break forY;
						}
						break forZ;
					}
					
					// Create the meteorite
					setBlockWithPowerLevel(world, centerX + x, centerY + y, centerZ + z, powerLevel);
					setBlockWithPowerLevel(world, centerX + x, centerY + y, centerZ - z, powerLevel);
					setBlockWithPowerLevel(world, centerX - x, centerY + y, centerZ + z, powerLevel);
					setBlockWithPowerLevel(world, centerX - x, centerY + y, centerZ - z, powerLevel);
					setBlockWithPowerLevel(world, centerX + x, centerY - y, centerZ + z, powerLevel);
					setBlockWithPowerLevel(world, centerX + x, centerY - y, centerZ - z, powerLevel);
					setBlockWithPowerLevel(world, centerX - x, centerY - y, centerZ + z, powerLevel);
					setBlockWithPowerLevel(world, centerX - x, centerY - y, centerZ - z, powerLevel);
				}
			}
		}
	}
	
	private void createCrater(World world, int centerX, int centerY, int centerZ, double radiusX, double radiusY, double radiusZ)
	{
		radiusX += 0.5;
		radiusY += 0.5;
		radiusZ += 0.5;
		
		final double invRadiusX = 1 / radiusX;
		final double invRadiusY = 1 / radiusY;
		final double invRadiusZ = 1 / radiusZ;

		final int ceilRadiusX = (int)Math.ceil(radiusX);
		final int ceilRadiusY = (int)Math.ceil(radiusY);
		final int ceilRadiusZ = (int)Math.ceil(radiusZ);
		
		double nextXn = 0.;
		forX: for(int x = 0; x <= ceilRadiusX; ++x)
		{
			final double xn = nextXn;
			nextXn = (x + 1) * invRadiusX;
			double nextYn = 0;
			forY: for(int y = 0; y <= ceilRadiusY; ++y)
			{
				final double yn = nextYn;
				nextYn = (y + 1) * invRadiusY;
				double nextZn = 0;
				forZ: for(int z = 0; z < ceilRadiusZ; ++z)
				{
					final double zn = nextZn;
					nextZn = (z + 1) * invRadiusZ;
					
					double distanceSq = ECMath.lengthSq(xn, yn, zn);
					if(distanceSq > 1)
					{
						if(z == 0)
						{
							if(y == 0)
							{
								break forX;
							}
							break forY;
						}
						break forZ;
					}
					
					// Empty out the crater
					final double l1 = ECMath.lengthSq(nextXn, yn, zn);
					final double l2 = ECMath.lengthSq(xn, nextYn, zn);
					final double l3 = ECMath.lengthSq(xn, yn, nextZn);
					if(l1 <= 1.0 && l2 <= 1.0 && l3 <= 1.0)
					{
						world.setBlockToAir(centerX + x, centerY - y, centerZ + z);
						world.setBlockToAir(centerX + x, centerY - y, centerZ - z);
						world.setBlockToAir(centerX - x, centerY - y, centerZ + z);
						world.setBlockToAir(centerX - x, centerY - y, centerZ - z);
						world.setBlockToAir(centerX + x, centerY + y, centerZ + z);
						world.setBlockToAir(centerX + x, centerY + y, centerZ - z);
						world.setBlockToAir(centerX - x, centerY + y, centerZ + z);
						world.setBlockToAir(centerX - x, centerY + y, centerZ - z);
					}
					// Fill the crater
					else
					{
						replaceBlock(world, centerX + x, centerY - y, centerZ + z);
						replaceBlock(world, centerX + x, centerY - y, centerZ - z);
						replaceBlock(world, centerX - x, centerY - y, centerZ + z);
						replaceBlock(world, centerX - x, centerY - y, centerZ - z);
					}
				}
			}
		}
	}
}
