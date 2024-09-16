package com.mygdx.fallingblocks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.fallingblocks.game.EndlessGameScreen;
import com.mygdx.fallingblocks.game.LevelWrapper;
import com.mygdx.fallingblocks.screens.*;
import com.mygdx.fallingblocks.utilities.AssetManagerWrapper;
import com.mygdx.fallingblocks.utilities.InputListenersManager;


public class FallingBlocks extends Game {

	private SpriteBatch spriteBatch;
	private AssetManagerWrapper assetManagerWrapper;
	private InputListenersManager inputListenersManager;

	public LevelWrapper levelWrapper;
	public LevelChooserScreen levelChooserScreen;

	private LoadingScreen loadingScreen;
	private MainMenuScreen mainMenuScreen;
	private EndlessGameScreen endlessGameScreen;

	public FallingBlocks(){}

	@Override
	public void create() {
		this.spriteBatch= new SpriteBatch();
		this.assetManagerWrapper= new AssetManagerWrapper();
		this.inputListenersManager= new InputListenersManager();

		this.levelWrapper= new LevelWrapper();
		this.levelChooserScreen = new LevelChooserScreen(this, spriteBatch);

		this.loadingScreen= new LoadingScreen(this, assetManagerWrapper);
		this.mainMenuScreen = new MainMenuScreen(this, spriteBatch);
		this.endlessGameScreen = new EndlessGameScreen(this, assetManagerWrapper, spriteBatch);
		setScreen(mainMenuScreen);
	}



	@Override
	public void render() {
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

	public InputListenersManager getInputListenersManager(){return inputListenersManager;}

	public LoadingScreen getLoadingScreen() {
		return loadingScreen;
	}

	public MainMenuScreen getMainMenuScreen() {
		return mainMenuScreen;
	}

	public EndlessGameScreen getGameScreen() {
		return endlessGameScreen;
	}

	public LevelChooserScreen getLevelChooserScreen(){return levelChooserScreen;}
}

