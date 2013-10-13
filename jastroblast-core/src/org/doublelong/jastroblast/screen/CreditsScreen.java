package org.doublelong.jastroblast.screen;

import java.util.ArrayList;
import java.util.List;

import org.doublelong.jastroblast.JastroBlast;
import org.doublelong.jastroblast.controller.MenuController;
import org.doublelong.jastroblast.entity.MenuButton;
import org.doublelong.jastroblast.loaders.TextureManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CreditsScreen extends AbstractScreen
{
	private JastroBlast game;

	private Stage stage;
	private Image logo;
	private Table table;
	private Image cursor;

	public CreditsScreen(JastroBlast game)
	{
		super(game);
		this.game = game;
		this.stage = new Stage();
		this.table = new Table();
		this.logo = new Image(JastroBlast.manager.get(TextureManager.LOGO, Texture.class));
		this.cursor = new Image(JastroBlast.manager.get(TextureManager.MENU_CURSOR, Texture.class));


		this.initializeTable();
	}

	private List<MenuButton> initalizeMenuElements()
	{
		List<MenuButton> list = new ArrayList<MenuButton>();
		{
			list.add(new MenuButton("Back!", new MainScreen(this.game)));
		}
		return list;
	}

	private void initializeTable()
	{
		this.table.setFillParent(true);

		for(MenuButton button : this.menu.elements)
		{
			this.table.add(button.render()).left();
			this.table.row();
		}
	}

	public void select(Screen screen)
	{
		if (screen != null)
		{
			this.game.setScreen(screen);
		}
	}

	@Override
	public void show()
	{
		this.stage.addActor(this.table);
		this.stage.addActor(this.logo);
		this.stage.addActor(this.cursor);
		Gdx.input.setInputProcessor(new MenuController(this));
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
	public void render(float delta)
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.stage.act();
		this.stage.draw();
		this.menu.updateCursor();
	}
}
