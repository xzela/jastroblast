package org.doublelong.jastroblast;

import org.doublelong.jastroblast.controller.ShipController;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Inputs implements InputProcessor
{

	private final ShipController controller;

	public Inputs(ShipController controller)
	{
		this.controller = controller;
	}

	@Override
	public boolean keyDown(int keycode)
	{
		if (keycode == Keys.LEFT)
		{
			this.controller.leftPressed();
		}
		if (keycode == Keys.RIGHT)
		{
			this.controller.rightPressed();
		}
		if (keycode == Keys.UP)
		{
			this.controller.upPressed();
		}
		if (keycode == Keys.DOWN)
		{
			this.controller.downPressed();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		if (keycode == Keys.LEFT)
		{
			this.controller.leftReleased();
		}
		if (keycode == Keys.RIGHT)
		{
			this.controller.rightReleased();
		}
		if (keycode == Keys.UP)
		{
			this.controller.upReleased();
		}
		if (keycode == Keys.DOWN)
		{
			this.controller.downReleased();
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
