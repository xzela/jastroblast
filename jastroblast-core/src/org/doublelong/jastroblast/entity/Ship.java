package org.doublelong.jastroblast.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Ship
{
	// TODO fix Height/Width, should be associated with the sprite
	private static final float WIDTH = 50f;
	private static final float HEIGHT = 50f;

	private final World world;

	private final Body body;
	public Body getBody() { return this.body; }

	private BodyDef bodyDef = new BodyDef();

	private final Fixture fixture;
	public Fixture getFixture() { return this.fixture; }

	private final FixtureDef fixtureDef = new FixtureDef();

	private final PolygonShape shape = new PolygonShape();


	public Ship(World world, Vector2 position)
	{
		this.world = world;

		this.bodyDef.type = BodyType.DynamicBody;
		this.bodyDef.position.set(position);

		this.shape.setAsBox(WIDTH, HEIGHT);

		this.fixtureDef.shape = this.shape;
		this.fixtureDef.density = 5f;
		this.fixtureDef.friction = 0;
		this.fixtureDef.restitution = 1f;

		this.body = this.world.createBody(this.bodyDef);
		this.body.setGravityScale(.2f);

		this.fixture = this.body.createFixture(this.fixtureDef);
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
	}

	public void update(float delta)
	{

	}

	public void dispose()
	{
	}
}
