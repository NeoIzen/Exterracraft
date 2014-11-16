package com._izen_.exterracraft.client.handler;

import com._izen_.exterracraft.client.settings.KeyBindings;
import com._izen_.exterracraft.reference.Key;
import com._izen_.exterracraft.utility.LogHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputEventHandler
{
	private static Key getPressedKeyBinding()
	{
		if(KeyBindings.charge.isPressed())
		{
			return Key.CHARGE;
		}
		else if(KeyBindings.release.isPressed())
		{
			return Key.RELEASE;
		}
		
		return Key.UNKNOWN;
	}
	
	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
	{
		//LogHelper.info(getPressedKeyBinding());
	}
}
