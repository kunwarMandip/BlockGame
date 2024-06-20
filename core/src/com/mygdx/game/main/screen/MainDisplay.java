package com.mygdx.game.main.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.map.MapLoader;

import com.mygdx.game.entity.player.Player;

/**
 * This class is the main class which holds
 * everything aka the view the user will see.
 */
public class MainDisplay implements Screen {

    //These are normal viewport ratio for mobile devices
    public static final float VIRTUAL_WIDTH = 800;
    public static final float VIRTUAL_HEIGHT = 480;

    private OrthographicCamera gameCamera;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

    /**
     * Used to render box2D objects for developers
     * shows box2D objects borders.
     * Should not be used when game is finished
     */
    private Box2DDebugRenderer box2DDebugRenderer;

    /**
     * Allows to retain aspect ratio on different sized screens
     */
    private Viewport viewport;

    private World world;

    private TiledMap tiledMap;
    private MapLoader mapLoader;

    private SpriteBatch spriteBatch;

    private EntityManager entityManager;

    private Player player;

    public MainDisplay(){

        setAspectRatio();
        createWorld();
    }


    /**
     * Sets the screen to allow the game to retain aspect ratio
     * on various sizes of screens
     */
    private void setAspectRatio(){
        gameCamera= new OrthographicCamera();
        viewport= new ExtendViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, gameCamera);
        viewport.apply();
        gameCamera.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
    }

    /**
     *Creates a world, sets the renderer and tiledMap
     * tells the renderer to render teh tileMap
     */
    private void createWorld(){

        world= new World(new Vector2(0, -9.81f), true);

        //Init Box2DDebugRenderer and add no colors to objects
        // "set(0, 0, 0, 1)" : sets opacity to 100% for objects
        box2DDebugRenderer= new Box2DDebugRenderer();
        box2DDebugRenderer.SHAPE_STATIC.set(0, 0, 0, 1);
        box2DDebugRenderer.setDrawBodies(true);

        //load the very first TileMap into orthogonalTiledMapRenderer renderer
        tiledMap = new TmxMapLoader().load("myMap.tmx");
        orthogonalTiledMapRenderer= new OrthogonalTiledMapRenderer(tiledMap);

        mapLoader = new MapLoader();
        mapLoader.mapWorld(world, tiledMap);

    }

    @Override
    public void show() {

    }

    /**
     * Updates all entities inside world
     * @param delta time in seconds since last render
     */
    public void update(float delta){

        // 1/60f: 60 frames per second
        // Rest i found on youtube like it
        world.step(1/60f, 6, 2);
        gameCamera.update();
    }

    @Override
    public void render(float delta) {

        update(delta);

        //Clear the screen
        Gdx.gl.glClearColor(0,0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthogonalTiledMapRenderer.setView(gameCamera);
        orthogonalTiledMapRenderer.render();
        box2DDebugRenderer.render(world, gameCamera.combined);



        //Render the spriteBatch
//        spriteBatch.begin();
//        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        // true enables centering of the camera
        viewport.update(width, height, true);
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
        tiledMap.dispose();
        orthogonalTiledMapRenderer.dispose();
        world.dispose();
    }
}
