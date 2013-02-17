package com.emveyh.antares.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.emveyh.antares.map.MapManager;
import com.emveyh.antares.map.Tile;
import com.emveyh.antares.utils.Coord;

public class Player extends Entity {

	private boolean actionEventReady = true;

	public Player(float x, float y, float speed, EntityAnimationTextures entityAnimationTextures) {
		super(x, y, speed, entityAnimationTextures);
	}

	@Override
	public void tickLogic() {
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			EntityManager.getInstance().getPlayer().moveX(true);
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			EntityManager.getInstance().getPlayer().moveX(false);
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			EntityManager.getInstance().getPlayer().moveY(true);
		} else if (Gdx.input.isKeyPressed(Keys.UP)) {
			EntityManager.getInstance().getPlayer().moveY(false);
		}

		if (Gdx.input.isKeyPressed(Keys.X)) {
			if (actionEventReady) {
				Coord coord = this.getTileNextToEntity();
				if (MapManager.getInstance().getGameMap().getTiles()[coord.getX()][coord.getY()] == Tile.FLOOR) {
					MapManager.getInstance().getGameMap().getTiles()[coord.getX()][coord.getY()] = Tile.WALL;
				} else {
					MapManager.getInstance().getGameMap().getTiles()[coord.getX()][coord.getY()] = Tile.FLOOR;
				}
				actionEventReady = false;
			}
		} else {
			actionEventReady = true;
		}

	}

}
