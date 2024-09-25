package com.mygdx.fallingblocks.game.levels;

import com.badlogic.gdx.Screen;
import com.mygdx.fallingblocks.FallingBlocks;
import com.mygdx.fallingblocks.contactlistener.handler.EnemyOuterBoundCollisionInterface;
import com.mygdx.fallingblocks.contactlistener.handler.PlayerEnemyCollisionInterface;
import com.mygdx.fallingblocks.entity.NewEntityManager;

public class Level3 extends LevelProtoType implements Screen {

    private NewEntityManager newEntityManager;
    public Level3(FallingBlocks fallingBlocks) {
        super(fallingBlocks);
    }

    @Override
    public void show() {
        String tiledMapPath= "map/tiledMap.tmx";
        setWorld(tiledMapPath);

        PlayerEnemyCollisionInterface playerEnemyCollisionInterface= new PlayerEnemyCollisionInterface() {};
        EnemyOuterBoundCollisionInterface enemyOuterBoundCollisionInterface= new EnemyOuterBoundCollisionInterface(){};
        newEntityManager= new NewEntityManager(world, playerEnemyCollisionInterface, enemyOuterBoundCollisionInterface);
    }

    private void update(float delta){
//        newEntityManager.update();
        world.step(1/60f, 6, 2);
    }

    @Override
    public void render(float delta) {
        update(delta);

        clearScreen();

        orthogonalTiledMapRenderer.setView(orthographicCamera);
        orthogonalTiledMapRenderer.render( new int[]{0});
        box2DDebugRenderer.render(world, orthographicCamera.combined);

        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
        spriteBatch.begin();
        spriteBatch.end();

        orthogonalTiledMapRenderer.render( new int[]{1});

    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
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
