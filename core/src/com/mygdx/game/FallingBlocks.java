package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.MainDisplay;
import com.mygdx.game.screens.MainMenuScreen;

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

