package com.mygdx.fallingblocks.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.fallingblocks.FallingBlocks;
import com.mygdx.fallingblocks.utilities.AssetManagerWrapper;

public class LoadingScreen implements Screen {

    private final FallingBlocks fallingBlocks;
    private AssetManagerWrapper assetManagerWrapper;

    public LoadingScreen(FallingBlocks fallingBlocks){
        this.fallingBlocks=fallingBlocks;
    }

    @Override
    public void show() {
        assetManagerWrapper= new AssetManagerWrapper();
        assetManagerWrapper.loadAssets();
    }

    @Override
    public void render(float delta) {
        if(assetManagerWrapper.getAssetManager().update()) {
            fallingBlocks.setScreen(new GameScreen(fallingBlocks, assetManagerWrapper));
        }

        float progress = assetManagerWrapper.getAssetManager().getProgress();
        System.out.println("Loading progress: " + (progress * 100) + "% | Delta: " + delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
