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

	private final Asteroid asteroid;
	private final Asteroid asteroid2;

	public final float ppuX;
	public final float ppuY;


	public Space(JastroBlast game, float ppuX, float ppuY)
	{
		this.game = game;
		this.ppuX = ppuX;
		this.ppuY = ppuY;
		this.ship = new Ship(this);
		this.asteroid =  new Asteroid(this, new Vector2(4f * this.ppuX, 4f * this.ppuY));
		this.asteroid2 =  new Asteroid(this, new Vector2(5f * this.ppuX, 7f * this.ppuY));
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.ship.render(batch, cam);
		this.asteroid.render(batch, cam);
		this.asteroid2.render(batch, cam);
	}

	public void update(float delta)
	{
		this.ship.update(delta);
		this.asteroid.update(delta);
		this.asteroid2.update(delta);
	}

	public void dispose()
	{
		this.ship.dispose();
		this.asteroid.dispose();
		this.asteroid2.dispose();
	}
}
