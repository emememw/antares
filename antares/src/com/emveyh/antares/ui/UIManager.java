package com.emveyh.antares.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emveyh.antares.core.CameraManager;
import com.emveyh.antares.core.TextureManager;
import com.emveyh.antares.map.MapManager;
import com.emveyh.antares.map.Tile;
import com.emveyh.antares.map.WorldTileType;

public class UIManager {

	private static final UIManager INSTANCE = new UIManager();

	public static UIManager getInstance() {
		return UIManager.INSTANCE;
	}

	private UIManager() {
	}

	public void render(SpriteBatch batch) {

		renderWorldMap(batch);

	}

	private void renderWorldMap(SpriteBatch batch) {

		int tileSize = 1;
		int xOffset = 100;
		int yOffset = -100;

		for (int x = 0; x < MapManager.getInstance().getWorld().getTiles().length; x++) {
			for (int y = 0; y < MapManager.getInstance().getWorld().getTiles()[x].length; y++) {
				if (MapManager.getInstance().getWorld().getTiles()[x][y] != Tile.WATER) {
					batch.draw(TextureManager.getInstance().getSprites()[0][0], x * tileSize + xOffset + CameraManager.getInstance().getCamera().position.x, y
							* tileSize + yOffset + CameraManager.getInstance().getCamera().position.y, tileSize, tileSize);
				}
			}
		}

	}

}
