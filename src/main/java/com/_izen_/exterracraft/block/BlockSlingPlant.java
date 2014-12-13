package com._izen_.exterracraft.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com._izen_.exterracraft.init.ECItems;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSlingPlant extends BlockPlant implements IGrowable
{
	public static final PropertyInteger	AGE	= PropertyInteger.create("age", 0, 3);
	
	public BlockSlingPlant(String name)
	{
		super(Material.plants, name);
		setCreativeTab(null);
		setHardness(0F);
		setStepSound(soundTypeGrass);
		setDefaultState(new BlockState(this, AGE).getBaseState());
	}
	
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random random)
	{
		super.updateTick(world, pos, state, random);
		
		if(world.getLightFromNeighbors(pos.offsetUp()) >= 9)
		{
			int i = ((Integer)state.getValue(AGE)).intValue();
			
			if(i < 7)
			{
				float f = getGrowthChance(this, world, pos);
				
				if(random.nextInt((int)(25.0F / f) + 1) == 0)
				{
					world.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i + 1)), 2);
				}
			}
		}
	}

    public void growCrops(World world, BlockPos pos, IBlockState state)
    {
        int i = ((Integer)state.getValue(AGE)).intValue() + 1;

        if (i > 3)
        {
            i = 3;
        }

        world.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i)), 2);
    }
    
	protected static float getGrowthChance(Block block, World world, BlockPos pos)
	{
		float f = 1.0F;
		BlockPos blockpos1 = pos.offsetDown();
		
		for(int i = -1; i <= 1; ++i)
		{
			for(int j = -1; j <= 1; ++j)
			{
				float f1 = 0.0F;
				IBlockState blockState = world.getBlockState(blockpos1.add(i, 0, j));
				
				if(blockState.getBlock().canSustainPlant(world, blockpos1.add(i, 0, j), net.minecraft.util.EnumFacing.UP, (IPlantable)block))
				{
					f1 = 1.0F;
					
					if(blockState.getBlock().isFertile(world, blockpos1.add(i, 0, j)))
					{
						f1 = 3.0F;
					}
				}
				
				if(i != 0 || j != 0)
				{
					f1 /= 4.0F;
				}
				
				f += f1;
			}
		}
		
		BlockPos blockpos2 = pos.offsetNorth();
		BlockPos blockpos3 = pos.offsetSouth();
		BlockPos blockpos4 = pos.offsetWest();
		BlockPos blockpos5 = pos.offsetEast();
		boolean flag = block == world.getBlockState(blockpos4).getBlock() || block == world.getBlockState(blockpos5).getBlock();
		boolean flag1 = block == world.getBlockState(blockpos2).getBlock() || block == world.getBlockState(blockpos3).getBlock();
		
		if(flag && flag1)
		{
			f /= 2.0F;
		} else
		{
			boolean flag2 = block == world.getBlockState(blockpos4.offsetNorth()).getBlock() || block == world.getBlockState(blockpos5.offsetNorth()).getBlock() || block == world.getBlockState(blockpos5.offsetSouth()).getBlock() || block == world.getBlockState(blockpos4.offsetSouth()).getBlock();
			
			if(flag2)
			{
				f /= 2.0F;
			}
		}
		
		return f;
	}
	
	protected Item getSeed()
	{
		return ECItems.slingPlantSeeds;
	}
	
	protected Item getCrop()
	{
		return ECItems.slingBlossom;
	}
	
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
	{
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
		int age = ((Integer)state.getValue(AGE)).intValue();
		Random rand = world instanceof World ? ((World)world).rand : new Random();
		
		if(age >= 3)
		{
			int k = 3 + fortune;
			
			for(int i = 0; i < 3 + fortune; ++i)
			{
				if(rand.nextInt(6) <= age)
				{
					ret.add(new ItemStack(this.getSeed(), 1, 0));
				}
			}
		}
		
		return ret;
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ((Integer)state.getValue(AGE)).intValue() == 3 ? this.getCrop() : this.getSeed();
    }

	@Override
    public boolean isStillGrowing(World world, BlockPos pos, IBlockState state, boolean p_176473_4_)
    {
        return ((Integer)state.getValue(AGE)).intValue() < 3;
    }

	@Override
    public boolean canUseBonemeal(World world, Random random, BlockPos pos, IBlockState state)
    {
        return isStillGrowing(world, pos, state, true);
    }

	@Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return this.getSeed();
    }

	@Override
    public void grow(World world, Random random, BlockPos pos, IBlockState state)
    {
        this.growCrops(world, pos, state);
    }
    
	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }
    
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Integer)state.getValue(AGE)).intValue();
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { AGE });
	}
	
	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
	{
		return (world.getLight(pos) >= 8 || world.canSeeSky(pos)) && this.canPlaceBlockOn(world.getBlockState(pos.offsetDown()).getBlock());
	}
}
