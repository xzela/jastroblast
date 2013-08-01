package org.doublelong.jastroblast.entity;

import org.doublelong.jastroblast.renderer.AsteroidRenderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Asteroid
{

	private final Space space;

	private final Vector2 position;
	public Vector2 getPosition() { return this.position; }

	private Rectangle bounds;
	public Rectangle getBounds() { return this.bounds; }

	private float angle;
	public float getAngle() { return this.angle; }
	public void setAngle(float angle) { this.angle = angle; }

	private final float spin;
	private final int direction;

	public AsteroidRenderer renderer;

	public Asteroid(Space space, Vector2 position)
	{
		this.space = space;
		this.position = position;
		this.spin = (float) Math.random() * 100;
		this.direction = -1; //new Random().nextBoolean() ? 1 : -1;

		this.renderer = new AsteroidRenderer(this);
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.renderer.render(batch, cam);
	}

	public void update(float delta)
	{
		System.out.println(this.angle);
		this.angle += this.direction * this.spin * delta;
		//this.setAngle(this.direction * this.spin * delta);
	}

	public void dispose()
	{
		this.renderer.dispose();
	}
}
