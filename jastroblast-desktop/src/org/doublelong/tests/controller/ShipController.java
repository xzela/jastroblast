package org.doublelong.tests.controller;

import org.doublelong.tests.entities.Ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class ShipController extends InputAdapter
{
	private final static float MAX_VELOCITY = 100f;
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
			vel.y = Math.signum(vel.y) * MAX_VELOCITY;
			this.ship.getBody().setLinearVelocity(vel.x, vel.y);
		}

		// calculate stilltime & damp
		if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)
				&& !Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN))
		{
			this.stillTime += Gdx.graphics.getDeltaTime();
			this.ship.getBody().setLinearVelocity(vel.x * 0.99f, vel.y * 0.99f);
			this.ship.getBody().setAngularDamping(2f);
		}
		else
		{
			this.stillTime = 0;
		}

		if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT) && this.stillTime > 0.2)
		{
			this.ship.getFixture().setFriction(100f);
			//			this.ship.getFixture().setFriction(100f);
		}
		else
		{
			this.ship.getFixture().setFriction(0.2f);
			//			this.ship.getFixture().setFriction(0.2f);
		}


		// apply left impulse, but only if max velocity is not reached yet
		if(Gdx.input.isKeyPressed(Keys.LEFT) && vel.x > -MAX_VELOCITY)
		{
			this.ship.getBody().applyAngularImpulse(20f, false);
			//this.ship.getBody().applyLinearImpulse(-2f, 0, pos.x, pos.y);
		}

		// apply UP impulse
		if (Gdx.input.isKeyPressed(Keys.UP) && vel.y > -MAX_VELOCITY)
		{
			this.ship.getBody().applyLinearImpulse(0, 2f, pos.x, pos.y, true);
		}

		// apply UP impulse
		if (Gdx.input.isKeyPressed(Keys.DOWN) && vel.y < MAX_VELOCITY)
		{
			this.ship.getBody().applyLinearImpulse(0, -2f, pos.x, pos.y, true);
		}

		// apply right impulse, but only if max velocity is not reached yet
		if(Gdx.input.isKeyPressed(Keys.RIGHT) && vel.x < MAX_VELOCITY)
		{
			this.ship.getBody().applyLinearImpulse(2f, 0, pos.x, pos.y, true);
		}
	}
}
