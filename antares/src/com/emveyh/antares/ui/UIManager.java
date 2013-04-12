package com.emveyh.antares.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emveyh.antares.core.CameraManager;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.core.TextureManager;
import com.emveyh.antares.entity.EntityManager;
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
		if (GlobalConfig.getInstance().isShowMap()) {
			int tileSize = 1;
			int xOffset = -200;
			int yOffset = -300;

			for (int x = 0; x < MapManager.getInstance().getWorld().getTiles().length; x++) {
				for (int y = 0; y < MapManager.getInstance().getWorld().getTiles()[x].length; y++) {
					if (MapManager.getInstance().getWorld().getTiles()[x][y] != Tile.WATER
							&& MapManager.getInstance().getWorld().getTiles()[x][y] != Tile.RIVER) {
						batch.draw(TextureManager.getInstance().getSprites()[0][0],
								x * tileSize + xOffset + CameraManager.getInstance().getCamera().position.x, y * tileSize + yOffset
										+ CameraManager.getInstance().getCamera().position.y, tileSize, tileSize);
					}

				}
			}

			for (int x = 0; x < MapManager.getInstance().getWorld().getTiles().length; x++) {
				for (int y = 0; y < MapManager.getInstance().getWorld().getTiles()[x].length; y++) {
					if ((int) (EntityManager.getInstance().getPlayer().getX() / GlobalConfig.FIXED_TILESIZE) == x
							&& (int) (EntityManager.getInstance().getPlayer().getY() / GlobalConfig.FIXED_TILESIZE) == y) {
						batch.draw(TextureManager.getInstance().getSprites()[3][0],
								x * tileSize + xOffset + CameraManager.getInstance().getCamera().position.x, y * tileSize + yOffset
										+ CameraManager.getInstance().getCamera().position.y, 8, 8);
					}

				}
			}
		}
	}

}
