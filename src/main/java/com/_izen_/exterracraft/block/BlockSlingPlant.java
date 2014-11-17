package com._izen_.exterracraft.block;

import java.util.ArrayList;
import java.util.Random;

import com._izen_.exterracraft.init.ECItems;
import com._izen_.exterracraft.reference.Reference;
import com._izen_.exterracraft.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSlingPlant extends BlockPlant implements IGrowable
{
	@SideOnly(Side.CLIENT)
	private IIcon icons[];
	
	public BlockSlingPlant()
	{
		super(Material.plants);
		setBlockName("sling_plant");
		setCreativeTab(null);
		setHardness(0F);
		setStepSound(soundTypeGrass);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		super.updateTick(world, x, y, z, random);
		
		if(world.getBlockLightValue(x, y + 1, z) >= 9)
		{
			int metadata = world.getBlockMetadata(x, y, z);
			
			if(metadata < 3)
			{
				if(random.nextInt(5) == 0)
				{
					++metadata;
					world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
				}
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons = new IIcon[4];
		
		for(int i = 0 ; i < icons.length; ++i)
		{
			icons[i] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())) + i);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		if(metadata < 0 || metadata >= 4)
		{
			LogHelper.error("Wrong metadata for " + getLocalizedName());
			return icons[0];
		}
		else
			return icons[metadata];
	}
	
    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int p_149690_5_, float p_149690_6_, int p_149690_7_)
    {
    	super.dropBlockAsItemWithChance(world, x, y, z, p_149690_5_, p_149690_6_, 0);
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
    	ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
    	
    	ret.add(new ItemStack(ECItems.slingPlantSeeds, metadata == 3? world.rand.nextInt(1) + 1 : 1));
    	if(metadata == 3)
    		ret.add(new ItemStack(ECItems.slingBlossom, 1));
    	
    	return ret;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return  world.getBlock(x, y - 1, z) == Blocks.dirt || world.getBlock(x, y - 1, z) == Blocks.grass;
    }
    
	@Override
	public boolean func_149851_a(World world, int x, int y, int z, boolean p_149851_5_)
	{
		return world.getBlockMetadata(x, y, z) < 3;
	}

	@Override
	public boolean func_149852_a(World world, Random random, int x, int y, int z)
	{
		if(random.nextInt(2) == 0)
			return true;
		else
			return false;
	}

	@Override
	public void func_149853_b(World world, Random random, int x, int y, int z)
	{
		int next = world.getBlockMetadata(x, y, z) + 1;
		
		if(next > 3)
			next = 3;
		
		world.setBlockMetadataWithNotify(x, y, z, next, 2);
	}
}
