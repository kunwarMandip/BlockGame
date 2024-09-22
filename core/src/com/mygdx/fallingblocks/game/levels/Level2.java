package com.mygdx.fallingblocks.game.levels;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.fallingblocks.FallingBlocks;
import com.mygdx.fallingblocks.entity.EntityManager;
import com.mygdx.fallingblocks.game.GameVariables;
import com.mygdx.fallingblocks.game.LevelWrapper;
import com.mygdx.fallingblocks.map.TiledMapManager;

public class Level2 implements Screen {

    private final SpriteBatch spriteBatch;
    private final LevelWrapper levelWrapper;
    private final FallingBlocks fallingBlocks;

    private EntityManager entityManager;
    private GameVariables gameVariables;

    public Level2(FallingBlocks fallingBlocks, LevelWrapper levelWrapper) {
        this.levelWrapper=levelWrapper;
        this.fallingBlocks=fallingBlocks;
        this.spriteBatch=fallingBlocks.getSpriteBatch();
    }

    @Override
    public void show() {
        this.gameVariables= new GameVariables();;

        levelWrapper.initCameraAndViewport();
        levelWrapper.initWorld();
        levelWrapper.initTiledMapAndRenderer("map/tiledMap.tmx");
        new TiledMapManager(levelWrapper.world, fallingBlocks.levelWrapper.tiledMap);
        entityManager= new EntityManager(levelWrapper.world);
    }

    public void update(float delta){
        if(levelWrapper.isLevelCompleted){
            fallingBlocks.setScreen(fallingBlocks.getMainMenuScreen());
        }

        entityManager.update(delta);
        levelWrapper.world.step(1/60f, 6, 2);
    }

    @Override
    public void render(float delta) {
        update(delta);
        levelWrapper.clearScreen();

        levelWrapper.orthographicCamera.update();
        levelWrapper.orthogonalTiledMapRenderer.setView(levelWrapper.orthographicCamera);
        levelWrapper.orthogonalTiledMapRenderer.render(new int[]{0});
        levelWrapper.renderBox2DDebugRenderer();

        levelWrapper.setProjectionMatrix(spriteBatch);
        spriteBatch.begin();
        entityManager.drawEntities(spriteBatch);
        spriteBatch.end();

        levelWrapper.orthogonalTiledMapRenderer.render(new int[]{1});
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
