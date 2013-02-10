package com.emveyh.antares.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emveyh.antares.entity.EntityManager;
import com.emveyh.antares.input.InputManager;
import com.emveyh.antares.map.MapManager;

public class GdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	@Override
	public void create() {
		int viewWidth = 800;
		int viewHeight = 480;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, viewWidth, viewHeight);
		
		GlobalConfig.getInstance().setViewWidth(viewWidth);
		GlobalConfig.getInstance().setViewHeight(viewHeight);
		GlobalConfig.getInstance().setCurrentGameState(GameState.NORMAL);
		
		batch = new SpriteBatch();
		
		TextureManager.getInstance().init();
		//
		MapManager.getInstance().loadTestMap();
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
		
		MapManager.getInstance().getGameMap().render(batch);
		EntityManager.getInstance().getPlayer().render(batch);
		
		
		batch.end();
		
		InputManager.getInstance().tick();
		System.out.println(Gdx.graphics.getFramesPerSecond());
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
