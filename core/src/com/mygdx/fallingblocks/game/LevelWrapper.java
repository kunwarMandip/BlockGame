package com.mygdx.fallingblocks.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
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
import com.mygdx.fallingblocks.entity.player.GesturePlayerController;
import com.mygdx.fallingblocks.utilities.SolidTextureCreator;

import static com.mygdx.fallingblocks.GlobalStaticVariables.*;

public class LevelWrapper{

    public Viewport gameViewport;
    public OrthographicCamera orthographicCamera;
    public Box2DDebugRenderer box2DDebugRenderer;
    public OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

    public World world;
    public TiledMap tiledMap;
    public boolean isLevelCompleted=false;

    public SolidTextureCreator solidTextureCreator = new SolidTextureCreator();

    private FallingBlocks fallingBlocks;
    public LevelWrapper(FallingBlocks fallingBlocks){
        this.fallingBlocks=fallingBlocks;
    }
    public void initCameraAndViewport(){
        if(orthographicCamera == null){
            orthographicCamera= new OrthographicCamera();
        }

        if(gameViewport == null){
            gameViewport = new FitViewport(VIRTUAL_WIDTH/ PPM, VIRTUAL_HEIGHT/PPM, orthographicCamera);
            gameViewport.apply();
            orthographicCamera.position.set(gameViewport.getWorldWidth() / 2f, gameViewport.getWorldHeight() / 2f, 0);
            orthographicCamera.update();
        }
    }

    /**
     * Checks if box2D world is already initialized.
     * If world == null, creates a new Box2D world.
     * Else, removes all Box2D body from World
     */
    public void initWorld(){
        if(world != null){
            resetWorld();
        }

        world= new World(new Vector2(0, 0), true);
        box2DDebugRenderer= new Box2DDebugRenderer();
        box2DDebugRenderer.SHAPE_STATIC.set(0, 0, 0, 1);
        box2DDebugRenderer.setDrawBodies(true);
    }

    /**
     * Initialize a new
     * @param tiledMapPath
     */
    public void initTiledMapAndRenderer(String tiledMapPath){
        tiledMap= new TmxMapLoader().load(tiledMapPath);
        orthogonalTiledMapRenderer= new OrthogonalTiledMapRenderer(tiledMap, 1/PPM);
    }

    public void resize(int width, int height){
        gameViewport.update(width, height);
        gameViewport.apply();
    }

    public void clearScreen(){
        Gdx.gl.glClearColor(0,0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Reset the world to default for new Box2D body instances to be added
     */
    public void resetWorld(){
        if(world==null){return;}

        Array<Body> allWorldBodies= new Array<>();
        world.getBodies(allWorldBodies);
        for(Body body: allWorldBodies){
            world.destroyBody(body);
        }
    }


    public void setProjectionMatrix(SpriteBatch spriteBatch){
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
    }

    public void renderBox2DDebugRenderer(){
        box2DDebugRenderer.render(world, orthographicCamera.combined);
    }



    GestureDetector gestureDetector;
    public void setPlayerBodyController(Body body){
        gestureDetector= new GestureDetector(new GesturePlayerController(body));
        fallingBlocks.getInputListenersManager().addInputProcessor(gestureDetector);
    }

    public void disposeInputProcessor(){
        fallingBlocks.getInputListenersManager().removeInputProcessor(gestureDetector);
    }
}
