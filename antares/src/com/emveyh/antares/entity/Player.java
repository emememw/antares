package com.emveyh.antares.entity;

import com.badlogic.gdx.Input.Keys;
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

		if (InputManager.getInstance().isKeyPressed(Keys.X)) {
			Coord coord = this.getTileNextToEntity();
			if (MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] == Tile.STONE) {
				MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] = Tile.GRASS;
			} else {
				MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] = Tile.STONE;
			}
			InputManager.getInstance().resetKey(Keys.X);
		}

		if (InputManager.getInstance().isKeyPressed(Keys.Y)) {
			Coord coord = this.getTileNextToEntity();
			if (MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] == Tile.WOODEN_FLOOR) {
				MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] = Tile.GRASS;
			} else {
				MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] = Tile.WOODEN_FLOOR;
			}
			InputManager.getInstance().resetKey(Keys.Y);
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

	}

}
