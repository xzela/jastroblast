package org.doublelong.jastroblast.entity;

import org.doublelong.jastroblast.controller.ShipController;
import org.doublelong.jastroblast.renderer.ShipRenderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ship
{
	private final Space space;

	private static final float WIDTH = 1.5f;
	private static final float HEIGHT = 1f;

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

	public Ship(Space space)
	{
		this.space = space;
		this.position = new Vector2(1f, 1f);
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
		//		System.out.println(this.rotation);
		this.controller.update(delta);
		if(this.rotation > 360)
		{
			this.rotation = 0;
		}
		if(this.rotation < 0)
		{
			this.rotation = 360;
		}
	}

	public void dispose()
	{
		this.renderer.dispose();
	}
}
