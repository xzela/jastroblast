package org.doublelong.jastroblast.renderer;


import org.doublelong.jastroblast.entity.Ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class ShipRenderer extends BaseRenderer
{
	private final Ship ship;
	private final Texture texture;
	public final Sprite sprite;


	public ShipRenderer(Ship ship)
	{
		this.ship = ship;
		this.texture = new Texture(Gdx.files.internal("assets/images/player.png"));
		this.sprite = new Sprite(this.texture);
		this.sprite.setBounds(this.ship.getPosition().x, this.ship.getPosition().y, this.ship.getBounds().width, this.ship.getBounds().height);
		this.sprite.setPosition(this.ship.getPosition().x, this.ship.getPosition().y);

	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		float debug_x = this.ship.getPosition().x + this.ship.getBounds().width;
		float debug_y = this.ship.getPosition().y + this.ship.getBounds().width;

		if (this.debug)
		{
			this.debugRenderer.begin(ShapeType.Rectangle);
			this.debugRenderer.setColor(Color.RED);
			this.debugRenderer.rect(this.ship.getPosition().x, this.ship.getPosition().y, this.ship.getBounds().width, this.ship.getBounds().height);
			this.debugRenderer.end();
		}

		//
		batch.begin();

		if (this.debug)
		{
			this.font.setColor(Color.RED);
			this.font.draw(batch, "Pos: " + this.format.format(this.ship.getPosition().x) + " : " + this.format.format(this.ship.getPosition().y), debug_x, debug_y);
			this.font.draw(batch, "Vel: " + this.format.format(this.ship.getVelocity().x) + " : " + this.format.format(this.ship.getVelocity().y), debug_x, debug_y - 20);
		}
		// rotate the sprite when needed
		this.sprite.setRotation(this.ship.getRotation());

		this.sprite.draw(batch);

		batch.end();
	}

	public void dispose()
	{
		this.texture.dispose();
	}
}
