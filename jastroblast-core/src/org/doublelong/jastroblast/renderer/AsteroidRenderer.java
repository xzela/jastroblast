package org.doublelong.jastroblast.renderer;

import org.doublelong.jastroblast.entity.Asteroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AsteroidRenderer
{

	private final Asteroid asteroid;
	private final Texture texture;
	private final BitmapFont font;

	public final Sprite sprite;

	public AsteroidRenderer(Asteroid asteroid)
	{
		this.asteroid = asteroid;
		this.texture = new Texture(Gdx.files.internal("assets/images/asteriod_big.png"));
		this.sprite = new Sprite(this.texture);
		this.sprite.setPosition(this.asteroid.getPosition().x, this.asteroid.getPosition().y);


		this.font = new BitmapFont();
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		float debug_x = this.asteroid.getPosition().x + this.asteroid.getBounds().width;
		float debug_y = this.asteroid.getPosition().y + this.asteroid.getBounds().width;

		batch.begin();
		this.font.setColor(Color.LIGHT_GRAY);
		this.font.draw(batch, "Pos: " + this.asteroid.getPosition().toString(), debug_x, debug_y);

		this.sprite.setRotation(this.asteroid.getAngle());
		this.sprite.draw(batch);

		batch.end();
	}

	public void dispose()
	{

	}
}
