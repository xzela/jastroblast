package org.doublelong.tests;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;

public class ProjectionViewportCamera implements ApplicationListener {
	private Mesh squarePartOne;
	private Mesh squarePartTwo;

	@Override
	public void create() {
		if (squarePartOne == null) {
			squarePartOne = new Mesh(true, 3, 3,
					new VertexAttribute(Usage.Position, 3, "a_position"),
					new VertexAttribute(Usage.ColorPacked, 4, "a_color"));

			squarePartOne.setVertices(new float[] {
					-0.5f, -0.5f, 0, Color.toFloatBits(128, 0, 0, 255),
					0.5f, -0.5f, 0, Color.toFloatBits(192, 0, 0, 255),
					-0.5f, 0.5f, 0, Color.toFloatBits(192, 0, 0, 255) });
			squarePartOne.setIndices(new short[] { 0, 1, 2});
		}

		if (squarePartTwo == null) {
			squarePartTwo = new Mesh(true, 3, 3,
					new VertexAttribute(Usage.Position, 3, "a_position"),
					new VertexAttribute(Usage.ColorPacked, 4, "a_color"));

			squarePartTwo.setVertices(new float[] {
					0.5f, -0.5f, 0, Color.toFloatBits(192, 0, 0, 255),
					-0.5f, 0.5f, 0, Color.toFloatBits(192, 0, 0, 255),
					0.5f, 0.5f, 0, Color.toFloatBits(255, 0, 0, 255) });
			squarePartTwo.setIndices(new short[] { 0, 1, 2});
		}
	}

	@Override
	public void dispose() { }

	@Override
	public void pause() { }

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		squarePartOne.render(GL10.GL_TRIANGLES, 0, 3);
		squarePartTwo.render(GL10.GL_TRIANGLES, 0, 3);
	}

	@Override
	public void resize(int width, int height) { }

	@Override
	public void resume() { }


}