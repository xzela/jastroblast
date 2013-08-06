package org.doublelong.tests;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;


public class ProjectionViewportCameraTest
{
	public static void main(String[] args)
	{
		ProjectionViewportCamera game = new ProjectionViewportCamera();
		new LwjglApplication(game);
	}

}