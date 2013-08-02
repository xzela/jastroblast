package org.doublelong.jastroblast.renderer;

import org.doublelong.jastroblast.entity.Ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipRenderer
{
	private final Ship ship;
	private final Texture texture;
	public final Sprite sprite;

	private final BitmapFont font;

	public ShipRenderer(Ship ship)
	{
		this.ship = ship;
		this.texture = new Texture(Gdx.files.internal("assets/images/player.png"));
		this.sprite = new Sprite(this.texture);
		this.sprite.setBounds(this.ship.getPosition().x, this.ship.getPosition().y, this.ship.getBounds().width, this.ship.getBounds().height);
		this.sprite.setPosition(this.ship.getPosition().x, this.ship.getPosition().y);

		this.font = new BitmapFont();
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		float debug_x = this.ship.getPosition().x + this.ship.getBounds().width;
		float debug_y = this.ship.getPosition().y + this.ship.getBounds().width;
		//
		batch.begin();
		this.font.setColor(Color.GRAY);
		this.font.draw(batch, "Pos: " + this.ship.getPosition().toString(), debug_x, debug_y);
		this.font.draw(batch, "Vel: " + this.ship.getVelocity().toString(), debug_x, debug_y - 20);

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
