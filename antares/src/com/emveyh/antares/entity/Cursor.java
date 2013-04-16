package com.emveyh.antares.entity;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emveyh.antares.core.GameState;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.core.TextureManager;
import com.emveyh.antares.input.InputManager;
import com.emveyh.antares.map.MapManager;
import com.emveyh.antares.map.Tile;
import com.emveyh.antares.utils.Coord;

public class Cursor {

	private static final Cursor INSTANCE = new Cursor();
	
	public static Cursor getInstance() {
		return Cursor.INSTANCE;
	}
	
	private Cursor(){}
	
	private int tileX;
	private int tileY;
	
	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.getInstance().getSprites()[1][0], tileX*GlobalConfig.FIXED_TILESIZE, tileY*GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);
	}
	
	public void tick() {
		if(InputManager.getInstance().isKeyPressed(Keys.ESCAPE) || InputManager.getInstance().isKeyPressed(Keys.B)) {
			GlobalConfig.getInstance().setCurrentGameState(GameState.NORMAL);
			InputManager.getInstance().resetKey(Keys.B);
			InputManager.getInstance().resetKey(Keys.ESCAPE);
		} else {
			
			if(InputManager.getInstance().isKeyPressed(Keys.LEFT)) {
				this.tileX--;
				InputManager.getInstance().resetKey(Keys.LEFT);
			} else if(InputManager.getInstance().isKeyPressed(Keys.RIGHT)) {
				this.tileX++;
				InputManager.getInstance().resetKey(Keys.RIGHT);
			}
			
			if(InputManager.getInstance().isKeyPressed(Keys.DOWN)) {
				this.tileY--;
				InputManager.getInstance().resetKey(Keys.DOWN);
			} else if(InputManager.getInstance().isKeyPressed(Keys.UP)) {
				this.tileY++;
				InputManager.getInstance().resetKey(Keys.UP);
			}
			
			if (InputManager.getInstance().isKeyPressed(Keys.X)) {
				if (MapManager.getInstance().getCurrentMap().getTiles()[tileX][tileY] == Tile.STONE) {
					MapManager.getInstance().getCurrentMap().getTiles()[tileX][tileY] = Tile.GRASS;
				} else {
					MapManager.getInstance().getCurrentMap().getTiles()[tileX][tileY] = Tile.STONE;
				}
				InputManager.getInstance().resetKey(Keys.X);
			}

			if (InputManager.getInstance().isKeyPressed(Keys.Y)) {
				if (MapManager.getInstance().getCurrentMap().getTiles()[tileX][tileY] == Tile.WOODEN_FLOOR) {
					MapManager.getInstance().getCurrentMap().getTiles()[tileX][tileY] = Tile.GRASS;
				} else {
					MapManager.getInstance().getCurrentMap().getTiles()[tileX][tileY] = Tile.WOODEN_FLOOR;
				}
				InputManager.getInstance().resetKey(Keys.Y);
			}
			
			
			
		}
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}
	
	
	
}
