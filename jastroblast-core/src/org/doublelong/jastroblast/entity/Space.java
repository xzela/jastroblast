package org.doublelong.jastroblast.entity;

import org.doublelong.jastroblast.JastroBlast;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Space
{
	public static final float HEIGHT = 10f;
	public static final float WIDTH = 12f;

	private final JastroBlast game;

	private final Ship ship;
	public Ship getShip() { return this.ship; }

	public Space(JastroBlast game)
	{
		this.game = game;
		this.ship = new Ship(this);
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.ship.render(batch, cam);
	}

	public void update(float delta)
	{
		this.ship.update(delta);
	}

	public void dispose()
	{
		this.ship.dispose();
	}
}
