package org.doublelong.jastroblast.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.doublelong.jastroblast.JastroBlast;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Space
{
	public final World world;
	private final JastroBlast game;

	public static final float HEIGHT = 10f;
	public static final float WIDTH = 12f;

	public final float ppuX;
	public final float ppuY;



	private final Ship ship;
	public Ship getShip() { return this.ship; }

	private final List<Asteroid> asteroids;
	public List<Asteroid> getAsteroids() { return this.asteroids; }

	public Rectangle viewport;

	public boolean debug = false;

	public Space(JastroBlast game, boolean debug, float ppuX, float ppuY)
	{
		this.world = new World(new Vector2(0,0), true); // no gravity

		this.game = game;
		this.debug = debug;
		this.ppuX = ppuX;
		this.ppuY = ppuY;
		this.ship = new Ship(this, new Vector2(0f, 0f));
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
		temp.add(new Asteroid(this, new Vector2(-220f, -150f)));
		temp.add(new Asteroid(this, new Vector2(-220f, 80f)));

		//		int flip = 1;
		//		for(int i = 0; i < num; i++)
		//		{
		//			float x = r.nextInt((int)(this.ppuX * 10 - 150)) + 1;
		//			System.out.println(x);
		//			float y = r.nextInt((int)(this.ppuY * 10 - 150)) + 1;
		//			Asteroid a = new Asteroid(this, new Vector2(flip * x, flip * y));
		//			temp.add(a);
		//			flip = flip * -1;
		//		}
		return temp;
	}
}
