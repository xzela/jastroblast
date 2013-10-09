package org.doublelong.jastroblast.entity;

import java.util.List;

public abstract class Menu
{
	protected int currentMenuIndex;
	protected List<String> elements;

	public Menu(List<String> elements)
	{
		super();
		this.currentMenuIndex = 0;
		this.elements = elements;
	}


}
