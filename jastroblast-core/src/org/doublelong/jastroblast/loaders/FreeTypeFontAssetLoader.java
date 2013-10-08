package org.doublelong.jastroblast.loaders;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;

public class FreeTypeFontAssetLoader extends SynchronousAssetLoader<BitmapFont, AssetLoaderParameters<BitmapFont>>
{

	public FreeTypeFontAssetLoader(FileHandleResolver resolver) {
		super(resolver);
	}

	@Override
	public BitmapFont load(AssetManager assetManager, String fileName, FileHandle file, AssetLoaderParameters<BitmapFont> parameter) {
		FreeTypeFontGenerator f = new FreeTypeFontGenerator(resolve(fileName));
		return f.generateFont(14);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Array getDependencies(String fileName, FileHandle file, AssetLoaderParameters<BitmapFont> parameter) {
		return null;
	}

}
