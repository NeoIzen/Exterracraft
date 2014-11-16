package com._izen_.exterracraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.MathHelper;

import com._izen_.exterracraft.block.BlockExterraniumOre;
import com._izen_.exterracraft.init.ModBlocks;
import com._izen_.exterracraft.utility.LogHelper;

public class TileEntityExterraniumOre extends TileEntityEC implements TileEntityExRadiationEmitter
{
	private int powerLevel = 0;
	public static final int maxPowerLevel = 65535;
	
	public TileEntityExterraniumOre()
	{
	}
	
	@Override
	public void updateEntity()
	{
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		powerLevel = tag.getInteger("powerLevel");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		tag.setInteger("powerLevel", powerLevel);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
	       this.readFromNBT(packet.func_148857_g());
	}

	@Override
	public Packet getDescriptionPacket()
	{
	       NBTTagCompound var1 = new NBTTagCompound();

	       this.writeToNBT(var1);

	       return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, var1);
	}
	
	@Override
	public void setPowerLevel(int powerLevel)
	{
		if(this.powerLevel != powerLevel)
		{
			this.powerLevel = MathHelper.clamp_int(powerLevel, 0, this.maxPowerLevel);
			markDirty();
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	@Override
	public int getPowerLevel()
	{
		return powerLevel;
	}

	@Override
	public int getMaxPowerLevel()
	{
		return maxPowerLevel;
	}
}
