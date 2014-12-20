package com._izen_.exterracraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.MathHelper;

import com._izen_.exterracraft.utility.InfusionHelper;

public class TileEntityExterraniumOre extends TileEntityEC implements
		TileEntityExRadiationEmitter
{
	private int				powerLevel			= 0;
	private int				dirtAndGrassMeta	= 0;
	public static final int	maxPowerLevel		= 65535;
	
	public TileEntityExterraniumOre()
	{
	}
	
	/*
	 * @Override public void updateEntity() { super.updateEntity(); }
	 */
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		this.powerLevel = tag.getInteger("powerLevel");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		tag.setInteger("powerLevel", this.powerLevel);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		this.readFromNBT(packet.getNbtCompound());
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound var1 = new NBTTagCompound();
		
		this.writeToNBT(var1);
		
		return new S35PacketUpdateTileEntity(this.pos, 1, var1);
	}
	
	@Override
	public void setPowerLevel(int powerLevel)
	{
		if(this.powerLevel != powerLevel)
		{
			this.powerLevel = MathHelper.clamp_int(powerLevel, 0, this.maxPowerLevel);
			markDirty();
			worldObj.markBlockForUpdate(this.pos);
		}
		
		if(this.dirtAndGrassMeta != InfusionHelper.powerLevelToMeta(this.powerLevel))
		{
			this.dirtAndGrassMeta = InfusionHelper.powerLevelToMeta(this.powerLevel);
			/*
			 * InfusionHelper.InfuseDirtAndGrass(this.worldObj, this.xCoord,
			 * this.yCoord, this.zCoord, this.dirtAndGrassMeta);
			 * 
			 * InfusionHelper.triggerUpdate(this.worldObj, this.xCoord - 1,
			 * this.yCoord, this.zCoord);
			 * InfusionHelper.triggerUpdate(this.worldObj, this.xCoord + 1,
			 * this.yCoord, this.zCoord);
			 * InfusionHelper.triggerUpdate(this.worldObj, this.xCoord,
			 * this.yCoord - 1, this.zCoord);
			 * InfusionHelper.triggerUpdate(this.worldObj, this.xCoord,
			 * this.yCoord + 1, this.zCoord);
			 * InfusionHelper.triggerUpdate(this.worldObj, this.xCoord,
			 * this.yCoord, this.zCoord - 1);
			 * InfusionHelper.triggerUpdate(this.worldObj, this.xCoord,
			 * this.yCoord, this.zCoord + 1);
			 */
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
