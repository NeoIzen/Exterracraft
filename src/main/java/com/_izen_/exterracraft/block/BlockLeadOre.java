package com._izen_.exterracraft.block;

import net.minecraft.init.Blocks;

public class BlockLeadOre extends BlockEC
{
	public BlockLeadOre()
	{
		super();
		this.setBlockName("lead_ore");
		this.setHardness(3F);
		this.setResistance(5F);
		this.setStepSound(soundTypePiston);
	}
}
