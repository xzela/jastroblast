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
		//this.sprite.setBounds(this.ship.getPosition().x, this.ship.getPosition().y, this.ship.getBounds().width, this.ship.getBounds().height);
		this.sprite.setBounds(this.ship.getPosition().x, this.ship.getPosition().y, this.texture.getWidth(), this.texture.getHeight());
		this.sprite.setPosition(this.ship.getPosition().x, this.ship.getPosition().y);

		float debug_x = this.ship.getPosition().x + this.ship.getBounds().width;
		float debug_y = this.ship.getPosition().y + this.ship.getBounds().width;
		Rectangle bb = this.sprite.getBoundingRectangle();
		this.r.x = this.sprite.getX();
		this.r.y = this.sprite.getY();
		this.r.width =  this.sprite.getWidth();
		this.r.height = this.sprite.getHeight();


		if (this.debug)
		{
			this.debugRenderer.setProjectionMatrix(cam.combined);
			this.debugRenderer.begin(ShapeType.Rectangle);
			this.debugRenderer.identity();
			this.debugRenderer.setColor(Color.RED);
			this.debugRenderer.rect(bb.x, bb.y, bb.width, bb.height);
			this.debugRenderer.end();
			float x = this.sprite.getX();
			float y = this.sprite.getY();
			float width = this.sprite.getWidth();
			float height = this.sprite.getHeight();


			this.debugRenderer.begin(ShapeType.Rectangle);
			this.debugRenderer.setColor(Color.BLUE);
			this.debugRenderer.identity();
			this.debugRenderer.translate(this.r.x + this.r.width / 2, this.r.y + this.r.height / 2, 0f);
			this.debugRenderer.rotate(0, 0, 1, this.ship.getRotation());
			this.debugRenderer.rect(-this.r.width / 2, -this.r.height / 2, this.r.width, this.r.height);
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
