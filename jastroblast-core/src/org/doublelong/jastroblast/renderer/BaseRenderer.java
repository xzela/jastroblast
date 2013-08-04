package org.doublelong.jastroblast.renderer;

import java.text.NumberFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class BaseRenderer
{
	private final FreeTypeFontGenerator generator;

	protected final BitmapFont font;

	protected final NumberFormat format;

	public BaseRenderer()
	{
		this.generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/kenpixel_blocks.ttf"));
		this.font = this.generator.generateFont(14);

		this.format = NumberFormat.getNumberInstance();
		this.format.setMinimumFractionDigits(2);
		this.format.setMaximumFractionDigits(2);

	}
}
