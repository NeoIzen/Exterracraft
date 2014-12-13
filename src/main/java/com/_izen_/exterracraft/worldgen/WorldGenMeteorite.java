package com._izen_.exterracraft.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
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
	public boolean generate(World world, Random random, BlockPos pos)
	{
		// Exit on client worlds
		if(world.isRemote)
			return false;
		
		double uniRadius = 0.0;
		int powerLevel = TileEntityExterraniumOre.maxPowerLevel;
		
		switch(this.size)
		{
		case FRAGMENT:
			uniRadius = 1.0;
			powerLevel = 0;
			break;
		case HUGE:
			uniRadius = 17.0;
			powerLevel /= 1;
			break;
		case LARGE:
			uniRadius = 12.0;
			powerLevel /= 2;
			break;
		case MEDIUM:
			uniRadius = 8.0;
			powerLevel /= 4;
			break;
		case SMALL:
			uniRadius = 5.0;
			powerLevel /= 8;
			break;
		case TINY:
			uniRadius = 3.0;
			powerLevel /= 16;
			break;
		default:
			break;
		}
		
		Vec3 radius = new Vec3(uniRadius * MathHelper.getRandomDoubleInRange(random, 0.9, 1.1), uniRadius * MathHelper.getRandomDoubleInRange(random, 0.9, 1.1), uniRadius * MathHelper.getRandomDoubleInRange(random, 0.9, 1.1));

		createCrater(world, pos, new Vec3(radius.xCoord * 3.0, radius.yCoord * 2.0, radius.zCoord * 3.0));
		createMeteorite(world, powerLevel, pos.offsetDown((int)radius.yCoord), radius);
		
		return true;
	}
	
	private void setBlockWithPowerLevel(World world, BlockPos pos, int powerLevel)
	{
		if(world.getBlockState(pos).getBlock() != ECBlocks.exterraniumOre)
		{
			world.setBlockState(pos, ECBlocks.exterraniumOre.getDefaultState(), 2);
			
			TileEntity tileEntity = world.getTileEntity(pos);
			if(tileEntity instanceof TileEntityExterraniumOre)
				((TileEntityExterraniumOre)tileEntity).setPowerLevel(powerLevel);
		}
	}
	
	private void replaceBlock(World world, BlockPos pos)
	{
		Block oldBlock = world.getBlockState(pos).getBlock();
		Block newBlock = world.getBiomeGenForCoords(pos).topBlock.getBlock(); // Detect biome and set block depending on biome
		
		//if(oldBlock != Blocks.air && oldBlock != Blocks.water && oldBlock != Blocks.lava)
			world.setBlockState(pos, Blocks.bedrock.getDefaultState());
	}
	
	private void createMeteorite(World world, int powerLevel, BlockPos centerPos, Vec3 radius)
	{
		radius.addVector(0.5, 0.5, 0.5);
		
		final Vec3 invRadius = new Vec3(1 / radius.xCoord, 1 / radius.yCoord, 1 / radius.zCoord);

		final Vec3i intRadius = new Vec3i(MathHelper.ceiling_double_int(radius.xCoord), MathHelper.ceiling_double_int(radius.yCoord), MathHelper.ceiling_double_int(radius.zCoord));
		
		double nextXn = 0.;
		forX: for(int x = 0; x <= intRadius.getX(); ++x)
		{
			final double xn = nextXn;
			nextXn = (x + 1) * invRadius.xCoord;
			double nextYn = 0;
			forY: for(int y = 0; y <= intRadius.getY(); ++y)
			{
				final double yn = nextYn;
				nextYn = (y + 1) * invRadius.yCoord;
				double nextZn = 0;
				forZ: for(int z = 0; z < intRadius.getZ(); ++z)
				{
					final double zn = nextZn;
					nextZn = (z + 1) * invRadius.zCoord;
					
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
					setBlockWithPowerLevel(world, centerPos.add( x,  y,  z), powerLevel);
					setBlockWithPowerLevel(world, centerPos.add( x,  y, -z), powerLevel);
					setBlockWithPowerLevel(world, centerPos.add(-x,  y,  z), powerLevel);
					setBlockWithPowerLevel(world, centerPos.add(-x,  y, -z), powerLevel);
					setBlockWithPowerLevel(world, centerPos.add( x, -y,  z), powerLevel);
					setBlockWithPowerLevel(world, centerPos.add( x, -y, -z), powerLevel);
					setBlockWithPowerLevel(world, centerPos.add(-x, -y,  z), powerLevel);
					setBlockWithPowerLevel(world, centerPos.add(-x, -y, -z), powerLevel);
				}
			}
		}
	}
	
	private void createCrater(World world, BlockPos centerPos, Vec3 radius)
	{
		radius.addVector(0.5, 0.5, 0.5);
		
		final Vec3 invRadius = new Vec3(1 / radius.xCoord, 1 / radius.yCoord, 1 / radius.zCoord);

		final Vec3i intRadius = new Vec3i(MathHelper.ceiling_double_int(radius.xCoord), MathHelper.ceiling_double_int(radius.yCoord), MathHelper.ceiling_double_int(radius.zCoord));
		
		double nextXn = 0.;
		forX: for(int x = 0; x <= intRadius.getX(); ++x)
		{
			final double xn = nextXn;
			nextXn = (x + 1) * invRadius.xCoord;
			double nextYn = 0;
			forY: for(int y = 0; y <= intRadius.getY(); ++y)
			{
				final double yn = nextYn;
				nextYn = (y + 1) * invRadius.yCoord;
				double nextZn = 0;
				forZ: for(int z = 0; z < intRadius.getZ(); ++z)
				{
					final double zn = nextZn;
					nextZn = (z + 1) * invRadius.zCoord;
					
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
						world.setBlockToAir(centerPos.add( x,  y,  z));
						world.setBlockToAir(centerPos.add( x,  y, -z));
						world.setBlockToAir(centerPos.add(-x,  y,  z));
						world.setBlockToAir(centerPos.add(-x,  y, -z));
						world.setBlockToAir(centerPos.add( x, -y,  z));
						world.setBlockToAir(centerPos.add( x, -y, -z));
						world.setBlockToAir(centerPos.add(-x, -y,  z));
						world.setBlockToAir(centerPos.add(-x, -y, -z));
					}
					// Fill the crater
					else
					{
						replaceBlock(world, centerPos.add( x, -y,  z));
						replaceBlock(world, centerPos.add( x, -y, -z));
						replaceBlock(world, centerPos.add(-x, -y,  z));
						replaceBlock(world, centerPos.add(-x, -y, -z));
					}
				}
			}
		}
	}
}
