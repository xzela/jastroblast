package org.doublelong.jastroblast.screen;

import org.doublelong.jastroblast.JastroBlast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class LoadingScreen extends AbstractScreen
{
	private Stage stage;

	private Image logo;

	Runnable onLoadingFinish = new Runnable()
	{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			game.setScreen(new MenuScreen(game));
		}

	};

	public LoadingScreen(JastroBlast game)
	{
		super(game);
		this.stage = new Stage();
		this.load();
	}

	public void load()
	{
		this.game.manager.load("assets/images/jastroblast_loading.png", Texture.class);
		// call finishLoading to BLOCK the application
		// this will load the "loading" image and then
		// progressively load everything else.
		this.game.manager.finishLoading();
	}

	@Override
	public void show()
	{
		this.game.manager.load("assets/images/jastroblast_logo.png", Texture.class);
		this.game.manager.load("assets/sounds/vampire_fire.mp3", Music.class);
		this.game.manager.load("assets/sounds/inside_space_station_5.mp3", Music.class);
		this.game.manager.load("assets/images/asteriod_big.png", Texture.class);
		this.game.manager.load("assets/images/player.png", Texture.class);

		this.logo = new Image(this.game.manager.get("assets/images/jastroblast_loading.png", Texture.class));
		this.stage.addActor(this.logo);
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
		if (this.game.manager.isLoaded("assets/images/jastroblast_loading.png"))
		{
			this.stage.act();
			this.stage.draw();

			if(this.game.manager.update())
			{
				this.logo.addAction(Actions.sequence(Actions.fadeOut(.75f), Actions.run(this.onLoadingFinish)));
				//this.game.setScreen(new MenuScreen(this.game));
			}
		}
	}
}
