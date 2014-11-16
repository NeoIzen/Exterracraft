package com._izen_.exterracraft.reference;

public class Reference
{
	public static final String MOD_ID = "exterracraft";
	public static final String MOD_NAME = "Exterracraft";
	public static final String VERSION = "@VERSION@";
	private static final String PACKAGE = "com._izen_." + MOD_ID;
	public static final String CLIENT_PROXY_CLASS = PACKAGE + ".proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = PACKAGE + ".proxy.ServerProxy";
	public static final String GUI_FACTORY_CLASS = PACKAGE + ".client.gui.GuiFactory";
	
	// folder paths
	public static final String GUI_PATH = Reference.MOD_ID.toLowerCase() + ":" + "textures/gui/";
}
