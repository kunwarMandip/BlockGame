package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.MainDisplay;

public class FallingBlocks extends Game {

	//9:18 ratio to ensure it fits on every device
	//Since game is PORTRAIT mode only, the height is longer than width
	public static final float VIRTUAL_WIDTH = 720;
	public static final float VIRTUAL_HEIGHT = 1440;
	//see description in Readme.md
	public static final float PPM=100;


	public static int score=0;

	@Override
	public void create() {
		setScreen(new MainDisplay(this));
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

