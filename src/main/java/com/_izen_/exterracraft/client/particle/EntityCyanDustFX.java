package com._izen_.exterracraft.client.particle;

import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.world.World;

public class EntityCyanDustFX extends EntityReddustFX
{
	public EntityCyanDustFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ)
    {
        this(world, x, y, z, 1.0F, motionX, motionY, motionZ);
    }

    public EntityCyanDustFX(World world, double x, double y, double z, float scale, double motionX, double motionY, double motionZ)
    {
        super(world, x, y, z, scale, 0.0F, 0.0F, 0.0F);

        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        this.particleRed = 0.584F;
        this.particleGreen = 0.812F;
        this.particleBlue = 0.737F;
    }
}
