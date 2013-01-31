package com.emveyh.antares;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emveyh.antares.entity.Entity;
import com.emveyh.antares.map.GameMap;

public class GdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	
	private GameMap gameMap;
	
	private Entity entity;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		
		TextureManager.getInstance().init();
		this.gameMap = new GameMap();
		entity = new Entity(TextureManager.getInstance().getSprites()[0][0]);
		entity.setGridCoord(new Coord(0,0));
	}

	@Override
	public void dispose() {
		batch.dispose();
		TextureManager.getInstance().dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0,0,0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		gameMap.renderMap(batch);
		
		batch.end();
		
		if(Gdx.input.justTouched()) {
			int x = (int)(Gdx.input.getX()-48)/64;
			int y = 6-(int)(Gdx.input.getY()-16)/64;
			if(x < 0) {
				x = 0;
			} else if(x > 10) {
				x = 10;
			}
			if(y < 0) {
				y = 0;
			} else if(y > 6) {
				y = 6;
			}
			gameMap.getTiles()[entity.getGridCoord().getX()][entity.getGridCoord().getY()].setEntity(null);
			gameMap.getTiles()[x][y].setEntity(entity);
			entity.setGridCoord(new Coord(x,y));
			System.out.println(x+":"+y);
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
