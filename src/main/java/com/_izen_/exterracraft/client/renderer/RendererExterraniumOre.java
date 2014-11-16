package com._izen_.exterracraft.client.renderer;

import org.lwjgl.opengl.GL11;

import com._izen_.exterracraft.tileentity.TileEntityExRadiationEmitter;
import com._izen_.exterracraft.tileentity.TileEntityExterraniumOre;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RendererExterraniumOre implements ISimpleBlockRenderingHandler
{
	public final static int renderId = RenderingRegistry.getNextAvailableRenderId();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		if(modelId == renderId)
		{
			Tessellator tessellator = Tessellator.instance;
		    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		    tessellator.startDrawingQuads();
		    tessellator.setNormal(0.0F, -1.0F, 0.0F);
		    renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
		    tessellator.draw();
		    tessellator.startDrawingQuads();
		    tessellator.setNormal(0.0F, 1.0F, 0.0F);
		    renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
		    tessellator.draw();
		    tessellator.startDrawingQuads();
		    tessellator.setNormal(0.0F, 0.0F, -1.0F);
		    renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
		    tessellator.draw();
		    tessellator.startDrawingQuads();
		    tessellator.setNormal(0.0F, 0.0F, 1.0F);
		    renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
		    tessellator.draw();
		    tessellator.startDrawingQuads();
		    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		    renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
		    tessellator.draw();
		    tessellator.startDrawingQuads();
		    tessellator.setNormal(1.0F, 0.0F, 0.0F);
		    renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
		    tessellator.draw();
		    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if(modelId == renderId)
		{
			TileEntity tileEntity = blockAccess.getTileEntity(x, y, z);
			if(tileEntity instanceof TileEntityExterraniumOre)
			{
				if(((TileEntityExterraniumOre)tileEntity).getPowerLevel() > 0)
					renderer.setOverrideBlockTexture(block.getIcon(blockAccess, x, y, z, blockAccess.getBlockMetadata(x, y, z)));
			}
			
			renderer.renderStandardBlock(block, x, y, z);
			renderer.clearOverrideBlockTexture();
		}
				
		
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		if(modelId == renderId)
			return true;
		else
			return false;
	}

	@Override
	public int getRenderId()
	{
		return renderId;
	}
}
