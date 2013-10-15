package org.doublelong.jastroblast.screen;

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
	}
	;

	public abstract Screen getScreenInstance();
}
