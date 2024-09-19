package com.mygdx.fallingblocks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.fallingblocks.screens.*;
import com.mygdx.fallingblocks.utilities.AssetManagerWrapper;
import com.mygdx.fallingblocks.utilities.InputListenersManager;


public class FallingBlocks extends Game {

	private SpriteBatch spriteBatch;
	private AssetManagerWrapper assetManagerWrapper;
	private InputListenersManager inputListenersManager;

	private LoadingScreen loadingScreen;
	private MainMenuScreen mainMenuScreen;
	private GameScreen gameScreen;

	public FallingBlocks(){}

	@Override
	public void create() {
		this.spriteBatch= new SpriteBatch();
		this.assetManagerWrapper= new AssetManagerWrapper();
		this.inputListenersManager= new InputListenersManager();

		this.loadingScreen= new LoadingScreen(this, assetManagerWrapper);
		this.mainMenuScreen = new MainMenuScreen(this, spriteBatch);
		this.gameScreen = new GameScreen(this, assetManagerWrapper, spriteBatch);
		setScreen(mainMenuScreen);
	}



	@Override
	public void render() {

//		assetManagerWrapper.loadLoadingArrow();
//
//		if(assetManagerWrapper.getAssetManagerUpdate()) {
//            setScreen(loadingScreen);
//        }


		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public SpriteBatch getSpriteBatch(){return spriteBatch;}

	public AssetManagerWrapper getAssetManagerWrapper() {
		return assetManagerWrapper;
	}

	public InputListenersManager getInputListenersManager(){return  inputListenersManager;}

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

