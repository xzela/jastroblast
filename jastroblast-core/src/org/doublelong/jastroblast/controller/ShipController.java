package org.doublelong.jastroblast.controller;

import java.util.HashMap;
import java.util.Map;

import org.doublelong.jastroblast.entity.Space;

public class ShipController
{
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
	}

	public void processInput(float delta)
	{
		if(keys.get(Keys.LEFT))
		{
			this.space.getShip().setRotation(this.space.getShip().getRotation() + 1);
			//this.space.getShip().getPosition().x -= .5f;
		}
		else if(keys.get(Keys.RIGHT))
		{
			this.space.getShip().setRotation(this.space.getShip().getRotation() - 1);
			//this.space.getShip().getPosition().x += .5f;
		}

		if(keys.get(Keys.DOWN))
		{
			//this.space.getShip().setRotation(-1);
			//this.space.getShip().getPosition().y -= .5f;
		}
		else if(keys.get(Keys.UP))
		{
			//this.space.getShip().setRotation(-1);
			//			this.space.getShip().getPosition().y += .5f;
			this.space.getShip().getPosition().y += Math.cos(this.space.getShip().getRotation() * delta);
			this.space.getShip().getPosition().x -= Math.sin(this.space.getShip().getRotation() * delta);
		}
		else
		{
			//			this.space.getShip().setRotation(0);
		}
		this.space.getShip().renderer.sprite.setPosition(this.space.getShip().getPosition().x, this.space.getShip().getPosition().y);
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
