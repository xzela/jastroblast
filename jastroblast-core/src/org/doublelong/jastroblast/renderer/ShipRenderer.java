package org.doublelong.jastroblast.renderer;


import org.doublelong.jastroblast.entity.Ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;

public class ShipRenderer extends BaseRenderer
{
	private final Ship ship;
	private final Texture texture;
	public final Sprite sprite;

	public Polygon poly;

	public ShipRenderer(Ship ship)
	{
		this.ship = ship;
		this.texture = new Texture(Gdx.files.internal("assets/images/player.png"));
		this.sprite = new Sprite(this.texture);


	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		// set scale
		//		this.sprite.setScale(this.ship.SCALE, this.ship.SCALE);
		//		this.font.setScale(this.ship.SCALE, this.ship.SCALE);

		//this.sprite.setBounds(this.ship.getPosition().x, this.ship.getPosition().y, this.ship.getBounds().width, this.ship.getBounds().height);
		this.sprite.setBounds(this.ship.getPosition().x, this.ship.getPosition().y, this.sprite.getWidth(), this.sprite.getHeight());
		this.sprite.setPosition(this.ship.getPosition().x, this.ship.getPosition().y);

		this.poly = new Polygon(new float[] {0, 0, this.sprite.getWidth(), 0, this.sprite.getWidth(), this.sprite.getHeight(), 0, this.sprite.getHeight() });

		this.poly.setOrigin(this.sprite.getWidth() / 2, this.sprite.getHeight() / 2);


		this.poly.setPosition(this.ship.getPosition().x, this.ship.getPosition().y);
		this.poly.setRotation(this.ship.getRotation());


		float debug_x = this.ship.getPosition().x + 40 + this.ship.getBounds().width;
		float debug_y = this.ship.getPosition().y + 40 + this.ship.getBounds().height;

		// set the bounding box
		this.bb = this.sprite.getBoundingRectangle();
		//set the hitable bounds and position
		this.hb.x = this.sprite.getX();
		this.hb.y = this.sprite.getY();
		this.hb.width =  this.sprite.getWidth();
		this.hb.height = this.sprite.getHeight();


		if (this.debug)
		{
			this.debugRenderer.setProjectionMatrix(cam.combined);

			//			this.debugRenderer.begin(ShapeType.Rectangle);
			//			this.debugRenderer.identity();
			//			this.debugRenderer.setColor(Color.RED);
			//			this.debugRenderer.rect(bb.x, bb.y, bb.width, bb.height);
			//			this.debugRenderer.end();

			this.debugRenderer.begin(ShapeType.Rectangle);
			this.debugRenderer.setColor(Color.BLUE);
			this.debugRenderer.identity();

			this.debugRenderer.translate(this.poly.getX() + this.hb.width / 2, this.poly.getY() + this.hb.height / 2, 0f);
			this.debugRenderer.rotate(0, 0, 1, this.poly.getRotation());
			this.debugRenderer.rect(-this.hb.width / 2, -this.hb.height / 2, this.hb.width, this.hb.height);

			//			this.debugRenderer.translate(this.hb.x + this.hb.width / 2, this.hb.y + this.hb.height / 2, 0f);
			//			this.debugRenderer.rotate(0, 0, 1, this.ship.getRotation());
			//			this.debugRenderer.rect(-this.hb.width / 2, -this.hb.height / 2, this.hb.width, this.hb.height);
			this.debugRenderer.end();
		}

		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		if (this.debug)
		{
			this.font.setColor(Color.RED);
			this.font.draw(batch, "Pos: " + this.format.format(this.sprite.getX()) + " : " + this.format.format(this.sprite.getY()), debug_x, debug_y);
			this.font.draw(batch, "View: " + this.format.format(this.ship.getSpace().ppuX - Math.abs(this.sprite.getX())) + " : " + this.format.format(this.ship.getSpace().viewport.height), debug_x, debug_y - 20);
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
