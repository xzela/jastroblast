package org.doublelong.jastroblast.entity;

import java.util.List;


public abstract class Menu
{
	public int currentMenuIndex;
	protected List<String> elements;

	public Menu(List<String> elements)
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

	public abstract void select();
}
