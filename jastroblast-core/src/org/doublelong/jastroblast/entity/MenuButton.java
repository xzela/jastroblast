package org.doublelong.jastroblast.entity;

import org.doublelong.jastroblast.JastroBlast;
import org.doublelong.jastroblast.loaders.FontManager;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class MenuButton
{
	private String label;
	public String getLabel() { return this.label;}
	private BitmapFont font;
	private Color color;

	private Screen screen;
	public Screen getScreen() { return this.screen;}
	public void setScreen(Screen screen) { this.screen = screen; }

	public MenuButton(String label)
	{
		this.label = label;
		this.font = JastroBlast.manager.get(FontManager.BLOCK_FONT, BitmapFont.class);
		this.color = Color.RED;
	}

	public Label render()
	{
		return new Label(this.label, new LabelStyle(this.font, this.color));
	}


}
