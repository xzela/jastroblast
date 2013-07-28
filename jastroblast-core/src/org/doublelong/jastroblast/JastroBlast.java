package org.doublelong.jastroblast;

import org.doublelong.jastroblast.screen.JastroScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;

public class JastroBlast extends Game
{
	public final String WINDOW_TITLE = "jAstroBlast";
	public final int WINDOW_HEIGHT = 600;
	public final int WINDOW_WIDTH = WINDOW_HEIGHT / 9 * 10;

	private JastroScreen jastroScreen;

	@Override
	public void create()
	{
		Texture.setEnforcePotImages(false);
		this.initialize();
		this.setScreen(this.jastroScreen);
	}

	private void initialize()
	{
		this.jastroScreen = new JastroScreen(this);
	}
}
