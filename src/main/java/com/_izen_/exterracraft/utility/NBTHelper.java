package com._izen_.exterracraft.utility;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper
{
	public static boolean hasTag(ItemStack itemStack, String keyName)
	{
		return itemStack != null && itemStack.getTagCompound() != null && itemStack.getTagCompound().hasKey(keyName);
	}
	
	public static void removeTag(ItemStack itemStack, String keyName)
	{
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt != null)
		{
			nbt.removeTag(keyName);
		}
		itemStack.setTagCompound(nbt);
	}
	
	/**
	 * Initializes the NBT Tag Compound for the given ItemStack if it is null
	 *
	 * @param itemStack
	 *            The ItemStack for which its NBT Tag Compound is being checked
	 *            for initialization
	 */
	private static void initNBTTagCompound(ItemStack itemStack)
	{
		if(itemStack.getTagCompound() == null)
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}
	}
	
	public static void setLong(ItemStack itemStack, String keyName, long keyValue)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		nbt.setLong(keyName, keyValue);
		itemStack.setTagCompound(nbt);
	}
	
	// String
	public static String getString(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		if(!nbt.hasKey(keyName))
		{
			setString(itemStack, keyName, "");
		}
		
		return nbt.getString(keyName);
	}
	
	public static void setString(ItemStack itemStack, String keyName, String keyValue)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		nbt.setString(keyName, keyValue);
		itemStack.setTagCompound(nbt);
	}
	
	// boolean
	public static boolean getBoolean(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		if(!nbt.hasKey(keyName))
		{
			setBoolean(itemStack, keyName, false);
		}
		
		return nbt.getBoolean(keyName);
	}
	
	public static void setBoolean(ItemStack itemStack, String keyName, boolean keyValue)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		nbt.setBoolean(keyName, keyValue);
		itemStack.setTagCompound(nbt);
	}
	
	// byte
	public static byte getByte(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		if(!nbt.hasKey(keyName))
		{
			setByte(itemStack, keyName, (byte)0);
		}
		
		return nbt.getByte(keyName);
	}
	
	public static void setByte(ItemStack itemStack, String keyName, byte keyValue)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		nbt.setByte(keyName, keyValue);
		itemStack.setTagCompound(nbt);
	}
	
	// short
	public static short getShort(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		if(!nbt.hasKey(keyName))
		{
			setShort(itemStack, keyName, (short)0);
		}
		
		return nbt.getShort(keyName);
	}
	
	public static void setShort(ItemStack itemStack, String keyName, short keyValue)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		nbt.setShort(keyName, keyValue);
		itemStack.setTagCompound(nbt);
	}
	
	// int
	public static int getInt(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		if(!nbt.hasKey(keyName))
		{
			setInteger(itemStack, keyName, 0);
		}
		
		return nbt.getInteger(keyName);
	}
	
	public static void setInteger(ItemStack itemStack, String keyName, int keyValue)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		nbt.setInteger(keyName, keyValue);
		itemStack.setTagCompound(nbt);
	}
	
	// long
	public static long getLong(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		if(!nbt.hasKey(keyName))
		{
			setLong(itemStack, keyName, 0);
		}
		
		return nbt.getLong(keyName);
	}
	
	// float
	public static float getFloat(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		if(!nbt.hasKey(keyName))
		{
			setFloat(itemStack, keyName, 0);
		}
		
		return nbt.getFloat(keyName);
	}
	
	public static void setFloat(ItemStack itemStack, String keyName, float keyValue)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		nbt.setFloat(keyName, keyValue);
		itemStack.setTagCompound(nbt);
	}
	
	// double
	public static double getDouble(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		if(!nbt.hasKey(keyName))
		{
			setDouble(itemStack, keyName, 0);
		}
		
		return nbt.getDouble(keyName);
	}
	
	public static void setDouble(ItemStack itemStack, String keyName, double keyValue)
	{
		initNBTTagCompound(itemStack);

		NBTTagCompound nbt = itemStack.getTagCompound();
		nbt.setDouble(keyName, keyValue);
		itemStack.setTagCompound(nbt);
	}
}