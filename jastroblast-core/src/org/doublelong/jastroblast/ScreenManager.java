package org.doublelong.jastroblast;

import org.doublelong.jastroblast.screen.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.IntMap;

public final class ScreenManager
{
	private static ScreenManager instance;

	private JastroBlast game;

	private IntMap<Screen> screens;

	private ScreenManager() {
		screens = new IntMap<Screen>();
	}

	public static ScreenManager getInstance()
	{
		if (null == instance)
		{
			instance = new ScreenManager();
		}
		return instance;
	}

	public void initialize(JastroBlast game)
	{
		this.game = game;
	}

	public void show(Screens screen)
	{
		if (null == game)
		{
			return;
		}
		if (!screens.containsKey(screen.ordinal()))
		{
			screens.put(screen.ordinal(), screen.getScreenInstance());
		}
		this.game.setScreen(screens.get(screen.ordinal()));
	}

	public void dispose(Screens screen)
	{
		if(!screens.containsKey(screen.ordinal()))
		{
			return;
		}
		screens.remove(screen.ordinal()).dispose();
	}

	public void dispose()
	{
		for (Screen screen : screens.values())
		{
			screen.dispose();
		}
		screens.clear();
		instance = null;
	}
}
