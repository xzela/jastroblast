package org.doublelong.jastroblast.entity;

import java.util.List;

import org.doublelong.jastroblast.managers.ScreenManager;
import org.doublelong.jastroblast.screen.AbstractScreen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


public abstract class Menu
{

	public int currentMenuIndex;
	public List<MenuButton> elements;
	private Actor cursor;
	private AbstractScreen currentScreen;
	public AbstractScreen getCurrentScreen() { return this.currentScreen; }

	protected Table table;
	public Table getTable() { return this.table; }


	public Menu(Actor cursor)
	{
		this.currentMenuIndex = 0;
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
		//this.currentScreen = (AbstractScreen) this.elements.get(this.currentMenuIndex).getScreen();
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

	public void showScreen(Screens screen)
	{
		ScreenManager.getInstance().show(screen);
	}

	public Screen getScreen(Screens screen)
	{
		return ScreenManager.getInstance().get(screen);
	}

	protected void initializeTable()
	{
		//this.table.debug();
		this.table.setFillParent(true);

		for(MenuButton button : this.elements)
		{
			this.table.add(button.renderLabel()).left();
			this.table.row();
		}
	}
}
