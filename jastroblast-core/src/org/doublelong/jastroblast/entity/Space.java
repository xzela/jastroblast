package org.doublelong.jastroblast.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	private final List<Asteroid> asteroids;
	public List<Asteroid> getAsteroids() { return this.asteroids; }

	public final float ppuX;
	public final float ppuY;


	public Space(JastroBlast game, float ppuX, float ppuY)
	{
		this.game = game;
		this.ppuX = ppuX;
		this.ppuY = ppuY;
		this.ship = new Ship(this);
		this.asteroids = this.generateAsteroids(3);
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.ship.render(batch, cam);
		for(Asteroid a : this.asteroids)
		{
			a.render(batch, cam);
		}
	}

	public void update(float delta)
	{
		this.ship.update(delta);
		for(Asteroid a : this.asteroids)
		{
			a.update(delta);
		}
	}

	public void dispose()
	{
		this.ship.dispose();
		for(Asteroid a : this.asteroids)
		{
			a.dispose();
		}
	}

	private List<Asteroid> generateAsteroids(int num)
	{
		Random r = new Random();
		List<Asteroid> temp = new ArrayList<Asteroid>();
		for(int i = 0; i < num; i++)
		{
			float x = r.nextInt((int)(this.ppuX * WIDTH - 50)) + 1;
			float y = r.nextInt((int)(this.ppuY * HEIGHT - 50)) + 1;
			Asteroid a = new Asteroid(this, new Vector2(x, y));
			temp.add(a);
		}
		return temp;
	}
}
