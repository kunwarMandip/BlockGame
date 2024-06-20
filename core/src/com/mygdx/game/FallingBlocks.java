package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.main.screen.MainDisplay;

public class FallingBlocks extends Game {


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

