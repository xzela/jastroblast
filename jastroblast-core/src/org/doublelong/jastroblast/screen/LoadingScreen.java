package org.doublelong.jastroblast.screen;

import org.doublelong.jastroblast.JastroBlast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class LoadingScreen extends AbstractScreen
{
	private Stage stage;

	private Image logo;

	public LoadingScreen(JastroBlast game)
	{
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show()
	{
		this.stage = new Stage();
		this.game.manager.load("assets/images/jastroblast_logo.png", Texture.class);

		this.logo = new Image(this.game.manager.get("assets/images/jastroblast_logo.png", Texture.class));
	}

	@Override
	public void resize(int width, int height)
	{
		stage.setViewport(width, height, false);
		this.logo.setX((width - logo.getWidth()) / 2);
		this.logo.setY((height - logo.getHeight()) / 2);
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if(this.game.manager.update())
		{
			if(Gdx.input.isKeyPressed(Keys.SPACE))
			{
				this.game.setScreen(new JastroScreen(this.game, true));
			}
		}
		this.stage.act();
		this.stage.draw();
	}
}
