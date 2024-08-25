package com.mygdx.fallingblocks;

import com.badlogic.gdx.Game;
import com.mygdx.fallingblocks.screens.GameScreen;
import com.mygdx.fallingblocks.screens.MainMenuScreen;

public class FallingBlocks extends Game {

	@Override
	public void create() {
		setScreen(new MainMenuScreen(this));
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

