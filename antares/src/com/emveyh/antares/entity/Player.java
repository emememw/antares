package com.emveyh.antares.entity;

import com.badlogic.gdx.Input.Keys;
import com.emveyh.antares.core.CameraManager;
import com.emveyh.antares.core.GameState;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.input.InputManager;
import com.emveyh.antares.map.MapManager;
import com.emveyh.antares.map.Tile;
import com.emveyh.antares.object.DestructableGameObject;
import com.emveyh.antares.object.GameObjectManager;
import com.emveyh.antares.utils.Coord;

public class Player extends Entity {

	public Player(float x, float y, float speed, EntityAnimationTextures entityAnimationTextures) {
		super(x, y, speed, entityAnimationTextures);
	}

	@Override
	public void tickLogic() {
		if (InputManager.getInstance().isKeyPressed(Keys.LEFT)) {
			EntityManager.getInstance().getPlayer().moveX(true);
		} else if (InputManager.getInstance().isKeyPressed(Keys.RIGHT)) {
			EntityManager.getInstance().getPlayer().moveX(false);
		}
		if (InputManager.getInstance().isKeyPressed(Keys.DOWN)) {
			EntityManager.getInstance().getPlayer().moveY(true);
		} else if (InputManager.getInstance().isKeyPressed(Keys.UP)) {
			EntityManager.getInstance().getPlayer().moveY(false);
		}

		
		if (InputManager.getInstance().isKeyPressed(Keys.M)) {
			GlobalConfig.getInstance().setShowMap(!GlobalConfig.getInstance().isShowMap());
			InputManager.getInstance().resetKey(Keys.M);
		}
		if(InputManager.getInstance().isKeyPressed(Keys.SPACE)) {
			Coord coord = this.getGameObjectCoordInFrontOfEntity();
			if(coord != null) {
				GameObjectManager.getInstance().getGameObjectAt(coord.getX(), coord.getY()).hit();
			}
			InputManager.getInstance().resetKey(Keys.SPACE);
		}
		if(InputManager.getInstance().isKeyPressed(Keys.B)) {
			GlobalConfig.getInstance().setCurrentGameState(GameState.BUILD_MODE);
			Cursor.getInstance().setTileX((int)this.getX()/GlobalConfig.FIXED_TILESIZE);
			Cursor.getInstance().setTileY((int)this.getY()/GlobalConfig.FIXED_TILESIZE);
			InputManager.getInstance().resetKey(Keys.B);
		}

	}

}
