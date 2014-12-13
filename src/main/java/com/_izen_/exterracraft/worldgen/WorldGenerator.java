package com._izen_.exterracraft.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import com._izen_.exterracraft.handler.ConfigurationHandler;
import com._izen_.exterracraft.init.ECBlocks;
import com._izen_.exterracraft.worldgen.WorldGenMeteorite.MeteoriteSize;

public class WorldGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.getDimensionId())
		{
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}
	}
	
	private void generateEnd(World world, Random random, int x, int z)
	{
	}
	
	private void generateSurface(World world, Random random, int x, int z)
	{
		if(ConfigurationHandler.enableLeadOreGeneration)
			addOreSpawn(ECBlocks.leadOre.getDefaultState(), Blocks.stone, world, random, x, z, 16, 16, 8, 10, 10, 35);
		
		// Meteorite spawn
		if(random.nextInt(100) == 101)
		{
			BlockPos pos = new BlockPos(x + random.nextInt(16), z + random.nextInt(16), world.getHeight());
			
			for(; pos.getY() > 0; pos = pos.offsetDown())
			{
				if(world.getBlockState(pos).getBlock() == world.getBiomeGenForCoords(pos).topBlock.getBlock())
					break;
			}
			
			if(pos.getY() > 0)
			{
				int size = 1 + random.nextInt(4); // randomize size (1-5)
				(new WorldGenMeteorite(MeteoriteSize.getSize(size))).generate(world, random, pos);
			}
		}
	}
	
	private void generateNether(World world, Random random, int x, int z)
	{
	}
	
	/**
	 * Adds an Ore Spawn to Minecraft. Simply register all Ores to spawn with
	 * this method in your Generation method in your IWorldGeneration extending
	 * Class
	 *
	 * @param The
	 *            Block to spawn
	 * @param The
	 *            metadata of the Block
	 * @param The
	 *            Block where to generate in
	 * @param The
	 *            World to spawn in
	 * @param A
	 *            Random object for retrieving random positions within the world
	 *            to spawn the Block
	 * @param An
	 *            int for passing the X-Coordinate for the Generation method
	 * @param An
	 *            int for passing the Z-Coordinate for the Generation method
	 * @param An
	 *            int for setting the maximum X-Coordinate values for spawning
	 *            on the X-Axis on a Per-Chunk basis
	 * @param An
	 *            int for setting the maximum Z-Coordinate values for spawning
	 *            on the Z-Axis on a Per-Chunk basis
	 * @param An
	 *            int for setting the maximum size of a vein
	 * @param An
	 *            int for the Number of chances available for the Block to spawn
	 *            per-chunk
	 * @param An
	 *            int for the minimum Y-Coordinate height at which this block
	 *            may spawn
	 * @param An
	 *            int for the maximum Y-Coordinate height at which this block
	 *            may spawn
	 **/
	public void addOreSpawn(IBlockState block, Block target, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
	{
		int maxPosY = minY + (maxY - 1);
		assert maxY > minY : "The maximum Y must be greater than the Minimum Y";
		assert maxX > 0 && maxX <= 16 : "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
		assert minY > 0 : "addOreSpawn: The Minimum Y must be greater than 0";
		assert maxY < 256 && maxY > 0 : "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
		assert maxZ > 0 && maxZ <= 16 : "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";
		
		int diffBtwnMinMaxY = maxY - minY;
		for(int x = 0; x < chancesToSpawn; x++)
		{
			BlockPos pos = new BlockPos(blockXPos + random.nextInt(maxX), minY + random.nextInt(diffBtwnMinMaxY), blockZPos + random.nextInt(maxZ));
			(new WorldGenMinable(block, maxVeinSize, BlockHelper.forBlock(target))).generate(world, random, pos);
		}
	}
}
