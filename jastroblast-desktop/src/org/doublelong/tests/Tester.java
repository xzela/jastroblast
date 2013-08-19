package org.doublelong.tests;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Tester
{
	public static void main(String[] args)
	{
		Box2dTest game = new Box2dTest();
		new LwjglApplication(game);
	}
}
