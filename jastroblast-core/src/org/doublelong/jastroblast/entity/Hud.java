package org.doublelong.jastroblast.entity;

import org.doublelong.jastroblast.JastroBlast;
import org.doublelong.jastroblast.managers.FontManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Hud
{
	private Space space;

	private Stage stage;
	private Label label;
	private BitmapFont font;

	public Hud(Space space)
	{
		this.space = space;

		this.stage = new Stage();
		this.font = JastroBlast.manager.get(FontManager.BLOCK_FONT, BitmapFont.class);

		this.label = new Label("Tester", new LabelStyle(this.font, Color.RED));
		this.stage.addActor(this.label);
	}

	public void render(SpriteBatch batch, OrthographicCamera camera)
	{
		this.stage.act();
		this.stage.draw();
	}

	public void update(float delta)
	{
		this.updateLabel();
		this.stage.act();
		this.stage.draw();
	}

	private void updateLabel()
	{
		String s = String.valueOf(this.space.getShip().getBody().getAngle());
		this.label.setText(s);
	}
}
