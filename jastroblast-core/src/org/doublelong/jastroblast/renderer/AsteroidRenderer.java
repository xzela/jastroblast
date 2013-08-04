package org.doublelong.jastroblast.renderer;

import org.doublelong.jastroblast.entity.Asteroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AsteroidRenderer extends BaseRenderer
{

	private final Asteroid asteroid;
	private final Texture texture;

	public final Sprite sprite;

	public AsteroidRenderer(Asteroid asteroid)
	{
		this.asteroid = asteroid;
		this.texture = new Texture(Gdx.files.internal("assets/images/asteriod_big.png"));
		this.sprite = new Sprite(this.texture);
		this.sprite.setPosition(this.asteroid.getPosition().x, this.asteroid.getPosition().y);
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		float debug_x = this.asteroid.getPosition().x + this.asteroid.getBounds().width;
		float debug_y = this.asteroid.getPosition().y + this.asteroid.getBounds().width;
		this.sprite.setPosition(this.asteroid.getPosition().x, this.asteroid.getPosition().y);

		batch.begin();
		this.font.setColor(Color.LIGHT_GRAY);
		this.font.draw(batch, "Pos: " + this.format.format(this.asteroid.getPosition().x) + " : " + this.format.format(this.asteroid.getPosition().y), debug_x, debug_y);
		this.font.draw(batch, "Ang: " + this.format.format(this.asteroid.getAngle()), debug_x, debug_y - 20);

		this.sprite.setRotation(this.asteroid.getAngle());
		this.sprite.draw(batch);

		batch.end();

		this.wrap();
	}

	private void wrap()
	{
		if(this.asteroid.getPosition().x < -this.sprite.getWidth())
		{
			this.asteroid.getPosition().x = 600 + this.sprite.getWidth() / 2;
		}
		else if (this.asteroid.getPosition().x > 600 + this.sprite.getWidth() - 10)
		{
			this.asteroid.getPosition().x = 0 - this.sprite.getWidth() / 2;
		}

		if (this.asteroid.getPosition().y < -this.sprite.getHeight())
		{
			this.asteroid.getPosition().y = 599;
		}
		else if(this.asteroid.getPosition().y > 600)
		{
			this.asteroid.getPosition().y = 0 - this.sprite.getHeight();
		}
	}
	public void dispose()
	{
		this.texture.dispose();
	}
}
