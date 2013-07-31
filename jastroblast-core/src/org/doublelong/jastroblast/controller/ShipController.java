package org.doublelong.jastroblast.controller;

import java.util.HashMap;
import java.util.Map;

import org.doublelong.jastroblast.entity.Space;

public class ShipController
{
	private static final float DAMP = .991f;
	private static final float MAX_VELOCITY = 2.5f;
	private static final float SPEED = 1f;

	enum Keys {LEFT, RIGHT, UP, DOWN}
	static Map<Keys, Boolean> keys = new HashMap<ShipController.Keys, Boolean>();
	static
	{
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
	}

	private final Space space;

	public ShipController(Space space)
	{
		this.space = space;
	}

	public void update(float delta)
	{
		this.processInput(delta);

		// add the velocity to the position
		this.setMaxVelocities();
		this.space.getShip().getPosition().add(this.space.getShip().getVelocity());


		// "frame-time!"
		this.space.getShip().getVelocity().mul(delta);

		// dampen the x and y velocity by a fixed amount (to add slowing effect)
		this.space.getShip().getVelocity().x *= DAMP;
		this.space.getShip().getVelocity().y *= DAMP;

		this.space.getShip().getVelocity().mul(1 / delta);

		this.space.getShip().renderer.sprite.setPosition(this.space.getShip().getPosition().x, this.space.getShip().getPosition().y);

		this.wrap();
	}

	public void processInput(float delta)
	{
		// calculate x and y scales (this will give the x/y equivalent of the angle)
		double scale_x = Math.cos(this.space.getShip().getRotation() * SPEED * delta);
		double scale_y = Math.sin(this.space.getShip().getRotation() * SPEED * delta);

		if(keys.get(Keys.LEFT))
		{
			this.space.getShip().setRotation(this.space.getShip().getRotation() + 1.5f);
		}
		else if(keys.get(Keys.RIGHT))
		{
			this.space.getShip().setRotation(this.space.getShip().getRotation() - 1.5f);
		}

		if(keys.get(Keys.DOWN))
		{
			// delta times scale gives velocity
			this.space.getShip().getVelocity().y -= scale_x * delta;
			this.space.getShip().getVelocity().x -= -scale_y * delta;
		}
		else if(keys.get(Keys.UP))
		{
			this.space.getShip().getVelocity().y += scale_x * delta;
			this.space.getShip().getVelocity().x += -scale_y * delta;
		}
	}

	private void setMaxVelocities()
	{
		// test x velocity
		if (this.space.getShip().getVelocity().x > MAX_VELOCITY)
		{
			this.space.getShip().getVelocity().x = MAX_VELOCITY;
		}
		if (this.space.getShip().getVelocity().x < -MAX_VELOCITY)
		{
			this.space.getShip().getVelocity().x = -MAX_VELOCITY;
		}

		// test y velocity
		if (this.space.getShip().getVelocity().y > MAX_VELOCITY)
		{
			this.space.getShip().getVelocity().y = MAX_VELOCITY;
		}
		if (this.space.getShip().getVelocity().y < -MAX_VELOCITY)
		{
			this.space.getShip().getVelocity().y = -MAX_VELOCITY;
		}

	}

	private void wrap()
	{
		if(this.space.getShip().getPosition().x < -this.space.getShip().renderer.sprite.getWidth())
		{
			this.space.getShip().getPosition().x = 600 + this.space.getShip().renderer.sprite.getWidth() / 2;
		}
		else if (this.space.getShip().getPosition().x > 600 + this.space.getShip().renderer.sprite.getWidth() - 10)
		{
			this.space.getShip().getPosition().x = 0 - this.space.getShip().renderer.sprite.getWidth() / 2;
		}

		if (this.space.getShip().getPosition().y < -this.space.getShip().renderer.sprite.getHeight())
		{
			this.space.getShip().getPosition().y = 599;
		}
		else if(this.space.getShip().getPosition().y > 600)
		{
			this.space.getShip().getPosition().y = 0 - this.space.getShip().renderer.sprite.getHeight();
		}
	}

	public void leftPressed() { keys.get(keys.put(Keys.LEFT, true)); }
	public void leftReleased() { keys.get(keys.put(Keys.LEFT, false)); }

	public void rightPressed() { keys.get(keys.put(Keys.RIGHT, true)); }
	public void rightReleased() { keys.get(keys.put(Keys.RIGHT, false)); }

	public void upPressed() { keys.get(keys.put(Keys.UP, true)); }
	public void upReleased() { keys.get(keys.put(Keys.UP, false)); }

	public void downPressed() { keys.get(keys.put(Keys.DOWN, true)); }
	public void downReleased() { keys.get(keys.put(Keys.DOWN, false)); }

}
