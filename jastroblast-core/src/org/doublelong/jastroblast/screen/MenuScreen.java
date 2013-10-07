package org.doublelong.jastroblast.screen;

import org.doublelong.jastroblast.JastroBlast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MenuScreen extends AbstractScreen
{
	private Stage stage;
	public JastroBlast game;
	private Image logo;
	private Music menuMusic;

	private boolean ready = false;

	Runnable onMenuFinish = new Runnable()
	{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			game.setScreen(new JastroScreen(game, true));
		}

	};

	public MenuScreen(JastroBlast game)
	{
		super(game);
		this.game = game;
		this.menuMusic = game.manager.get("assets/sounds/inside_space_station_5.mp3", Music.class);
		this.menuMusic.setLooping(true);
		this.menuMusic.play();

		this.stage = new Stage();

	}

	@Override
	public void show()
	{
		this.logo = new Image(this.game.manager.get("assets/images/jastroblast_logo.png", Texture.class));


		this.stage.addActor(this.logo);
	}

	@Override
	public void resize(int width, int height)
	{
		this.stage.setViewport(width, height, false);

		this.logo.setX((width - this.logo.getWidth()) / 2); //center the logo horizontally
		this.logo.setY((width - this.logo.getHeight()) / 2 + this.logo.getHeight());
	}

	@Override
	public void render(float detla)
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if(Gdx.input.isKeyPressed(Keys.SPACE))
		{
			this.ready = true;
		}
		this.stage.act();
		this.stage.draw();
		if(this.ready)
		{
			this.loadGame();
		}
	}

	private void loadGame()
	{
		if(!this.menuMusic.isPlaying())
		{
			this.logo.addAction(Actions.sequence(Actions.fadeOut(.1f), Actions.run(this.onMenuFinish)));
			//this.game.setScreen(new JastroScreen(this.game, true));
		}
		if (this.menuMusic.getVolume() > 0)
		{
			this.menuMusic.setVolume(this.menuMusic.getVolume() - 0.01f);
		}
		else
		{
			this.menuMusic.stop();
		}
	}
}
