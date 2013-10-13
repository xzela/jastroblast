package org.doublelong.jastroblast.screen;

import org.doublelong.jastroblast.JastroBlast;
import org.doublelong.jastroblast.entity.Space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class JastroScreen extends AbstractScreen
{

	private static final int VIRTUAL_WIDTH = 800;
	private static final int VIRTUAL_HEIGHT = 600;

	private static final float ASPECT_RATIO = (float) VIRTUAL_WIDTH / VIRTUAL_HEIGHT;

	public Rectangle viewport;

	private OrthographicCamera cam;
	private final SpriteBatch batch;
	private final Space space;

	//private final Inputs input;
	public final float ppuX;
	public final float ppuY;

	public ShapeRenderer debugRenderer = new ShapeRenderer();
	public BitmapFont debugFont = new BitmapFont();

	public int i;

	public boolean debug = false;

	public JastroScreen(JastroBlast game)
	{
		super(game);
		// define the pixels
		this.debug = JastroBlast.DEBUG;
		this.ppuX = game.WINDOW_WIDTH / Space.WIDTH;
		this.ppuY = game.WINDOW_HEIGHT / Space.HEIGHT;

		this.space = new Space(game, debug, this.ppuX, this.ppuY);

		this.batch = new SpriteBatch();
		this.cam = new OrthographicCamera();

		//this.input = new Inputs(this.space.getShip().controller);

	}

	@Override
	public void render(float delta)
	{
		this.space.viewport = this.viewport;
		this.cam.update();

		//Gdx.gl.glViewport((int)this.viewport.x, (int)this.viewport.y, (int)this.viewport.width, (int)this.viewport.height);
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.cam.apply(Gdx.gl10);

		this.batch.setProjectionMatrix(this.cam.combined);

		this.space.render(this.batch, this.cam);
		if(this.debug)
		{
			// draw height line
			this.debugRenderer.setProjectionMatrix(this.cam.combined);
			this.debugRenderer.begin(ShapeType.Line);
			this.debugRenderer.identity();
			this.debugRenderer.setColor(Color.GREEN);
			this.debugRenderer.line(0, 0, 0, VIRTUAL_HEIGHT / 2);
			this.debugRenderer.end();


			this.batch.begin();
			this.debugFont.setColor(Color.GREEN);
			//			this.debugFont.setScale(.5f, .5f);
			this.debugFont.draw(this.batch, "0x0", 0, 0);
			this.batch.end();

			// draw width line
			this.debugRenderer.begin(ShapeType.Line);
			this.debugRenderer.identity();
			this.debugRenderer.setColor(Color.PINK);
			this.debugRenderer.line(0, 0, VIRTUAL_WIDTH / 2, 0);
			this.debugRenderer.end();

		}
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
		this.cam.translate(this.viewport.width / 2, this.viewport.height / 2, 0);
		//this.cam = new OrthographicCamera(this.viewport.width / 2,  this.viewport.height / 2);
		this.cam = new OrthographicCamera(width,  height);
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		this.space.dispose();
	}
}
