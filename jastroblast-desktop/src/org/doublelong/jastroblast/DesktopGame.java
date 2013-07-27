package org.doublelong.jastroblast;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;


public class DesktopGame
{
	public static void main(String[] args)
	{
		JastroBlast game = new JastroBlast();
		new LwjglApplication(game, game.WINDOW_TITLE, game.WINDOW_WIDTH, game.WINDOW_HEIGHT, false);
	}

}
