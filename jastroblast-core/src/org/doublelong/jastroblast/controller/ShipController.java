package org.doublelong.jastroblast.controller;

import java.util.HashMap;
import java.util.Map;

import org.doublelong.jastroblast.entity.Space;

public class ShipController
{
	private static final float DAMP = .991f;
	private static final float MAX_VELOCITY = 2.5f;
	private static final float ACCELERATION = .1f;

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
		System.out.println(this.space.getShip().getAcceleration());
		// transform acceleration into "frame-time"
		this.space.getShip().getAcceleration().mul(delta);

		this.processInput(delta);


		// add current ship acceleration to the velocity
		this.space.getShip().getVelocity().add(this.space.getShip().getAcceleration());
		//this.space.getShip().getAcceleration().mul(1 / delta);


		// should be in collision check

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

	}

	public void processInput(float delta)
	{
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
			//this.space.getShip().setRotation(-1);
			//this.space.getShip().getPosition().y -= .5f;
			//this.space.getShip().getAcceleration().y -= Math.cos(this.space.getShip().getRotation() * delta);
			//this.space.getShip().getAcceleration().x += Math.sin(this.space.getShip().getRotation() * delta);
			if(this.space.getShip().getVelocity().y > 0)
			{
				this.space.getShip().getVelocity().y -= ACCELERATION;
			}
			else
			{
				this.space.getShip().getAcceleration().y -= ACCELERATION;
			}
			//			this.space.getShip().getAcceleration().x += ACCELERATION;

		}
		else if(keys.get(Keys.UP))
		{
			if(this.space.getShip().getVelocity().y < 0)
			{
				this.space.getShip().getVelocity().y += ACCELERATION;
			}
			else
			{
				this.space.getShip().getAcceleration().y += ACCELERATION;
			}
			//			this.space.getShip().getAcceleration().x -= ACCELERATION;
			this.space.getShip().getVelocity().y += (float)Math.cos(this.space.getShip().getRotation() * delta);
			this.space.getShip().getVelocity().x += -(float)Math.sin(this.space.getShip().getRotation() * delta);
		}
		//this.space.getShip().getPosition().add(this.space.getShip().getVelocity());
		//this.space.getShip().getAcceleration().mul(1/delta);
		//this.space.getShip().renderer.sprite.setPosition(this.space.getShip().getPosition().x, this.space.getShip().getPosition().y);
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

	public void leftPressed() { keys.get(keys.put(Keys.LEFT, true)); }
	public void leftReleased() { keys.get(keys.put(Keys.LEFT, false)); }

	public void rightPressed() { keys.get(keys.put(Keys.RIGHT, true)); }
	public void rightReleased() { keys.get(keys.put(Keys.RIGHT, false)); }

	public void upPressed() { keys.get(keys.put(Keys.UP, true)); }
	public void upReleased() { keys.get(keys.put(Keys.UP, false)); }

	public void downPressed() { keys.get(keys.put(Keys.DOWN, true)); }
	public void downReleased() { keys.get(keys.put(Keys.DOWN, false)); }

}
