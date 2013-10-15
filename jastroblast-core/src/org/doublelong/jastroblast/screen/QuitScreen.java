package org.doublelong.jastroblast.screen;

import com.badlogic.gdx.Gdx;

public class QuitScreen extends AbstractScreen
{
	public QuitScreen() { }

	@Override
	public void render(float delta)
	{
		Gdx.app.exit();
	}
}
