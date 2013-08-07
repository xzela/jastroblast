package org.doublelong.jastroblast.entity;

import java.util.List;

import org.doublelong.jastroblast.controller.ShipController;
import org.doublelong.jastroblast.renderer.ShipRenderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ship
{
	private final Space space;

	// TODO fix Height/Width, should be associated with the sprite
	private static final float WIDTH = 100f;
	private static final float HEIGHT = 76f;

	private Vector2 position;
	public Vector2 getPosition() {return this.position; }
	public void setPosition(Vector2 position) { this.position = position;}

	private Rectangle bounds;
	public Rectangle getBounds() { return this.bounds;}
	public void setBounds(Rectangle bounds) { this.bounds = bounds; }

	public ShipRenderer renderer;
	public ShipController controller;

	private float rotation = 0;
	public float getRotation() { return this.rotation; }
	public void setRotation(float rotation) { this.rotation = rotation;}

	private Vector2 acceleration = new Vector2();
	public Vector2 getAcceleration() { return this.acceleration; }
	public void setAcceleration(Vector2 acceleration) { this.acceleration = acceleration; }

	private Vector2 velocity = new Vector2();
	public Vector2 getVelocity() { return this.velocity; }
	public void setVelocity(Vector2 velocity) { this.velocity = velocity; }

	public Vector2 ppu;

	public Ship(Space space, Vector2 position)
	{
		this.space = space;
		this.ppu = new Vector2(this.space.ppuX, this.space.ppuY);
		this.position = position;
		this.bounds = new Rectangle(this.position.x, this.position.y, Ship.WIDTH, Ship.HEIGHT);

		this.renderer = new ShipRenderer(this);
		this.controller = new ShipController(this.space);
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.renderer.render(batch, cam);
	}

	public void update(float delta)
	{
		this.controller.update(delta);
		if(this.rotation > 360)
		{
			this.rotation = 0;
		}
		if(this.rotation < 0)
		{
			this.rotation = 360;
		}

		List<Asteroid> list = this.space.getAsteroids();
		for (Asteroid a: list)
		{
			if(this.renderer.hb.overlaps(a.renderer.hb))
			{
				System.out.println("Hitting astroid");
				a.renderer.debugHit = true;
			}
			else
			{
				a.renderer.debugHit = false;
			}
		}
	}

	public void dispose()
	{
		this.renderer.dispose();
	}
}
