package org.doublelong.tests;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class ProjectionViewportCamera implements ApplicationListener
{
	private OrthographicCamera camera;
	private ShapeRenderer renderer;

	private int i;

	@Override
	public void create()
	{
		renderer = new ShapeRenderer();
	}

	@Override
	public void dispose() { }

	@Override
	public void pause() { }

	@Override
	public void render()
	{
		camera.update();
		camera.apply(Gdx.gl10);

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		this.renderer.setProjectionMatrix(camera.combined);
		this.renderer.begin(ShapeType.Rectangle);
		this.renderer.setColor(Color.BLUE);
		this.renderer.identity();
		this.renderer.translate(.25f, .25f, 0f);
		this.renderer.rotate(0, 0, 1, this.i);

		this.renderer.rect(-.25f / 2, -.25f / 2, .25f, .25f);


		this.renderer.end();
		i++;
	}

	@Override
	public void resize(int width, int height)
	{
		float aspectRatio = (float) width / (float) height;
		camera = new OrthographicCamera(2f * aspectRatio, 2f);
	}

	@Override
	public void resume() { }


}