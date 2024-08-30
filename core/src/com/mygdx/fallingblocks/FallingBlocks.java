package com.mygdx.fallingblocks;

import com.badlogic.gdx.Game;
import com.mygdx.fallingblocks.screens.*;
import com.mygdx.fallingblocks.utilities.AssetManagerWrapper;

public class FallingBlocks extends Game {

	private GameScreen gameScreen;
	private GameMenuScreen gameMenuScreen;

	@Override
	public void create() {
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}

