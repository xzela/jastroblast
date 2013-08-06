package org.doublelong.jastroblast.screen;

import org.doublelong.jastroblast.Inputs;
import org.doublelong.jastroblast.JastroBlast;
import org.doublelong.jastroblast.entity.Space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class JastroScreen implements Screen
{

	private static final int VIRTUAL_WIDTH = 800;
	private static final int VIRTUAL_HEIGHT = 600;

	private static final float ASPECT_RATIO = (float) VIRTUAL_WIDTH / VIRTUAL_HEIGHT;

	public Rectangle viewport;

	private OrthographicCamera cam;
	private final SpriteBatch batch;
	private final Space space;

	private final Inputs input;
	public final float ppuX;
	public final float ppuY;

	public ShapeRenderer debugRenderer = new ShapeRenderer();

	public int i;

	public JastroScreen(JastroBlast game)
	{
		// define the pixels
		this.ppuX = game.WINDOW_WIDTH / Space.WIDTH;
		this.ppuY = game.WINDOW_HEIGHT / Space.HEIGHT;

		this.space = new Space(game, this.ppuX, this.ppuY);

		this.batch = new SpriteBatch();
		this.cam = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		//this.cam.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);

		this.input = new Inputs(this.space.getShip().controller);

	}

	@Override
	public void render(float delta)
	{
		this.space.viewport = this.viewport;
		this.cam.update();
		this.batch.setProjectionMatrix(this.cam.combined);
		this.cam.apply(Gdx.gl10);

		Gdx.gl.glViewport((int)this.viewport.x, (int)this.viewport.y, (int)this.viewport.width, (int)this.viewport.height);

		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);



		this.debugRenderer.setProjectionMatrix(this.cam.combined);
		this.debugRenderer.begin(ShapeType.Rectangle);
		this.debugRenderer.setColor(Color.BLUE);
		this.debugRenderer.identity();
		this.debugRenderer.translate(5, 5, 1f);
		this.debugRenderer.rotate(0, 0, 1, this.i);
		this.debugRenderer.rect(-10, -10, 20, 20);
		this.debugRenderer.end();

		this.i++;
		this.space.render(this.batch, this.cam);
		this.update(delta);
	}

	public void update(float delta)
	{
		this.space.update(delta);
	}

	@Override
	public void resize(int width, int height)
	{
		float aspectRatio = (float) width / height;
		float scale = 1f;
		Vector2 crop = new Vector2(0f, 0f);

		if(aspectRatio > ASPECT_RATIO)
		{
			scale = (float) height / VIRTUAL_HEIGHT;
			crop.x = (width - VIRTUAL_WIDTH * scale) / 2f;
		}
		else if(aspectRatio < ASPECT_RATIO)
		{
			scale = (float) width / VIRTUAL_WIDTH;
			crop.y = (height - VIRTUAL_HEIGHT * scale) / 2f;
		}
		else
		{
			scale = (float) width / VIRTUAL_WIDTH;
		}

		float w = VIRTUAL_WIDTH * scale;
		float h = VIRTUAL_HEIGHT * scale;

		this.viewport = new Rectangle(crop.x, crop.y, w, h);
		//this.cam.translate(this.viewport.width / 2, this.viewport.height / 2, 0);
		this.cam = new OrthographicCamera(this.viewport.width / 2,  this.viewport.height / 2);
	}

	@Override
	public void show()
	{
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(this.input);
	}

	@Override
	public void hide()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		this.space.dispose();
	}

}
