package com._izen_.exterracraft.client.particle;

import com._izen_.exterracraft.utility.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBlockDustFX extends net.minecraft.client.particle.EntityBlockDustFX
{
	private EntityLivingBase target;
	
    protected EntityBlockDustFX(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, IBlockState p_i46281_14_)
    {
        super(world, posX, posY, posZ, 0.0D, 0.0D, 0.0D, p_i46281_14_);
        this.particleGravity = 0.0F;
    }

    @Override
    public void onUpdate()
    {
    	// calclate new velocity
    	Vec3 pos = new Vec3(this.posX, this.posY, this.posZ);
    	Vec3 velocity = target.getPositionEyes(0.75F).subtract(pos);
		velocity = new Vec3(velocity.xCoord, velocity.yCoord, velocity.zCoord);
		velocity.normalize();
		velocity = new Vec3(velocity.xCoord * 0.05D, velocity.yCoord * 0.05D, velocity.zCoord * 0.05D);
    	
		this.setVelocity(velocity.xCoord, velocity.yCoord, velocity.zCoord);
    	
		this.particleScale *= 0.1;
		
    	// execute normal update
    	super.onUpdate();
    }
    
    public void setTarget(EntityLivingBase entity)
    {
    	target = entity;
    }
    
    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory
        {
            public EntityFX func_178902_a(int p_178902_1_, World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, int ... p_178902_15_)
            {
                IBlockState iblockstate = Block.getStateById(p_178902_15_[0]);
                return iblockstate.getBlock().getRenderType() == -1 ? null : (new EntityBlockDustFX(world, posX, posY, posZ, motionX, motionY, motionZ, iblockstate)).func_174845_l();
            }
        }
}
