package org.doublelong.tests;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ProjectionViewportCamera implements ApplicationListener
{
	private OrthographicCamera camera;
	private ShapeRenderer renderer;

	private Rectangle r;
	private final HashMap<String, Float> rv = new HashMap<String, Float>();

	private final Vector2 position = new Vector2();

	@Override
	public void create()
	{
		this.renderer = new ShapeRenderer();
		this.r = new Rectangle();
		this.position.x = .25f;
		this.position.y = .25f;
		this.r.set(this.position.x, this.position.y, .5f, .5f);
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

		this.rv.put("LL", this.position.x);
		this.rv.put("LR", this.position.x + this.r.width);
		this.rv.put("UL", this.position.x + this.r.height);
		this.rv.put("UR", this.r.width + this.r.height);

		this.renderer.setProjectionMatrix(camera.combined);
		this.renderer.begin(ShapeType.Rectangle);
		this.renderer.setColor(Color.BLUE);
		this.renderer.identity();
		this.renderer.translate(this.position.x, this.position.y, 0f);
		this.renderer.rotate(0, 0, 1, 5);
		this.renderer.rect(-this.r.width / 2, -this.r.height / 2, this.r.width, this.r.height);

		//		this.renderer.rect(this.position.x, this.position.y, this.r.width, this.r.height);
		this.renderer.end();



		// draw the right side of 'r'
		this.renderer.begin(ShapeType.Line);
		this.renderer.setColor(Color.GREEN);
		this.renderer.identity();
		this.renderer.line(this.r.width * 1.5f, this.r.height / 2, this.position.x, this.position.y);
		this.renderer.end();

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