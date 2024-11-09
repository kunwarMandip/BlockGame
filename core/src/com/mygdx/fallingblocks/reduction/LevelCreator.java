package com.mygdx.fallingblocks.reduction;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.mygdx.fallingblocks.GlobalStaticVariables.*;
import static com.mygdx.fallingblocks.GlobalStaticVariables.PPM;

public class LevelCreator implements Screen {

    public Viewport gameViewport;
    public OrthographicCamera orthographicCamera;
    public Box2DDebugRenderer box2DDebugRenderer;
    public OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

    public World world;
    public TiledMap tiledMap;

    private LevelDto levelDto;
    public LevelCreator(LevelDto levelDto){
        this.levelDto= levelDto;
    }

    @Override
    public void show() {
        orthographicCamera= new OrthographicCamera();
        gameViewport = new FitViewport(VIRTUAL_WIDTH/ PPM, VIRTUAL_HEIGHT/PPM, orthographicCamera);
        gameViewport.apply();
        orthographicCamera.position.set(gameViewport.getWorldWidth() / 2f, gameViewport.getWorldHeight() / 2f, 0);
        orthographicCamera.update();

        world= new World(new Vector2(0, 0), true);
        box2DDebugRenderer= new Box2DDebugRenderer();
        box2DDebugRenderer.SHAPE_STATIC.set(0, 0, 0, 1);
        box2DDebugRenderer.setDrawBodies(true);

        tiledMap= new TmxMapLoader().load("tiledMap.txt");
        orthogonalTiledMapRenderer= new OrthogonalTiledMapRenderer(tiledMap, 1/PPM);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
        gameViewport.apply();
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
