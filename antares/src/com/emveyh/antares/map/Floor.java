package com.emveyh.antares.map;

import com.emveyh.antares.core.TextureManager;

public class Floor extends Tile {

	public Floor(int gridX, int gridY) {
		super(TextureManager.getInstance().getTiles()[1][0], true, gridX, gridY);
	}

}
