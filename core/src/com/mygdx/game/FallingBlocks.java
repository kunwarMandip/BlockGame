package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.MainDisplay;

public class FallingBlocks extends Game {

	public static int score=0;

	@Override
	public void create() {
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

