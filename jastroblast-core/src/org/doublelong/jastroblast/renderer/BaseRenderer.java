package org.doublelong.jastroblast.renderer;

import java.text.NumberFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BaseRenderer
{
	private final FreeTypeFontGenerator generator;

	protected final boolean debug;
	protected final ShapeRenderer debugRenderer = new ShapeRenderer();

	protected final BitmapFont font;
	protected final NumberFormat format;

	public boolean debugHit = false;

	public BaseRenderer()
	{
		this.debugRenderer.setColor(Color.RED);
		this.debug = true;
		this.generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/kenpixel_blocks.ttf"));
		this.font = this.generator.generateFont(14);

		this.format = NumberFormat.getNumberInstance();
		this.format.setMinimumFractionDigits(2);
		this.format.setMaximumFractionDigits(2);

	}
}
