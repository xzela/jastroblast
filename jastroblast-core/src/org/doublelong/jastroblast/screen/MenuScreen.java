package org.doublelong.jastroblast.screen;

import org.doublelong.jastroblast.JastroBlast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MenuScreen extends AbstractScreen
{
	private Stage stage;
	public JastroBlast game;
	private Image logo;
	private Music menuMusic;
	private Table table;

	private boolean ready = false;

	Runnable onMenuFinish = new Runnable()
	{
		@Override
		public void run() {
			menuMusic.stop();
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
		this.table = new Table();
		this.stage = new Stage();
		this.initializeTable();
	}

	private void initializeTable()
	{
		this.table.setFillParent(true);
		this.table.add(new Label("Start!", new LabelStyle(this.game.manager.get("assets/fonts/kenpixel_blocks.ttf", BitmapFont.class), Color.RED)));
		this.table.row();
		this.table.add(new Label("Options", new LabelStyle(this.game.manager.get("assets/fonts/kenpixel_blocks.ttf", BitmapFont.class), Color.RED)));
		this.table.row();
		this.table.add(new Label("Credits", new LabelStyle(this.game.manager.get("assets/fonts/kenpixel_blocks.ttf", BitmapFont.class), Color.RED)));
		this.table.row();
		this.table.add(new Label("Quit!", new LabelStyle(this.game.manager.get("assets/fonts/kenpixel_blocks.ttf", BitmapFont.class), Color.RED)));
	}

	@Override
	public void show()
	{
		this.logo = new Image(this.game.manager.get("assets/images/jastroblast_logo.png", Texture.class));
		this.stage.addActor(this.table);
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
		this.logo.addAction(Actions.sequence(Actions.fadeOut(1.75f), Actions.run(this.onMenuFinish)));
		if (this.menuMusic.getVolume() > 0)
		{
			this.menuMusic.setVolume(this.menuMusic.getVolume() - 0.01f);
		}
	}
}
