package org.doublelong.jastroblast.screen;

import org.doublelong.jastroblast.JastroBlast;
import org.doublelong.jastroblast.controller.MenuController;
import org.doublelong.jastroblast.entity.MainMenu;
import org.doublelong.jastroblast.entity.Menu;
import org.doublelong.jastroblast.entity.Screens;
import org.doublelong.jastroblast.managers.ScreenManager;
import org.doublelong.jastroblast.managers.SoundManager;
import org.doublelong.jastroblast.managers.TextureManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MainScreen extends AbstractScreen
{
	private Stage stage;
	private Image logo;
	private Music menuMusic;
	private Table table;
	@Override
	public Menu getMenu() { return this.menu; }
	private Image cursor;

	Runnable onMenuFinish = new Runnable()
	{
		@Override
		public void run()
		{
			System.out.println("MainScreen Runnable run");
			menuMusic.stop();
			ScreenManager.getInstance().show(Screens.GAME);
		}
	};

	public MainScreen()
	{
		this.menuMusic = JastroBlast.manager.get(SoundManager.MENU_MUSIC, Music.class);
		this.menuMusic.setLooping(true);
		this.menuMusic.play();
		this.stage = new Stage();
		this.logo = new Image(JastroBlast.manager.get(TextureManager.LOGO, Texture.class));
		this.cursor = new Image(JastroBlast.manager.get(TextureManager.MENU_CURSOR, Texture.class));

		this.menu = new MainMenu(this.cursor);
		this.table = this.menu.getTable();
	}



	@Override
	public void show()
	{
		this.stage.addActor(this.table);
		this.stage.addActor(this.logo);
		this.stage.addActor(this.cursor);
		Gdx.input.setInputProcessor(new MenuController(this, this.menu));

	}

	@Override
	public void resize(int width, int height)
	{
		this.stage.setViewport(width, height, false);
		this.logo.setX((width - this.logo.getWidth()) / 2); //center the logo horizontally
		this.logo.setY((width - this.logo.getHeight()) / 2 + this.logo.getHeight());

		this.table.setWidth((width - this.table.getWidth()) / 2);
		this.table.setHeight(height / 2);
	}

	@Override
	public void render(float detla)
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.stage.act();
		this.stage.draw();
		this.menu.updateCursor();
		//Table.drawDebug(this.stage);
	}

	/**
	 * TODO Fix the screen fading IT DOES NOT WORK
	 * 
	 * @param screen
	 */
	public void fadeToScreen()
	{
		System.out.println("MainScreen fadeToScreen:");
		// race condition!
		this.logo.addAction(Actions.sequence(Actions.fadeOut(1.75f), Actions.run(this.onMenuFinish)));
		if (this.menuMusic.getVolume() > 0)
		{
			this.menuMusic.setVolume(this.menuMusic.getVolume() - 0.01f);
		}
		this.menuMusic.stop();
	}

	@Override
	public void transitionScreen()
	{
		System.out.println("MainScreen loadScreen:");
		this.fadeToScreen();
	}
}
