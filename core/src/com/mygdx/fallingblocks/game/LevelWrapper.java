package com.mygdx.fallingblocks.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.fallingblocks.FallingBlocks;

import static com.mygdx.fallingblocks.GlobalStaticVariables.*;

/**
 *
 */
public class LevelWrapper {

    public Viewport gameViewport;
    public OrthographicCamera orthographicCamera;
    public Box2DDebugRenderer box2DDebugRenderer;
    public OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

    public World world;
    public TiledMap tiledMap;

    public boolean isLevelCompleted=false;

    /**
     * Set orthographicCamera and Viewport with DEFAULT Global values
     */
    public void setCamera(){
        orthographicCamera = new OrthographicCamera();
        gameViewport = new FitViewport(VIRTUAL_WIDTH/ PPM, VIRTUAL_HEIGHT/PPM, orthographicCamera);
        gameViewport.apply();
        orthographicCamera.position.set(gameViewport.getWorldWidth() / 2f, gameViewport.getWorldHeight() / 2f, 0);
        orthographicCamera.update();
    }

    /**
     * Change default params for Orthographic Camera and Viewport
     * @param worldSize height and width of the viewport
     * @param ppm pixel per meter for the camera
     */
    public void changeCamera(Vector2 worldSize, float ppm){
        gameViewport.update((int)worldSize.x, (int) worldSize.y);
        gameViewport.apply();
        orthographicCamera.position.set(gameViewport.getWorldWidth() / 2f, gameViewport.getWorldHeight() / 2f, 0);
        orthographicCamera.update();
    }

    /**
     * Init World, Box2dDebugRenderer, TiledMap and OrthogonalTiledMapRenderer
     * @param tiledMapPath path references to the tiledMap to be rendered
     */
    public void setWorld(String tiledMapPath){
        world= new World(new Vector2(0, 0), true);
        box2DDebugRenderer= new Box2DDebugRenderer();
        box2DDebugRenderer.SHAPE_STATIC.set(0, 0, 0, 1);
        box2DDebugRenderer.setDrawBodies(true);

        tiledMap= new TmxMapLoader().load(tiledMapPath);
        orthogonalTiledMapRenderer= new OrthogonalTiledMapRenderer(tiledMap, 1/PPM);
    }


    /**
     * Reset the screen to display nothing
     */
    public void clearScreen(){
        Gdx.gl.glClearColor(0,0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void addBody(Body body){
        world.destroyBody(body);
    }

    /**
     * Remove all Box2D bodies from the world
     */
    public void resetWorld(){
        if(world==null){return;}

        Array<Body> allWorldBodies= new Array<>();
        world.getBodies(allWorldBodies);
        for(Body body: allWorldBodies){
            world.destroyBody(body);
        }
    }



    public void resize(int width, int height){
        gameViewport.update(width, height);
        gameViewport.apply();
    }

}
