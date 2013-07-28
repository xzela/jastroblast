package org.doublelong.jastroblast.entity;

import org.doublelong.jastroblast.JastroBlast;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Space
{
	public static final float HEIGHT = 10f;
	public static final float WIDTH = 12f;

	private final JastroBlast game;

	private final Ship ship;
	public Ship getShip() { return this.ship; }

	private final SpriteBatch batch;

	public Space(JastroBlast game)
	{
		this.game = game;
		this.ship = new Ship(new Vector2(1f,1f));

		// locale
		this.batch = new SpriteBatch();
	}


	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.batch.begin();
		this.ship.render(batch, cam);
		this.batch.end();
	}

	public void update(float delta)
	{

	}

	public void dispose()
	{
		this.ship.dispose();
	}
}
