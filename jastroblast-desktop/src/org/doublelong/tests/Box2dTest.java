package org.doublelong.tests;

import org.doublelong.tests.entities.Ship;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Stolen from: http://programmersweb.blogspot.com/2012/07/simple-libgdx-box2d-bouncing-ball.html
 * http://www.badlogicgames.com/wordpress/?p=2017
 * 
 */
public class Box2dTest implements ApplicationListener
{
	World world;
	Ship ship;

	SpriteBatch batch;
	BitmapFont font;


	float i = 0;

	Box2DDebugRenderer debugRenderer;
	OrthographicCamera camera;

	static final float BOX_STEP = 1/60f;
	static final int BOX_VELOCITY_ITERATIONS = 6;
	static final int BOX_POSITION_ITERATIONS = 2;
	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_WORLD_TO = 100f;

	@Override
	public void create()
	{
		world = new World(new Vector2(0, 0), true);
		ship = new Ship(world);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.viewportHeight = 320;
		camera.viewportWidth = 480;
		camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);
		camera.update();

		batch = new SpriteBatch();
		font = new BitmapFont();



		//Ground body
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0, 10));
		Body groundBody = world.createBody(groundBodyDef);
		PolygonShape groundBox = new PolygonShape();
		// set the ground
		groundBox.setAsBox((camera.viewportWidth) * 2, 10.0f);
		groundBody.createFixture(groundBox, 0.0f);

		//Dynamic Body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2);
		Body body = world.createBody(bodyDef);



		// ball
		//		CircleShape dynamicCircle = new CircleShape();
		//		dynamicCircle.setRadius(10f);

		// triangle
		Vector2[] vertices = new Vector2[3];
		vertices[0] = new Vector2(-10, 0);
		vertices[1] = new Vector2(10, 0);
		vertices[2] = new Vector2(0, 20);

		BodyDef triBodyDef = new BodyDef();
		triBodyDef.type = BodyType.KinematicBody;
		triBodyDef.position.set(new Vector2(camera.viewportWidth / 2 + 5, 100));
		//triBodyDef.angle = 1f;
		// put the triangle in the world
		Body triBody = world.createBody(triBodyDef);
		PolygonShape tri = new PolygonShape();

		tri.set(vertices);

		triBody.createFixture(tri, 0f);



		// FixtureDef - properties of the fixture.
		//		FixtureDef fixtureDef = new FixtureDef();
		//		fixtureDef.shape = dynamicCircle;
		//		fixtureDef.density = 15.0f; // how dense it is
		//		fixtureDef.friction = .9f; // fiction
		//		fixtureDef.restitution = .15f; // how bouncy
		//		body.createFixture(fixtureDef);
		//
		//		dynamicCircle.dispose();
		tri.dispose();
		ship.dispose();

	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void render()
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.ship.controller.processControls();

		debugRenderer.render(world, camera.combined);
		world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);

		//camera.project()

		batch.begin();
		String str = "Anglar Vel: " + this.ship.getBody().getAngularVelocity();
		str += "\nLocal Vel: " + this.ship.getLocalVelocity();
		str += "\nLinear Vel: " + this.ship.getBody().getLinearVelocity();

		font.drawMultiLine(batch, str, this.ship.getBody().getPosition().x, this.ship.getBody().getPosition().y);
		batch.end();

		//		Iterator<Body> bodies = world.getBodies();
		//		while(bodies.hasNext())
		//		{
		//			Body b = bodies.next();
		//			if (b.getType().equals(BodyType.KinematicBody))
		//			{
		//				b.setTransform(b.getPosition(), b.getAngle() + 0.1f);
		//			}
		//		}

	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
}