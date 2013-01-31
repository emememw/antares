package com.emveyh.antares.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.core.TextureManager;
import com.emveyh.antares.map.MapManager;
import com.emveyh.antares.utils.Coord;

public class Entity extends Sprite {

	private Coord tilePosition;

	public Entity(TextureRegion texture) {
		this.setRegion(texture);
	}

	public Coord getGridCoord() {
		return tilePosition;
	}

	public void setGridCoord(Coord gridCoord) {
		this.tilePosition = gridCoord;
	}

	public void moveTo(int xTilePosition, int yTilePosition) {
		if (xTilePosition >= 0 && xTilePosition < MapManager.getInstance().getCurrentGameMap().getTiles().length && yTilePosition >= 0
				&& yTilePosition < MapManager.getInstance().getCurrentGameMap().getTiles()[xTilePosition].length) {

			MapManager.getInstance().getCurrentGameMap().getTiles()[this.getGridCoord().getX()][this.getGridCoord().getY()].setEntity(null);
			MapManager.getInstance().getCurrentGameMap().getTiles()[xTilePosition][yTilePosition].setEntity(this);
			this.setGridCoord(new Coord(xTilePosition, yTilePosition));
			
		}
	}

	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.getInstance().getSprites()[2][0], this.tilePosition.getX() * GlobalConfig.FIXED_TILESIZE, this.tilePosition.getY()
				* GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);
	}

}
