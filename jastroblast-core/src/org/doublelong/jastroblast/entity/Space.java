package org.doublelong.jastroblast.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Space
{
	private static final Vector2 GRAVITY = new Vector2(0, 0);

	private final World world;
	public World getWorld() { return this.world; }

	private final Ship ship;
	public Ship getShip() { return this.ship; }

	private final boolean debug;

	public Space(OrthographicCamera camera, boolean debug)
	{
		this.debug = debug;
		this.world = new World(GRAVITY, true); // no gravity
		this.ship = new Ship(world, new Vector2(camera.viewportWidth / 2, camera.viewportHeight / 2));
	}

	public void render(SpriteBatch batch, OrthographicCamera camera)
	{
		this.ship.render(batch, camera);
	}

	public void update(float delta)
	{
		this.ship.update(delta);
	}

	public void dispose()
	{

	}

}
