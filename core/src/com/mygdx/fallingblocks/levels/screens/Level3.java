package com.mygdx.fallingblocks.levels.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.fallingblocks.FallingBlocks;
import com.mygdx.fallingblocks.contactlistener.handler.EnemyOuterBoundCollisionInterface;
import com.mygdx.fallingblocks.contactlistener.handler.PlayerEnemyCollisionInterface;
import com.mygdx.fallingblocks.entity.NewEntityManager;
import com.mygdx.fallingblocks.levels.LevelProtoType;

public class Level3 extends LevelProtoType implements Screen {

    private NewEntityManager newEntityManager;

    public Level3(FallingBlocks fallingBlocks) {
        super(fallingBlocks);
    }

    @Override
    public void show() {
        System.out.println("Level3");
        String tiledMapPath= "map/tiledMap.tmx";
        setWorld(tiledMapPath);

        PlayerEnemyCollisionInterface playerEnemyCollisionInterface= new PlayerEnemyCollisionInterface() {};
        EnemyOuterBoundCollisionInterface enemyOuterBoundCollisionInterface= new EnemyOuterBoundCollisionInterface(){};
        newEntityManager= new NewEntityManager(world, playerEnemyCollisionInterface, enemyOuterBoundCollisionInterface);

        newEntityManager.getNewPlayer().setController(fallingBlocks.getInputListenersManager());
    }

    private void update(float delta){
//        newEntityManager.Updateable(1, new Vector2(10f, 10f));
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
        newEntityManager.draw(spriteBatch);
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
