package com._izen_.exterracraft.client.gui;

import java.io.IOException;

import com._izen_.exterracraft.reference.Reference;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiDataTablet extends GuiScreen
{
	private EntityPlayer		player;
	private World				world;
	private final int			xSize			= 176, ySize = 214;
	private final int			textOffset		= 5;
	private int					updateCount		= 0;
	private boolean				startupSequence	= true;
	private String				text			= "";
	
	private ResourceLocation	bgImage			= new ResourceLocation(Reference.GUI_PATH + "data_tablet.png");
	
	public GuiDataTablet(EntityPlayer player, World world, int x, int y, int z)
	{
		this.player = player;
		this.world = world;
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	@Override
	public void updateScreen()
	{
		super.updateScreen();
		++updateCount;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.mc.getTextureManager().bindTexture(bgImage);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
		
		if(this.startupSequence)
			drawStartup(x, y);
		else
			drawTabletData(x, y);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	private void drawTabletData(int x, int y)
	{
		String s1 = text;
		
		if(updateCount / 12 % 2 == 0)
		{
			s1 += EnumChatFormatting.DARK_GREEN + "_";
		}
		
		fontRendererObj.drawSplitString(s1, x + this.textOffset, y + this.textOffset, xSize - this.textOffset, 0);
	}
	
	private void drawStartup(int x, int y)
	{
		this.startupSequence = false;
	}
}
