package org.doublelong.tests.controller;

import org.doublelong.tests.entities.Ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class ShipController extends InputAdapter
{
	private final static float MAX_VELOCITY = 7f;
	private final Ship ship;
	private float stillTime = 0f;

	public ShipController(Ship ship)
	{
		this.ship = ship;
		Gdx.input.setInputProcessor(this);
	}


	public void processControls()
	{
		Vector2 vel = this.ship.getBody().getLinearVelocity();
		Vector2 pos = this.ship.getBody().getPosition();

		// cap max velocity on x
		if(Math.abs(vel.x) > MAX_VELOCITY)
		{
			vel.x = Math.signum(vel.x) * MAX_VELOCITY;
			this.ship.getBody().setLinearVelocity(vel.x, vel.y);
		}

		// calculate stilltime & damp
		if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			this.stillTime += Gdx.graphics.getDeltaTime();
			this.ship.getBody().setLinearVelocity(vel.x * 0.9f, vel.y);
		}
		else {
			this.stillTime = 0;
		}
		// apply left impulse, but only if max velocity is not reached yet
		if(Gdx.input.isKeyPressed(Keys.RIGHT) && vel.x > -MAX_VELOCITY)
		{
			this.ship.getBody().applyLinearImpulse(-2f, 0, pos.x, pos.y);
		}

		// apply right impulse, but only if max velocity is not reached yet
		if(Gdx.input.isKeyPressed(Keys.LEFT) && vel.x < MAX_VELOCITY)
		{
			this.ship.getBody().applyLinearImpulse(2f, 0, pos.x, pos.y);
		}
	}
}
