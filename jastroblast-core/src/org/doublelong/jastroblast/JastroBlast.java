package org.doublelong.jastroblast;

import org.doublelong.jastroblast.screen.LoadingScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class JastroBlast extends Game
{
	public final String WINDOW_TITLE = "jAstroBlast";
	public final int WINDOW_WIDTH = 800;
	public final int WINDOW_HEIGHT = 600;

	public AssetManager manager = new AssetManager();

	@Override
	public void create()
	{
		Texture.setEnforcePotImages(false);
		this.setScreen(new LoadingScreen(this));
	}
}
