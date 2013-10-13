package org.doublelong.jastroblast.entity;

import java.util.ArrayList;
import java.util.List;

import org.doublelong.jastroblast.JastroBlast;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CreditsMenu extends Menu
{
	private JastroBlast game;

	public CreditsMenu(JastroBlast game, Actor cursor)
	{
		super(game, cursor);
		this.game = game;
		this.table = new Table();
		this.elements = this.loadMenuItems();

		this.initializeTable();
	}

	private List<MenuButton> loadMenuItems()
	{
		List<MenuButton> list = new ArrayList<MenuButton>();
		{
			list.add(new MenuButton("Back!", "org.doublelong.jastroblast.screen.MainScreen"));
		}
		return list;
	}
}
