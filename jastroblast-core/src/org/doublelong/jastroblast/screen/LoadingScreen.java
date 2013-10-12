package org.doublelong.jastroblast.screen;

import org.doublelong.jastroblast.JastroBlast;
import org.doublelong.jastroblast.loaders.FontManager;
import org.doublelong.jastroblast.loaders.FreeTypeFontAssetLoader;
import org.doublelong.jastroblast.loaders.SoundManager;
import org.doublelong.jastroblast.loaders.TextureManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class LoadingScreen extends AbstractScreen
{
	private Stage stage;

	private Image logo;

	Runnable onLoadingFinish = new Runnable()
	{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			game.setScreen(new MenuScreen(game));
		}

	};

	public LoadingScreen(JastroBlast game)
	{
		super(game);
		this.stage = new Stage();
		this.load();
	}

	public void load()
	{
		JastroBlast.manager.load(TextureManager.LOADING, Texture.class);
		// call finishLoading to BLOCK the application
		// this will load the "loading" image and then
		// progressively load everything else.
		JastroBlast.manager.finishLoading();
	}

	@Override
	public void show()
	{
		JastroBlast.manager.setLoader(BitmapFont.class, new FreeTypeFontAssetLoader(new InternalFileHandleResolver()));
		JastroBlast.manager.load(FontManager.BLOCK_FONT, BitmapFont.class);
		JastroBlast.manager.load(TextureManager.LOGO, Texture.class);
		JastroBlast.manager.load(SoundManager.GAME_MUSIC, Music.class);
		JastroBlast.manager.load(SoundManager.MENU_MUSIC, Music.class);
		JastroBlast.manager.load(TextureManager.ASTERIOD_BIG, Texture.class);
		JastroBlast.manager.load(TextureManager.PLAYER, Texture.class);
		JastroBlast.manager.load(TextureManager.MENU_CURSOR, Texture.class);

		this.logo = new Image(JastroBlast.manager.get(TextureManager.LOADING, Texture.class));
		this.stage.addActor(this.logo);
	}

	@Override
	public void resize(int width, int height)
	{
		stage.setViewport(width, height, false);
		this.logo.setX((width - logo.getWidth()) / 2);
		this.logo.setY((height - logo.getHeight()) / 2);
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if (JastroBlast.manager.isLoaded("assets/images/jastroblast_loading.png"))
		{
			this.stage.act();
			this.stage.draw();

			if(JastroBlast.manager.update())
			{
				this.logo.addAction(Actions.sequence(Actions.fadeOut(.75f), Actions.run(this.onLoadingFinish)));
				//this.game.setScreen(new MenuScreen(this.game));
			}
		}
	}
}
