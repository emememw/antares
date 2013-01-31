package com.emveyh.antares.map;

import com.emveyh.antares.core.TextureManager;

public class Wall extends Tile {

	public Wall(int gridX, int gridY) {
		super(TextureManager.getInstance().getTiles()[6][0], false, gridX, gridY);
	}

}
