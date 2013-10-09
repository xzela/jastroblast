package org.doublelong.jastroblast.entity;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Menu
{

	public MainMenu()
	{
		super(loadMenuItems());
	}

	private static List<String> loadMenuItems()
	{
		List<String> list = new ArrayList<String>();
		list.add("Play!");
		list.add("Credits");
		list.add("Options");
		list.add("Quit");
		return list;
	}

}
