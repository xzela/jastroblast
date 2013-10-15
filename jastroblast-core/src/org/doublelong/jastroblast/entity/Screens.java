package org.doublelong.jastroblast.entity;

import org.doublelong.jastroblast.screen.CreditsScreen;
import org.doublelong.jastroblast.screen.JastroScreen;
import org.doublelong.jastroblast.screen.LoadingScreen;
import org.doublelong.jastroblast.screen.MainScreen;
import org.doublelong.jastroblast.screen.QuitScreen;

import com.badlogic.gdx.Screen;

public enum Screens
{
	LOADING {
		@Override
		public Screen getScreenInstance()
		{
			return new LoadingScreen();
		}
	},
	MAIN {
		@Override
		public Screen getScreenInstance()
		{
			return new MainScreen();
		}
	},
	GAME {
		@Override
		public Screen getScreenInstance()
		{
			return new JastroScreen();
		}
	},
	CREDITS {
		@Override
		public Screen getScreenInstance()
		{
			return new CreditsScreen();
		}
	},
	QUIT {
		@Override
		public Screen getScreenInstance()
		{
			return new QuitScreen();
		}
	};

	public abstract Screen getScreenInstance();
}
