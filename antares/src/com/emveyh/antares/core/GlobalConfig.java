package com.emveyh.antares.core;

public class GlobalConfig {
	private static final GlobalConfig INSTANCE = new GlobalConfig();
	
	public static GlobalConfig getInstance() {
		return GlobalConfig.INSTANCE;
	}
	
	private GlobalConfig() {}
	
	public static int FIXED_TILESIZE = 64;
	
	private int viewWidth;
	private int viewHeight;
	
	private boolean showMap;
	
	private GameState currentGameState;

	public int getViewWidth() {
		return viewWidth;
	}

	public void setViewWidth(int viewWidth) {
		this.viewWidth = viewWidth;
	}

	public int getViewHeight() {
		return viewHeight;
	}

	public void setViewHeight(int viewHeight) {
		this.viewHeight = viewHeight;
	}

	public GameState getCurrentGameState() {
		return currentGameState;
	}

	public void setCurrentGameState(GameState currentGameState) {
		this.currentGameState = currentGameState;
	}

	public boolean isShowMap() {
		return showMap;
	}

	public void setShowMap(boolean showMap) {
		this.showMap = showMap;
	}
	
	
	
}
