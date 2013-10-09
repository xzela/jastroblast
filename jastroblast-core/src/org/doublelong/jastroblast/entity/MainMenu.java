package org.doublelong.jastroblast.entity;

import java.util.ArrayList;
import java.util.List;

import org.doublelong.jastroblast.controller.MenuController;

public class MainMenu extends Menu
{
	public List<String> menuElements;
	public MenuController controller;

	public MainMenu()
	{
		super(loadMenuItems());
		this.menuElements = this.elements;
		this.controller = new MenuController(this);
	}

	private static List<String> loadMenuItems()
	{
		List<String> list = new ArrayList<String>();
		list.add("Play!");
		list.add("Credits");
		list.add("Options");
		list.add("Quit!");
		return list;
	}

	@Override
	public void select()
	{
		System.out.println(this.elements.get(this.currentMenuIndex));
	}


}
