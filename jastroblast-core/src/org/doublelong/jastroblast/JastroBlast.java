package org.doublelong.jastroblast;

import org.doublelong.jastroblast.screen.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class JastroBlast extends Game
{
	public final String WINDOW_TITLE = "jAstroBlast";
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;

	public static AssetManager manager = new AssetManager();
	public static final boolean DEBUG = true;

	@Override
	public void create()
	{
		ScreenManager.getInstance().initialize(this);
		Texture.setEnforcePotImages(false);
		ScreenManager.getInstance().show(Screens.LOADING);
	}

	@Override
	public void dispose()
	{
		super.dispose();
		ScreenManager.getInstance().dispose();
	}
}
