package org.doublelong.jastroblast.entity;

import java.util.List;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class Menu
{
	public int currentMenuIndex;
	public List<MenuButton> elements;
	private Actor cursor;

	public Menu(List<MenuButton> elements, Actor cursor)
	{
		this.currentMenuIndex = 0;
		this.elements = elements;
		this.cursor = cursor;
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

	public void updateCursor()
	{
		Label button = this.elements.get(this.currentMenuIndex).getLabel();
		this.setCursorPosition(button);
	}

	public void setCursorPosition(Label element)
	{
		Vector2 pos = new Vector2(element.getX(), element.getY());
		this.cursor.setX(pos.x - (this.cursor.getWidth() + 10f));
		this.cursor.setY(pos.y + 5f);
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
		return this.elements.get(this.currentMenuIndex).select();
	}
}
