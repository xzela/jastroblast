package org.doublelong.jastroblast.controller;

import org.doublelong.jastroblast.Inputs;
import org.doublelong.jastroblast.screen.AbstractScreen;

import com.badlogic.gdx.InputProcessor;

public class MenuController implements InputProcessor
{
	private AbstractScreen screen;

	public MenuController(AbstractScreen screen)
	{
		this.screen = screen;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode)
		{
		case Inputs.MENU_UP:
			this.screen.menu.moveUp();
			this.screen.menu.updateCursor();
			break;
		case Inputs.MENU_DOWN:
			this.screen.menu.moveDown();
			this.screen.menu.updateCursor();
			break;
		case Inputs.MENU_SELECT:
			this.screen.selectScreen(this.screen.menu.getCurrentScreen());
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