package com.mygdx.fallingblocks.game.levels;

import com.badlogic.gdx.Screen;
import com.mygdx.fallingblocks.FallingBlocks;
import com.mygdx.fallingblocks.game.LevelWrapper;

public class Level1 implements Screen {

    private final FallingBlocks fallingBlocks;
    private final LevelWrapper levelWrapper;

    public Level1(FallingBlocks fallingBlocks, LevelWrapper levelWrapper) {
      this.fallingBlocks=fallingBlocks;
      this.levelWrapper=levelWrapper;
    }

    @Override
    public void show() {
        levelWrapper.resetWorld();
        levelWrapper.setCamera();
        levelWrapper.setWorld("map/tiledMap.tmx");
    }

    public void update(float delta){
        if(levelWrapper.isLevelCompleted){
            fallingBlocks.setScreen(fallingBlocks.getMainMenuScreen());
        }
        levelWrapper.world.step(1/60f, 6, 2);
    }

    @Override
    public void render(float delta) {
        update(delta);
        levelWrapper.clearScreen();
        levelWrapper.orthogonalTiledMapRenderer.render();
        levelWrapper.box2DDebugRenderer.render(levelWrapper.world, levelWrapper.orthographicCamera.combined);
    }

    @Override
    public void resize(int width, int height) {
        levelWrapper.resize(width, height);
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
