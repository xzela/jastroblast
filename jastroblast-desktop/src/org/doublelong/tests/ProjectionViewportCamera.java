package org.doublelong.tests;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class ProjectionViewportCamera implements ApplicationListener
{
	private OrthographicCamera camera;
	private ShapeRenderer renderer;

	public Mesh squareMesh;

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

		if (this.squareMesh == null) {
			this.squareMesh = new Mesh(true, 4, 4,
					new VertexAttribute(Usage.Position, 3, "a_position"),
					new VertexAttribute(Usage.ColorPacked, 4, "a_color"));

			this.squareMesh.setVertices(new float[] {
					-0.5f, -0.5f, 0, Color.toFloatBits(128, 0, 0, 255),
					0.5f, -0.5f, 0, Color.toFloatBits(192, 0, 0, 255),
					-0.5f, 0.5f, 0, Color.toFloatBits(192, 0, 0, 255),
					0.5f, 0.5f, 0, Color.toFloatBits(255, 0, 0, 255) });
			this.squareMesh.setIndices(new short[] { 0, 1, 2, 3});
		}

		camera.update();
		camera.apply(Gdx.gl10);

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		this.squareMesh.render(GL10.GL_TRIANGLE_STRIP, 0, 4);

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