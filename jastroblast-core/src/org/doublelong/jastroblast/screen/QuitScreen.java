package org.doublelong.jastroblast.screen;

import org.doublelong.jastroblast.JastroBlast;

import com.badlogic.gdx.Gdx;

public class QuitScreen extends AbstractScreen
{
	public QuitScreen(JastroBlast game)
	{
		super(game);
	}

	@Override
	public void render(float delta)
	{
		Gdx.app.exit();
	}
}
