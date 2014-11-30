package com._izen_.exterracraft.client.gui;

import com._izen_.exterracraft.reference.Reference;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiDataTablet extends GuiScreen
{
	private int x, y, z;
	private EntityPlayer player;
	private World world;
	private int xSize, ySize;
	
	private ResourceLocation bgImage = new ResourceLocation(Reference.GUI_PATH + "data_tablet.png");
	
	public GuiDataTablet(EntityPlayer player, World world, int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.player = player;
		this.world = world;
		this.xSize = 176;
		this.ySize = 214;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float renderPartialTicks)
	{
		this.mc.getTextureManager().bindTexture(bgImage);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}
