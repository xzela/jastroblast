package org.doublelong.tests.entities;

import org.doublelong.tests.controller.ShipController;

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
	private final World world;

	public ShipController controller;

	private final Vector2[] vertices;
	public Vector2[] getVertices() { return this.vertices; }

	private final Body body;
	public Body getBody() { return this.body; }

	private final BodyDef bodyDef = new BodyDef();
	public BodyDef getBodyDef() { return this.bodyDef; }

	private final FixtureDef fixtureDef = new FixtureDef();
	public FixtureDef getFixtureDef() { return this.fixtureDef; }

	private final Fixture fixture;
	public Fixture getFixture() { return this.fixture; }

	private final PolygonShape shape = new PolygonShape();
	public PolygonShape getShape() { return this.shape; }

	public Ship(World world)
	{
		this.world = world;
		this.vertices = this.createVertices();
		this.bodyDef.type = BodyType.DynamicBody;
		this.bodyDef.position.set(new Vector2(125f,100f));
		//this.shape.set(this.vertices);
		this.shape.setAsBox(20f, 20f);


		this.fixtureDef.shape = this.shape;
		this.fixtureDef.density = .01f;
		this.fixtureDef.friction = .0f;
		this.fixtureDef.restitution = .5f;

		// set the world stuff
		this.body = this.world.createBody(bodyDef);
		//		this.body.createFixture(this.shape, 0f);
		this.fixture = this.body.createFixture(this.fixtureDef);


		this.controller = new ShipController(this);
	}

	private Vector2[] createVertices()
	{
		Vector2[] vertices = new Vector2[3];
		vertices[0] = new Vector2(-20, 0);
		vertices[1] = new Vector2(10, 0);
		vertices[2] = new Vector2(0, 20);
		return vertices;
	}

	public void update()
	{
		// update logic here!

	}

	public void dispose()
	{
		this.shape.dispose();
	}

	public Vector2 getLocalVelocity()
	{
		return this.body.getLocalVector(this.body.getLinearVelocityFromLocalPoint(this.getBody().getPosition()));
	}
}
