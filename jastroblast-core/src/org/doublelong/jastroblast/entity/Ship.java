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
import com.badlogic.gdx.utils.Array;

public class Ship
{
	// TODO fix Height/Width, should be associated with the sprite
	private static final float WIDTH = 50f;
	private static final float HEIGHT = 50f;

	public static final float RAITO = 10f;
	public static final float SPEED = 100f;

	private final World world;

	private final Body body;
	public Body getBody() { return this.body; }

	private BodyDef bodyDef = new BodyDef();

	//	private final Fixture fixture;
	//	public Fixture getFixture() { return this.fixture; }

	private final FixtureDef fixtureDef = new FixtureDef();

	private final Array<Fixture> fixtures;
	public Array<Fixture> getFixtures() { return this.fixtures; }

	private final PolygonShape shape = new PolygonShape();

	//	private ShipController controller;

	public Ship(World world, Vector2 position)
	{
		this.world = world;

		this.bodyDef.type = BodyType.DynamicBody;
		this.bodyDef.position.set(position);

		this.shape.setAsBox(WIDTH / 2, HEIGHT / 2);

		this.body = this.world.createBody(this.bodyDef);

		FixtureDef left = new FixtureDef();
		left.shape = this.shape;
		left.density = 5f;
		left.friction = 0;
		left.restitution =1f;

		FixtureDef fuselage = new FixtureDef();
		{
			fuselage.shape = this.makeFuselage();
			fuselage.density = 5f;
			fuselage.friction = 0.9f;
			fuselage.restitution = 1f;
		}

		FixtureDef rightWing = new FixtureDef();
		{
			rightWing.shape = this.makeRightWing(this.body.getLocalCenter());
			rightWing.density = 5f;
			rightWing.friction = 0.9f;
			rightWing.restitution = 1f;
		}

		FixtureDef leftWing = new FixtureDef();
		{
			leftWing.shape = this.makeLeftWing(this.body.getLocalCenter());
			leftWing.density = 5f;
			leftWing.friction = 0.9f;
			leftWing.restitution = 1f;
		}
		this.body.setGravityScale(.2f);

		//		this.body.createFixture(left);
		this.body.createFixture(fuselage);
		this.body.createFixture(rightWing);
		this.body.createFixture(leftWing);

		this.fixtures = this.body.getFixtureList();

		//		this.controller = new ShipController(this);
	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
	}

	public void update(float delta)
	{
		//		this.controller.update(delta);
	}

	public void dispose()
	{
	}


	private PolygonShape makeFuselage()
	{
		PolygonShape shape = new PolygonShape();
		Vector2[] fuselage = new Vector2[8]; //
		{
			fuselage[0] = new Vector2(-0.5f * Ship.RAITO, 3 * Ship.RAITO); // A
			fuselage[1] = new Vector2(0.5f * Ship.RAITO, 3 * Ship.RAITO); // B
			fuselage[2] = new Vector2(1f * Ship.RAITO, 0.5f * Ship.RAITO); // C
			fuselage[3] = new Vector2(1 * Ship.RAITO, -1 * Ship.RAITO); // D
			fuselage[4] = new Vector2(0.5f * Ship.RAITO, -2 * Ship.RAITO); //E
			fuselage[5] = new Vector2(-0.5f * Ship.RAITO, -2 * Ship.RAITO); //F
			fuselage[6] = new Vector2(-1 * Ship.RAITO, -1 * Ship.RAITO); //G
			fuselage[7] = new Vector2(-1 * Ship.RAITO, 0.5f * Ship.RAITO); //H
		}
		shape.set(fuselage);
		return shape;
	}

	private PolygonShape makeRightWing(Vector2 bodyCenter)
	{
		float offset = 2;
		PolygonShape shape = new PolygonShape();
		Vector2[] wing = new Vector2[4];
		{
			wing[0] = new Vector2( (-1 + offset) * Ship.RAITO, .5f * Ship.RAITO); //A
			wing[1] = new Vector2( (1 + offset) * Ship.RAITO, 0 * Ship.RAITO); //B
			wing[2] = new Vector2( (1 + offset) * Ship.RAITO, -1 * Ship.RAITO); //C
			wing[3] = new Vector2( (-1 + offset) * Ship.RAITO, -1 * Ship.RAITO); //D
		}
		shape.set(wing);
		return shape;
	}

	private PolygonShape makeLeftWing(Vector2 bodyCenter)
	{
		PolygonShape shape = new PolygonShape();
		float offset = -2;
		Vector2[] wing = new Vector2[4];
		{
			wing[0] = new Vector2( (-1 + offset) * Ship.RAITO, 0f * Ship.RAITO); //A
			wing[1] = new Vector2( (-1 + offset) * Ship.RAITO, -1 * Ship.RAITO); //B
			wing[2] = new Vector2( (1 + offset) * Ship.RAITO, -1 * Ship.RAITO); //C
			wing[3] = new Vector2( (1 + offset) * Ship.RAITO, .5f * Ship.RAITO); //D
		}
		shape.set(wing);

		return shape;
	}
}
