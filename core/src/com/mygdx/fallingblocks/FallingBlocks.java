package com.mygdx.fallingblocks;

import com.badlogic.gdx.Game;
import com.mygdx.fallingblocks.screens.MainDisplay;

public class FallingBlocks extends Game {



	@Override
	public void create() {
//		setScreen(new MainMenuScreen(spriteBatch));
		setScreen(new MainDisplay());
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

