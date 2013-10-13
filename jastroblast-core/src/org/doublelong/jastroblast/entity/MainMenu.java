package org.doublelong.jastroblast.entity;

import java.util.ArrayList;
import java.util.List;

import org.doublelong.jastroblast.JastroBlast;
import org.doublelong.jastroblast.screen.JastroScreen;
import org.doublelong.jastroblast.screen.QuitScreen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MainMenu extends Menu
{
	private JastroBlast game;

	public MainMenu(JastroBlast game, Actor cursor) {
		super(cursor);
		this.table = new Table();
		this.game = game;
		this.elements = this.loadMenuItems();

		this.initializeTable();
	}

	@Override
	public void selectScreen()
	{
		System.out.println("inside Main Menu");
		this.game.setScreen(this.elements.get(this.currentMenuIndex).getScreen());
	}

	private List<MenuButton> loadMenuItems()
	{
		List<MenuButton> list = new ArrayList<MenuButton>();
		{
			list.add(new MenuButton("Play!", new JastroScreen(this.game, true)));
			list.add(new MenuButton("Options", null));
			list.add(new MenuButton("Credits", null));
			list.add(new MenuButton("Quit", new QuitScreen(this.game)));
		}
		return list;
	}

	private void initializeTable()
	{
		//this.table.debug();
		this.table.setFillParent(true);

		for(MenuButton button : this.elements)
		{
			this.table.add(button.render()).left();
			this.table.row();
		}
	}
}
