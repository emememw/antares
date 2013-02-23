package com.emveyh.antares.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.map.MapManager;
import com.emveyh.antares.map.Tile;
import com.emveyh.antares.utils.Coord;
import com.emveyh.antares.utils.Direction;

public class Player extends Entity {

	private boolean actionEventReady1 = true;
	private boolean actionEventReady2 = true;
	
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
			if (actionEventReady1) {
				Coord coord = this.getTileNextToEntity();
				if (MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] == Tile.STONE) {
					MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] = Tile.GRASS;
				} else {
					MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] = Tile.STONE;
				}
				actionEventReady1 = false;
			}
		} else {
			actionEventReady1 = true;
		}
		if (Gdx.input.isKeyPressed(Keys.Y)) {
			if (actionEventReady2) {
				Coord coord = this.getTileNextToEntity();
				if (MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] == Tile.WOODEN_FLOOR) {
					MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] = Tile.GRASS;
				} else {
					MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] = Tile.WOODEN_FLOOR;
				}
				actionEventReady2 = false;
			}
		} else {
			actionEventReady2 = true;
		}

		if (shouldLoadNextMap()) {
			MapManager.getInstance().loadNextGameMap(getNextMapDirection());
		}

	}

	private boolean shouldLoadNextMap() {
		boolean result = false;
		if (this.getX() + this.getWidth() > MapManager.getInstance().getCurrentMap().getWidth() * GlobalConfig.FIXED_TILESIZE || this.getX() < 0
				|| this.getY() + this.getHeight() > MapManager.getInstance().getCurrentMap().getHeight() * GlobalConfig.FIXED_TILESIZE || this.getY() < 0) {
			result = true;
		}
		return result;
	}

	private Direction getNextMapDirection() {
		Direction result = null;
		if (this.getX() + this.getWidth() > MapManager.getInstance().getCurrentMap().getWidth() * GlobalConfig.FIXED_TILESIZE) {
			result = Direction.RIGHT;
		} else if (this.getX() < 0) {
			result = Direction.LEFT;
		} else if (this.getY() > MapManager.getInstance().getCurrentMap().getHeight()) {
			result = Direction.UP;
		} else if (this.getY() < 0) {
			result = Direction.DOWN;
		}
		return result;
	}

}
