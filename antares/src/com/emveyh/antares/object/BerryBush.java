package com.emveyh.antares.object;

import com.emveyh.antares.core.TextureManager;

public class BerryBush extends DestructableGameObject {

	public BerryBush(int tileX, int tileY) {
		super(TextureManager.getInstance().getSprites()[1][1], tileX, tileY, 2);
	}

}
