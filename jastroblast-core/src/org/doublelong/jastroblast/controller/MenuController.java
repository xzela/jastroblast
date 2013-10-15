package org.doublelong.jastroblast.controller;

import org.doublelong.jastroblast.Inputs;
import org.doublelong.jastroblast.entity.Menu;
import org.doublelong.jastroblast.screen.AbstractScreen;

import com.badlogic.gdx.InputProcessor;

public class MenuController implements InputProcessor
{
	private AbstractScreen screen;
	private Menu menu;

	public MenuController(AbstractScreen screen, Menu menu)
	{
		this.screen = screen;
		this.menu = menu;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode)
		{
		case Inputs.MENU_UP:
			this.menu.moveUp();
			this.menu.updateCursor();
			break;
		case Inputs.MENU_DOWN:
			this.menu.moveDown();
			this.menu.updateCursor();
			break;
		case Inputs.MENU_SELECT:
			this.screen.transitionScreen();
			this.menu.showScreen(this.menu.elements.get(this.menu.currentMenuIndex).getScreen());
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}