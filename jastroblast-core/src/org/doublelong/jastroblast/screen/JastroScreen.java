package org.doublelong.jastroblast.screen;

import org.doublelong.jastroblast.JastroBlast;
import org.doublelong.jastroblast.entity.Space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class JastroScreen extends AbstractScreen
{
	private final Space space;

	private static final int VIRTUAL_WIDTH = 800;
	private static final int VIRTUAL_HEIGHT = 600;

	private static final float ASPECT_RATIO = (float) VIRTUAL_WIDTH / VIRTUAL_HEIGHT;

	public Rectangle viewport;

	private OrthographicCamera camera;
	public OrthographicCamera getCamera() { return this.camera; }

	private final SpriteBatch batch;
	public SpriteBatch getBatch() { return this.batch; }

	private final Box2DDebugRenderer boxDebugRenderer;

	public boolean debug = false;

	private static final float BOX_STEP = 1/60f;
	private static final int BOX_VELOCITY_ITERATIONS = 6;
	private static final int BOX_POSITION_ITERATIONS = 2;


	public static final float WORLD_TO_BOX = 0.01f;
	public static float convertToBox(float x) { return x * WORLD_TO_BOX; }

	public static final float BOX_TO_WORLD = 100f;
	public static float convertToWorld(float x) { return x * BOX_TO_WORLD;}

	public JastroScreen()
	{
		this.debug = JastroBlast.DEBUG;
		this.batch = new SpriteBatch();

		this.camera = new OrthographicCamera();
		this.camera.viewportHeight = JastroBlast.WINDOW_HEIGHT;
		this.camera.viewportWidth = JastroBlast.WINDOW_WIDTH;
		this.camera.position.set(this.camera.viewportWidth / 2, this.camera.viewportHeight / 2 , 0f);


		this.space = new Space(this.camera, this.debug);
		this.boxDebugRenderer = new Box2DDebugRenderer();
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		this.camera.update();

		this.boxDebugRenderer.render(this.space.getWorld(), this.camera.combined);

		this.space.render(this.batch, this.camera);
		this.space.getWorld().step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);

		this.update(delta);
	}

	public void update(float delta)
	{
		this.space.update(delta);
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		this.space.dispose();
	}

	@Override
	public void transitionScreen() {
		// TODO Auto-generated method stub

	}
}
