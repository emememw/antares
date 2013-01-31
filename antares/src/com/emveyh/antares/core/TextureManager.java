package com.emveyh.antares.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureManager {

	private static final TextureManager INSTANCE = new TextureManager();
	
	public static TextureManager getInstance() {
		return TextureManager.INSTANCE;
	}
	
	private TextureManager() {}
	
	private TextureRegion[][] sprites;
	private Texture spritesTexture;
	
	private TextureRegion[][] tiles;
	private Texture tilesTexture;
	
	private Texture instructionTexture;
	
	public void init() {
		
		tilesTexture = new  Texture(Gdx.files.internal("data/tiles.png"));
		tiles = fillTextureRegionArray(tilesTexture, 16);
		
		spritesTexture = new  Texture(Gdx.files.internal("data/sprites.png"));
		sprites= fillTextureRegionArray(spritesTexture, 16);
		
	}
	
	private TextureRegion[][] fillTextureRegionArray(Texture texture , int splitSize) {
		
		
		TextureRegion[][] textureRegionArray = new TextureRegion[texture.getWidth()/splitSize][texture.getHeight()/splitSize];
		
		for(int y = 0; y < textureRegionArray[0].length; y++) {
			for(int x = 0; x < textureRegionArray.length; x++) {
				textureRegionArray[x][y] = new TextureRegion(texture, x*splitSize, y*splitSize, splitSize, splitSize);
			}
		}
		return textureRegionArray;
	}
	
	public void dispose() {
		spritesTexture.dispose();
	}

	public TextureRegion[][] getSprites() {
		return sprites;
	}

	public TextureRegion[][] getTiles() {
		return tiles;
	}

	public Texture getInstructionTexture() {
		return instructionTexture;
	}
	
	
	
	
}
