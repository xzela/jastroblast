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
		//
		batch.begin();
		this.font.setColor(Color.GRAY);
		this.font.draw(batch, "Pos: " + this.ship.getPosition().toString(), 100f, 60f);
		this.font.draw(batch, "Vel: " + this.ship.getVelocity().toString(), 100f, 40f);
		this.font.draw(batch, "Acc: " + this.ship.getAcceleration().toString(), 100f, 20f);

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
