package org.doublelong.jastroblast.entity;

import java.util.List;

import org.doublelong.jastroblast.controller.MenuController;


public class Menu
{
	public int currentMenuIndex;
	public List<MenuButton> elements;
	public MenuController controller;


	public Menu(List<MenuButton> elements)
	{
		this.currentMenuIndex = 0;
		this.elements = elements;
		this.controller = new MenuController(this);
	}

	public void moveUp()
	{
		if(!this.atTop())
		{
			this.currentMenuIndex--;
		}
	}

	public void moveDown()
	{
		if(!this.atBottom())
		{
			this.currentMenuIndex++;
		}
	}

	private boolean atTop()
	{
		return this.currentMenuIndex == 0;
	}

	private boolean atBottom()
	{
		return this.currentMenuIndex == this.elements.size() - 1;
	}

	public void select()
	{
		MenuButton button = this.elements.get(this.currentMenuIndex);
		System.out.println(button.getLabel());
	}
}
