package org.doublelong.jastroblast.entity;

import java.util.ArrayList;
import java.util.List;

import org.doublelong.jastroblast.screen.Screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MainMenu extends Menu
{
	public MainMenu(Actor cursor)
	{
		super(cursor);
		this.table = new Table();
		this.elements = this.loadMenuItems();

		this.initializeTable();
	}

	private List<MenuButton> loadMenuItems()
	{
		List<MenuButton> list = new ArrayList<MenuButton>();
		{
			list.add(new MenuButton("Play!", Screens.GAME));
			list.add(new MenuButton("Options", null));
			list.add(new MenuButton("Credits", Screens.CREDITS));
			list.add(new MenuButton("Quit", Screens.QUIT));
		}
		return list;
	}
}
