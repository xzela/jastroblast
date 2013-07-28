package org.doublelong.jastroblast.entity;

import org.doublelong.jastroblast.renderer.ShipRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ship
{
	private static final float WIDTH = .75f;
	private static final float HEIGHT = .5f;

	private final Texture texture;
	public Texture getTexture() {return this.texture; }

	private Vector2 position;
	public Vector2 getPosition() {return this.position; }
	public void setPosition(Vector2 position) { this.position = position;}

	private Rectangle bounds;
	public Rectangle getBounds() { return this.bounds;}
	public void setBounds(Rectangle bounds) { this.bounds = bounds; }

	public ShipRenderer renderer;

	public Ship(Vector2 position)
	{
		this.texture = new Texture(Gdx.files.internal("assets/images/player.png"));

		this.position = position;
		//		this.bounds = new Rectangle(this.position.x, this.position.y, this.texture.getWidth(), this.texture.getHeight());
		this.bounds = new Rectangle(this.position.x, this.position.y, Ship.WIDTH, Ship.HEIGHT);

		this.renderer = new ShipRenderer(this);
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.renderer.render(batch, cam);
	}

	public void dispose()
	{
		this.renderer.dispose();
	}
}
