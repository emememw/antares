package com.emveyh.antares.object;

import com.emveyh.antares.core.TextureManager;

public class Tree extends DestructableGameObject {

	public Tree(int tileX, int tileY) {
		super(TextureManager.getInstance().getSprites()[0][1], tileX, tileY, 2);
	}

}
