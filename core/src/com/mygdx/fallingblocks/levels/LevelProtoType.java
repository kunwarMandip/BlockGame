package com.mygdx.fallingblocks.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.fallingblocks.FallingBlocks;
import com.mygdx.fallingblocks.input.InputListenersManager;
import com.mygdx.fallingblocks.map.TiledMapManager;

import static com.mygdx.fallingblocks.GlobalStaticVariables.*;
import static com.mygdx.fallingblocks.GlobalStaticVariables.PPM;


public abstract class LevelProtoType {

    protected Viewport gameViewport;
    protected OrthographicCamera orthographicCamera;
    protected OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

    protected World world;
    protected TiledMap tiledMap;
    protected Box2DDebugRenderer box2DDebugRenderer;

    protected FallingBlocks fallingBlocks;
    protected SpriteBatch spriteBatch;
    protected InputListenersManager inputListenersManager;

    public LevelProtoType(FallingBlocks fallingBlocks){
        this.fallingBlocks= fallingBlocks;
        this.spriteBatch= fallingBlocks.getSpriteBatch();
        this.inputListenersManager= fallingBlocks.getInputListenersManager();
    }


    public void setWorld(String mapPath){
        //init Camera
        orthographicCamera= new OrthographicCamera();
        gameViewport= new FitViewport(VIRTUAL_WIDTH/ PPM, VIRTUAL_HEIGHT/PPM, orthographicCamera);
        gameViewport.apply();
        orthographicCamera.position.set(gameViewport.getWorldWidth() / 2f, gameViewport.getWorldHeight() / 2f, 0);
        orthographicCamera.update();

        //init World
        world= new World(new Vector2(0, 0), true);
        box2DDebugRenderer= new Box2DDebugRenderer();
        box2DDebugRenderer.SHAPE_STATIC.set(0, 0, 0, 1);
        box2DDebugRenderer.setDrawBodies(true);

        //TiledMap Object render
        tiledMap= new TmxMapLoader().load(mapPath);
        orthogonalTiledMapRenderer= new OrthogonalTiledMapRenderer(tiledMap, 1/PPM);
        TiledMapManager tiledMapManager= new TiledMapManager(world, tiledMap);
    }

    public void clearScreen(){
        Gdx.gl.glClearColor(0,0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

}
