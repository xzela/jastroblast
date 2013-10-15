package org.doublelong.jastroblast.entity;

import org.doublelong.jastroblast.JastroBlast;
import org.doublelong.jastroblast.loaders.FontManager;
import org.doublelong.jastroblast.screen.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class MenuButton
{
	private Label label;
	public Label getLabel() { return this.label; }
	private String text;
	public String getText() { return this.text;}

	private Screens screen;
	public Screens getScreen() { return this.screen; }

	private BitmapFont font;
	private Color color;

	public MenuButton(String text, Screens screen)
	{
		this.text = text;
		this.screen = screen;
		this.font = JastroBlast.manager.get(FontManager.BLOCK_FONT, BitmapFont.class);
		this.color = Color.RED;
	}

	public Label renderLabel()
	{
		this.label = new Label(this.text, new LabelStyle(this.font, this.color));
		return this.label;
	}
}
