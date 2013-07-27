package org.doublelong.jastroblast;

import org.doublelong.jastroblast.screen.JastroScreen;

import com.badlogic.gdx.Game;

public class JastroBlast extends Game
{
	public final String WINDOW_TITLE = "jAstroBlast";
	public final int WINDOW_WIDTH = 600;
	public final int WINDOW_HEIGHT = 600;

	private JastroScreen jastroScreen;

	@Override
	public void create()
	{
		this.initialize();
		this.setScreen(this.jastroScreen);
	}

	private void initialize()
	{
		this.jastroScreen = new JastroScreen();
	}
}
