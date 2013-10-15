package org.doublelong.jastroblast.entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CreditsMenu extends Menu
{
	public CreditsMenu( Actor cursor)
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
			list.add(new MenuButton("Back!", Screens.MAIN));
		}
		return list;
	}
}
