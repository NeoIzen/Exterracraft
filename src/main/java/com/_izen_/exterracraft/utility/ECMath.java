package com._izen_.exterracraft.utility;

public class ECMath
{
	public static double lengthSq(double x, double z)
	{
		return (x * x) + (z * z);
	}
	
	public static double lengthSq(double x, double y, double z)
	{
		return (x * x) + (y * y) + (z * z);
	}
}
