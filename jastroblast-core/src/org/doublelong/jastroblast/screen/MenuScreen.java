package org.doublelong.jastroblast.screen;

import java.util.ArrayList;
import java.util.List;

import org.doublelong.jastroblast.Inputs;
import org.doublelong.jastroblast.JastroBlast;
import org.doublelong.jastroblast.entity.Menu;
import org.doublelong.jastroblast.entity.MenuButton;
import org.doublelong.jastroblast.loaders.SoundManager;
import org.doublelong.jastroblast.loaders.TextureManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MenuScreen extends AbstractScreen
{
	private Stage stage;
	public JastroBlast game;
	private Image logo;
	private Music menuMusic;
	private Table table;
	private Menu menu;
	private Image cursor;

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
		this.menuMusic = game.manager.get(SoundManager.MENU_MUSIC, Music.class);
		this.menuMusic.setLooping(true);
		this.menuMusic.play();
		this.table = new Table();
		this.stage = new Stage();
		this.logo = new Image(this.game.manager.get(TextureManager.LOGO, Texture.class));
		this.cursor = new Image(this.game.manager.get(TextureManager.MENU_CURSOR, Texture.class));
		this.menu = new Menu(this.loadMenuItems());
		this.initializeTable();

	}

	private List<MenuButton> loadMenuItems()
	{
		List<MenuButton> list = new ArrayList<MenuButton>();
		list.add(new MenuButton("Play!", new JastroScreen(this.game, true)));
		list.add(new MenuButton("Options", null));
		list.add(new MenuButton("Credits", null));
		list.add(new MenuButton("Quit", null));
		return list;
	}

	private void initializeTable()
	{
		this.table.debug();
		//this.table.setFillParent(true);

		for(MenuButton button : this.menu.elements)
		{
			this.table.add(button.render());
			this.table.row();
		}
	}

	@Override
	public void show()
	{
		this.stage.addActor(this.table);
		this.stage.addActor(this.logo);
		Gdx.input.setInputProcessor(new MenuController(this.menu));
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
		if(Gdx.input.isKeyPressed(Keys.SPACE))
		{
			this.ready = true;
		}
		this.stage.act();
		this.stage.draw();
		if(this.ready)
		{
			//this.loadGame();
		}
		Table.drawDebug(this.stage);
	}

	private void loadGame()
	{
		this.logo.addAction(Actions.sequence(Actions.fadeOut(1.75f), Actions.run(this.onMenuFinish)));
		if (this.menuMusic.getVolume() > 0)
		{
			this.menuMusic.setVolume(this.menuMusic.getVolume() - 0.01f);
		}
	}

	public void select(Screen screen)
	{
		this.game.setScreen(screen);
	}




	class MenuController implements InputProcessor
	{
		private Menu menu;

		public MenuController(Menu menu)
		{
			this.menu = menu;
		}

		@Override
		public boolean keyDown(int keycode) {
			switch(keycode)
			{
			case Inputs.MENU_UP:
				this.menu.moveUp();
				break;
			case Inputs.MENU_DOWN:
				this.menu.moveDown();
				break;
			case Inputs.MENU_SELECT:
				select(this.menu.select());
				break;
			}
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
	}
}
