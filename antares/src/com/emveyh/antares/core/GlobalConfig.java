package com.emveyh.antares.core;

public class GlobalConfig {
	//asdfaaa
	private static final GlobalConfig INSTANCE = new GlobalConfig();
	
	public static GlobalConfig getInstance() {
		return GlobalConfig.INSTANCE;
	}
	
	private GlobalConfig() {}
	
	public static int FIXED_TILESIZE = 32;
	
	private int viewWidth;
	private int viewHeight;

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
	
	
	
}
