package com.mygdx.fallingblocks;

import com.badlogic.gdx.Game;
import com.mygdx.fallingblocks.screens.*;
import com.mygdx.fallingblocks.utilities.AssetManagerWrapper;

public class FallingBlocks extends Game {

	private final AssetManagerWrapper assetManagerWrapper;

	private final GameScreen gameScreen;
	private final LoadingScreen loadingScreen;
	private final MainMenuScreen mainMenuScreen;

	public FallingBlocks(){
		assetManagerWrapper= new AssetManagerWrapper();
		gameScreen = new GameScreen(this, assetManagerWrapper);
		loadingScreen= new LoadingScreen(this, assetManagerWrapper);
		mainMenuScreen = new MainMenuScreen(this);
	}

	@Override
	public void create() {
		setScreen(loadingScreen);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}


	public AssetManagerWrapper getAssetManagerWrapper() {
		return assetManagerWrapper;
	}

	public LoadingScreen getLoadingScreen() {
		return loadingScreen;
	}

	public MainMenuScreen getMainMenuScreen() {
		return mainMenuScreen;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}
}

