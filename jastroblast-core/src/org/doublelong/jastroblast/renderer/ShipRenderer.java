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
		this.sprite.setScale(.5f, .5f);
		this.font.setScale(.5f, .5f);

		//this.sprite.setBounds(this.ship.getPosition().x, this.ship.getPosition().y, this.ship.getBounds().width, this.ship.getBounds().height);
		this.sprite.setBounds(this.ship.getPosition().x, this.ship.getPosition().y, this.sprite.getWidth(), this.sprite.getHeight());
		this.sprite.setPosition(this.ship.getPosition().x, this.ship.getPosition().y);

		float debug_x = this.ship.getPosition().x + this.ship.getBounds().width;
		float debug_y = this.ship.getPosition().y + this.ship.getBounds().width;
		this.bb = this.sprite.getBoundingRectangle();
		//set the hittible bounds and position
		this.hb.x = this.sprite.getX();
		this.hb.y = this.sprite.getY();
		this.hb.width =  this.sprite.getWidth() * this.sprite.getScaleX();
		this.hb.height = this.sprite.getHeight() * this.sprite.getScaleY();


		if (this.debug)
		{
			this.debugRenderer.setProjectionMatrix(cam.combined);
			this.debugRenderer.begin(ShapeType.Rectangle);
			this.debugRenderer.identity();
			this.debugRenderer.setColor(Color.RED);
			this.debugRenderer.rect(bb.x, bb.y, bb.width, bb.height);
			this.debugRenderer.end();

			this.debugRenderer.begin(ShapeType.Rectangle);
			this.debugRenderer.setColor(Color.BLUE);
			this.debugRenderer.identity();
			this.debugRenderer.translate(this.hb.x + this.hb.width, this.hb.y + this.hb.height, 0f);
			this.debugRenderer.rotate(0, 0, 1, this.ship.getRotation());
			this.debugRenderer.rect(-this.hb.width / 2, -this.hb.height / 2, this.hb.width, this.hb.height);
			//this.debugRenderer.rect(this.r.x, this.r.y, this.r.width, this.r.height);
			this.debugRenderer.end();
		}

		//
		//		batch.setProjectionMatrix(cam.combined);
		batch.begin();

		if (this.debug)
		{
			this.font.setColor(Color.RED);
			this.font.draw(batch, "Pos: " + this.format.format(this.sprite.getX()) + " : " + this.format.format(this.sprite.getY()), debug_x, debug_y);
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
