package com._izen_.exterracraft.client.settings;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;

import com._izen_.exterracraft.reference.Names;

public class KeyBindings
{
	public static KeyBinding charge = new KeyBinding(Names.Keys.CHARGE, Keyboard.KEY_C, Names.Keys.CATEGORY);
	public static KeyBinding release = new KeyBinding(Names.Keys.RELEASE, Keyboard.KEY_R, Names.Keys.CATEGORY);
}
