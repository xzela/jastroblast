package org.doublelong.jastroblast.screen;

import org.doublelong.jastroblast.JastroBlast;
import org.doublelong.jastroblast.controller.MenuController;
import org.doublelong.jastroblast.entity.CreditsMenu;
import org.doublelong.jastroblast.entity.Screens;
import org.doublelong.jastroblast.managers.ScreenManager;
import org.doublelong.jastroblast.managers.TextureManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CreditsScreen extends AbstractScreen
{
	private Stage stage;
	private Image logo;
	private Table table;
	private Image cursor;

	public CreditsScreen()
	{
		this.stage = new Stage();
		this.logo = new Image(JastroBlast.manager.get(TextureManager.LOGO, Texture.class));
		this.cursor = new Image(JastroBlast.manager.get(TextureManager.MENU_CURSOR, Texture.class));

		this.menu = new CreditsMenu(this.cursor);
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
	public void render(float delta)
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.stage.act();
		this.stage.draw();
		this.menu.updateCursor();
	}

	@Override
	public void transitionScreen()
	{
		// TODO Auto-generated method stub
		ScreenManager.getInstance().show(Screens.GAME);
	}
}
