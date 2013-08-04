package org.doublelong.jastroblast.renderer;


import org.doublelong.jastroblast.entity.Ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class ShipRenderer extends BaseRenderer
{
	private final Ship ship;
	private final Texture texture;
	public final Sprite sprite;

	public Rectangle r;

	public ShipRenderer(Ship ship)
	{
		this.ship = ship;
		this.texture = new Texture(Gdx.files.internal("assets/images/player.png"));
		this.sprite = new Sprite(this.texture);
		this.r = new Rectangle();
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.sprite.setBounds(this.ship.getPosition().x, this.ship.getPosition().y, this.ship.getBounds().width, this.ship.getBounds().height);
		this.sprite.setPosition(this.ship.getPosition().x, this.ship.getPosition().y);

		float debug_x = this.ship.getPosition().x + this.ship.getBounds().width;
		float debug_y = this.ship.getPosition().y + this.ship.getBounds().width;
		Rectangle bb = this.sprite.getBoundingRectangle();
		this.r.x = this.ship.getPosition().x;
		this.r.y = this.ship.getPosition().y;
		this.r.width =  this.sprite.getWidth();
		this.r.height = this.sprite.getHeight();


		if (this.debug)
		{
			this.debugRenderer.begin(ShapeType.Rectangle);
			this.debugRenderer.setColor(Color.RED);
			this.debugRenderer.rect(bb.x, bb.y, bb.width, bb.height);
			this.debugRenderer.end();
			//			float x = 50 * this.ship.ppu.x;
			//			float y = 20 * this.ship.ppu.y;
			//			float width = x;
			//			float height = y;
			//
			//			this.debugRenderer.begin(ShapeType.Rectangle);
			//			this.debugRenderer.setColor(Color.BLUE);
			//			this.debugRenderer.identity();
			//			this.debugRenderer.translate(x, y, 2f);
			//			this.debugRenderer.rotate(0, 0, 1, 90);
			//			this.debugRenderer.rect(-height / 2, -width / 2, height, width);
			//			this.debugRenderer.end();
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
