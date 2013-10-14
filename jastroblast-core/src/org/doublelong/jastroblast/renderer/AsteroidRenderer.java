package org.doublelong.jastroblast.renderer;

import org.doublelong.jastroblast.entity.Asteroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class AsteroidRenderer extends BaseRenderer
{

	private final Asteroid asteroid;
	private final Texture texture;

	public final Sprite sprite;

	public Mesh mesh;
	public Mesh squareMesh;

	public AsteroidRenderer(Asteroid asteroid)
	{
		this.asteroid = asteroid;
		this.texture = new Texture(Gdx.files.internal("assets/images/asteriod_big.png"));
		this.sprite = new Sprite(this.texture);

	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		// set scale
		//		this.sprite.setScale(.5f, .5f);
		//		this.font.setScale(.5f, .5f);

		// set the bounds and position
		this.sprite.setPosition(this.asteroid.getPosition().x, this.asteroid.getPosition().y);
		this.sprite.setBounds(this.asteroid.getPosition().x, this.asteroid.getPosition().y, this.asteroid.getWidth(), this.asteroid.getHeight());


		if (squareMesh == null) {
			squareMesh = new Mesh(true, 4, 4,
					new VertexAttribute(Usage.Position, 3, "a_position"),
					new VertexAttribute(Usage.ColorPacked, 4, "a_color"));

			squareMesh.setVertices(new float[] {
					-0.5f, -0.5f, 0, Color.toFloatBits(128, 0, 0, 255),
					0.5f, -0.5f, 0, Color.toFloatBits(192, 0, 0, 255),
					-0.5f, 0.5f, 0, Color.toFloatBits(192, 0, 0, 255),
					0.5f, 0.5f, 0, Color.toFloatBits(255, 0, 0, 255) });
			squareMesh.setIndices(new short[] { 0, 1, 2, 3});
		}

		//		this.mesh = new Mesh(true, 4, 4, new VertexAttribute(Usage.Position, 3, "a_position"));
		//		this.mesh.setVertices(new float[] {0, 0, this.sprite.getWidth(), 0, this.sprite.getWidth(), this.sprite.getHeight(), 0, this.sprite.getHeight() });
		this.squareMesh.render(GL10.GL_TRIANGLE_STRIP, 0, 4);
		//this.poly = new Polygon(new float[] {0, 0, this.sprite.getWidth(), 0, this.sprite.getWidth(), this.sprite.getHeight(), 0, this.sprite.getHeight() });

		//		this.poly.setOrigin(this.sprite.getWidth() / 2, this.sprite.getHeight() / 2);
		//
		//		this.poly.setPosition(this.asteroid.getPosition().x, this.asteroid.getPosition().y);
		//		this.poly.setRotation(this.asteroid.getAngle());


		// set debug x
		float debug_x = this.asteroid.getPosition().x + this.asteroid.getBounds().width;
		float debug_y = this.asteroid.getPosition().y + this.asteroid.getBounds().width;
		// set the bounding box
		this.bb = this.sprite.getBoundingRectangle();
		// set the hittable box
		this.hb.x = this.sprite.getX();
		this.hb.y = this.sprite.getY();
		this.hb.width = this.sprite.getWidth() * this.sprite.getScaleX();
		this.hb.height = this.sprite.getHeight() * this.sprite.getScaleY();

		if (this.debug)
		{
			this.debugRenderer.setProjectionMatrix(cam.combined);
			if(this.debug)
			{
				if (this.debugHit)
				{
					this.debugRenderer.begin(ShapeType.Line);
					this.debugRenderer.identity();
					this.debugRenderer.setColor(Color.RED);
					this.debugRenderer.rect(this.bb.x, this.bb.y, this.bb.width, this.bb.height);
					this.debugRenderer.end();
				}
				else
				{
					this.debugRenderer.begin(ShapeType.Line);
					this.debugRenderer.identity();
					this.debugRenderer.setColor(Color.RED);
					this.debugRenderer.rect(this.bb.x, this.bb.y, this.bb.width, this.bb.height);
					this.debugRenderer.end();
				}
			}
			if (this.debugHit)
			{
				this.debugRenderer.begin(ShapeType.Line);
				this.debugRenderer.setColor(Color.BLUE);
				this.debugRenderer.identity();
				this.debugRenderer.translate(this.hb.x + this.hb.width, this.hb.y + this.hb.height, 0f);
				this.debugRenderer.rotate(0, 0, 1, this.asteroid.getAngle());
				this.debugRenderer.rect(-this.hb.width / 2, -this.hb.height / 2, this.hb.width, this.hb.height);
				this.debugRenderer.end();
			}
			else
			{
				this.debugRenderer.begin(ShapeType.Line);
				this.debugRenderer.setColor(Color.BLUE);
				this.debugRenderer.identity();
				this.debugRenderer.translate(this.hb.x + this.hb.width, this.hb.y + this.hb.height, 0f);
				this.debugRenderer.rotate(0, 0, 1, this.asteroid.getAngle());
				this.debugRenderer.rect(-this.hb.width / 2, -this.hb.height / 2, this.hb.width, this.hb.height);
				this.debugRenderer.end();
			}

		}

		batch.begin();
		if (this.debug)
		{
			this.font.setColor(Color.LIGHT_GRAY);
			this.font.draw(batch, "Pos: " + this.format.format(this.asteroid.getPosition().x) + " : " + this.format.format(this.asteroid.getPosition().y), debug_x, debug_y);
			this.font.draw(batch, "Ang: " + this.format.format(this.asteroid.getAngle()), debug_x, debug_y - 20);
		}
		this.sprite.setRotation(this.asteroid.getAngle());
		this.sprite.draw(batch);
		batch.end();


		//this.wrap();
	}

	private void wrap()
	{
		//		if(this.asteroid.getPosition().x < -this.sprite.getWidth())
		//		{
		//			this.asteroid.getPosition().x = this.asteroid.getSpace().viewport.width + this.sprite.getWidth() / 2;
		//		}
		//		else if (this.asteroid.getPosition().x > this.asteroid.getSpace().viewport.width + this.sprite.getWidth() - 10)
		//		{
		//			this.asteroid.getPosition().x = 0 - this.sprite.getWidth() / 2;
		//		}
		//
		//		if (this.asteroid.getPosition().y < -this.sprite.getHeight())
		//		{
		//			this.asteroid.getPosition().y = this.asteroid.getSpace().viewport.height - 1;
		//		}
		//		else if(this.asteroid.getPosition().y > this.asteroid.getSpace().viewport.height)
		//		{
		//			this.asteroid.getPosition().y = 0 - this.sprite.getHeight();
		//		}
	}
	public void dispose()
	{
		this.texture.dispose();
	}
}
