package com.emveyh.antares.map;

import com.emveyh.antares.object.GameObjectManager;

public class BuildingManager {

	private static final BuildingManager INSTANCE = new BuildingManager();

	public static BuildingManager getInstance() {
		return BuildingManager.INSTANCE;
	}

	private BuildingManager() {
	}

	public void buildAt(int tileX, int tileY, Tile tileToBuild) {

		if (tileX >= 0 && tileY >= 0 && tileX < MapManager.getInstance().getCurrentMap().getTiles().length
				&& tileY < MapManager.getInstance().getCurrentMap().getTiles()[0].length) {
			if (GameObjectManager.getInstance().getGameObjectAt(tileX, tileY) == null) {
				MapManager.getInstance().getCurrentMap().getRemoveableTiles()[tileX][tileY] = tileToBuild;
			}
		}

	}
	
	public void removeAt(int tileX, int tileY) {
		if (tileX >= 0 && tileY >= 0 && tileX < MapManager.getInstance().getCurrentMap().getRemoveableTiles().length
				&& tileY < MapManager.getInstance().getCurrentMap().getRemoveableTiles()[0].length) {
			if(MapManager.getInstance().getCurrentMap().getRemoveableTiles()[tileX][tileY] != null) {
				MapManager.getInstance().getCurrentMap().getRemoveableTiles()[tileX][tileY] = null;
			}
		}
				
	}

}
