package org.doublelong.jastroblast.controller;

import java.util.HashMap;
import java.util.Map;

import org.doublelong.jastroblast.Inputs;
import org.doublelong.jastroblast.entity.Ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class ShipController extends InputAdapter
{
	private static final float DAMP = .991f;
	private static final float MAX_VELOCITY = 2.5f;
	private static final float SPEED = 1.1f;

	enum Keys {LEFT, RIGHT, UP, DOWN}
	static Map<Keys, Boolean> keys = new HashMap<ShipController.Keys, Boolean>();
	static
	{
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
	}

	private final Ship ship;

	public ShipController(Ship ship)
	{
		this.ship = ship;
	}

	public void update(float delta)
	{
		this.processInput(delta);

		//		// add the velocity to the position
		//		this.setMaxVelocities();
		//		this.space.getShip().getPosition().add(this.space.getShip().getVelocity());
		//
		//
		//		// "frame-time!"
		//		this.space.getShip().getVelocity().mul(delta);
		//
		//		// dampen the x and y velocity by a fixed amount (to add slowing effect)
		//		this.space.getShip().getVelocity().x *= DAMP;
		//		this.space.getShip().getVelocity().y *= DAMP;
		//
		//		this.space.getShip().getVelocity().mul(1 / delta);
		//
		//		this.space.getShip().renderer.sprite.setPosition(this.space.getShip().getPosition().x, this.space.getShip().getPosition().y);

		//this.wrap();
	}

	public void processInput(float delta)
	{
		Vector2 velocity = this.ship.getBody().getLinearVelocity();
		float angle =  this.ship.getBody().getAngle();
		Vector2 desiredVelocity = new Vector2();
		Vector2 worldCenter = this.ship.getBody().getWorldCenter();
		Vector2 localCenter = this.ship.getBody().getLocalCenter();

		if(this.isLeftThrust())
		{
			this.ship.getBody().applyLinearImpulse(new Vector2(0,-500), localCenter, true);
		}

		if(this.isRightThrust())
		{
			this.ship.getBody().applyLinearImpulse(new Vector2(0,500), localCenter, true);
		}

		if (this.isForwardThrust())
		{
			float x = (float) Math.cos(angle * delta);
			float y = (float) Math.sin(angle * delta);
			desiredVelocity.add(velocity.x * x, 500 * y);
			this.ship.getBody().applyLinearImpulse(new Vector2(0,2500), worldCenter, true);
		}
		this.ship.getBody().setAngularDamping(0.5f);
		this.ship.getBody().setLinearDamping(0.5f);
	}

	private boolean isForwardThrust()
	{
		return Gdx.input.isKeyPressed(Inputs.PLAYER_THRUST);
	}

	private boolean isRightThrust()
	{
		return Gdx.input.isKeyPressed(Inputs.PLAYER_RIGHT);
	}

	private boolean isLeftThrust()
	{
		return Gdx.input.isKeyPressed(Inputs.PLAYER_LEFT);
	}
}
