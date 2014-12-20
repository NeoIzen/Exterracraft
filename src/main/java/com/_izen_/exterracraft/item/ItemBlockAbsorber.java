package com._izen_.exterracraft.item;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com._izen_.exterracraft.client.particle.EntityBlockDustFX;
import com._izen_.exterracraft.utility.LogHelper;
import com._izen_.exterracraft.utility.NBTHelper;

public class ItemBlockAbsorber extends ItemEC
{	
	private final IParticleFactory factory;
	
	public ItemBlockAbsorber(String name)
	{
		super(name);
		this.setMaxStackSize(1);
		
		factory = new EntityBlockDustFX.Factory();
	}
	
	@Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 0;
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(player.isSneaking())
		{
			// Open GUI and insert item container
		}
		
		return stack;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));

		if(!NBTHelper.getBoolean(stack, "absorbing"))
		{
			// Start absorbing
			startAbsorbing(stack, pos);
		}
		else
		{
			updateAbsorbing(stack, player, world, pos);
		}
		
		// Try using only onItemUse for this
		
        return false;
    }

	private void startAbsorbing(ItemStack stack, BlockPos pos)
	{
		NBTHelper.setBoolean(stack, "absorbing", true);
		NBTHelper.setInteger(stack, "xPosUsedOn", pos.getX());
		NBTHelper.setInteger(stack, "yPosUsedOn", pos.getY());
		NBTHelper.setInteger(stack, "zPosUsedOn", pos.getZ());
		NBTHelper.setInteger(stack, "counter", 50);
	}

	private void updateAbsorbing(ItemStack stack, EntityPlayer player, World world, BlockPos pos)
	{
		// restart on block look change
		BlockPos oldPos = new BlockPos(NBTHelper.getInt(stack, "xPosUsedOn"), NBTHelper.getInt(stack, "yPosUsedOn"), NBTHelper.getInt(stack, "zPosUsedOn"));
		if(oldPos.getX() != pos.getX() || oldPos.getY() != pos.getY() || oldPos.getZ() != pos.getZ())
			startAbsorbing(stack, pos);
		
		if(world.isRemote)
			spawnParticles(player, world, pos);
				
		// Destroy block after counter decreases to 0
		int counter = NBTHelper.getInt(stack, "counter");
		if(counter <= 0)
		{
			stopAbsorbing(stack, player, world, pos);
		}
		
		// Decrease the counter
		NBTHelper.setInteger(stack, "counter", counter - 1);
	}

	private void stopAbsorbing(ItemStack stack, EntityPlayer player, World world, BlockPos pos)
	{		
		if(NBTHelper.hasTag(stack, "xPosUsedOn") && NBTHelper.hasTag(stack, "yPosUsedOn") && NBTHelper.hasTag(stack, "yPosUsedOn"))
		{
			player.inventory.addItemStackToInventory(new ItemStack(Item.getItemFromBlock(world.getBlockState(pos).getBlock())));
			if(!world.isRemote)
				world.destroyBlock(pos, false);
		}
		
		// Clean NBTTags
		clearNBT(stack);
	}
	
	private void clearNBT(ItemStack stack)
	{
		NBTHelper.setBoolean(stack, "absorbing", false);
		NBTHelper.setInteger(stack, "xPosUsedOn", 0);
		NBTHelper.setInteger(stack, "yPosUsedOn", 0);
		NBTHelper.setInteger(stack, "zPosUsedOn", 0);
		NBTHelper.setInteger(stack, "counter", 0);
	}

	@SideOnly(Side.CLIENT)
	private void spawnParticles(EntityPlayer player, World world, BlockPos pos)
	{
		Vec3 blockPos = new Vec3(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
		for(int i = 0; i < 10; ++i)
		{
			Vec3 spawnPos = blockPos.addVector(Math.random() * 2.0D - 1.0D, Math.random() * 2.0D - 1.0D, Math.random() * 2.0D - 1.0D);
			
			EntityBlockDustFX particle = (EntityBlockDustFX)factory.func_178902_a(0, world, spawnPos.xCoord, spawnPos.yCoord, spawnPos.zCoord, 0.0D, 0.0D, 0.0D, Block.getStateId(player.worldObj.getBlockState(pos)));
			particle.setTarget(player);
			
			Minecraft.getMinecraft().effectRenderer.addEffect(particle);
		}
	}
}
