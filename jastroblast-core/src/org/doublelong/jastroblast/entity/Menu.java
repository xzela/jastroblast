package org.doublelong.jastroblast.entity;

import java.util.List;

import com.badlogic.gdx.Screen;


public class Menu
{
	public int currentMenuIndex;
	public List<MenuButton> elements;

	public Menu(List<MenuButton> elements)
	{
		this.currentMenuIndex = 0;
		this.elements = elements;
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

	public Screen select()
	{
		MenuButton button = this.elements.get(this.currentMenuIndex);
		return button.select();
	}
}
