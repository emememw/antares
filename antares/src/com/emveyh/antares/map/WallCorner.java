package com.emveyh.antares.map;

import com.emveyh.antares.TextureManager;

public class WallCorner extends Tile {

	public WallCorner(int gridX, int gridY) {
		super(TextureManager.getInstance().getTiles()[2][0], false, gridX, gridY);
	}

}
