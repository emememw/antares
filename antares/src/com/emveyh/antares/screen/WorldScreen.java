package com.emveyh.antares.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emveyh.antares.entity.EntityManager;
import com.emveyh.antares.map.MapManager;
import com.emveyh.antares.object.GameObjectManager;
import com.emveyh.antares.ui.UIManager;

public class WorldScreen implements Screen {

	@Override
	public void render(SpriteBatch batch) {
		MapManager.getInstance().getCurrentMap().render(batch);
		GameObjectManager.getInstance().render(batch);
		EntityManager.getInstance().render(batch);
		UIManager.getInstance().render(batch);
	}

}
