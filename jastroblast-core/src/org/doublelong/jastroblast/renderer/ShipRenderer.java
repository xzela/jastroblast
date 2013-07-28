package org.doublelong.jastroblast.renderer;

import org.doublelong.jastroblast.entity.Ship;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ShipRenderer
{
	private final Ship ship;
	private final ShapeRenderer renderer;

	public ShipRenderer(Ship ship)
	{
		this.ship = ship;
		this.renderer = new ShapeRenderer();

	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		batch.setProjectionMatrix(cam.projection);
		batch.begin();
		batch.draw(this.ship.getTexture(), this.ship.getPosition().x, this.ship.getPosition().y, this.ship.getBounds().width, this.ship.getBounds().height);
		batch.end();
	}

	public void dispose()
	{
		this.ship.getTexture().dispose();
	}
}
