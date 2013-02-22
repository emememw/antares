package com.emveyh.antares.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.map.MapManager;
import com.emveyh.antares.map.Tile;
import com.emveyh.antares.utils.Coord;
import com.emveyh.antares.utils.Direction;

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
				/*if (MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] == Tile.FLOOR) {
					MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] = Tile.WALL;
				} else {
					MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] = Tile.FLOOR;
				}
				coord = new Coord((int) this.getX() / GlobalConfig.FIXED_TILESIZE, (int) this.getY() / GlobalConfig.FIXED_TILESIZE);
				MapManager.getInstance().getCurrentMap().getTiles()[coord.getX()][coord.getY()] = Tile.FLOOR;*/
				actionEventReady = false;
			}
		} else {
			actionEventReady = true;
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
