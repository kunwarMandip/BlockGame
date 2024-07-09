package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.FallingBlocks;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.map.MapManager;

import static com.mygdx.game.GlobalVariables.*;

/**
 * This class is the main class which holds
 * everything aka the view the user will see.
 */
public class MainDisplay implements Screen {

    //Need this to be public to manage how much the playerMoves
    public OrthographicCamera gameCamera;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

    /**
     * Allows to retain aspect ratio on different sized screens
     * Fit viewport for the game
     * TODO fix the black corners using other viewport
     */
    private Viewport viewport;

    /**
     * Used to render box2D objects for developers
     * shows box2D objects borders.
     * Should not be used when game is finished
     */
    private Box2DDebugRenderer box2DDebugRenderer;

    private World world;

    private TiledMap tiledMap;
//    private MapLoader mapLoader;
    private MapManager mapManager;

    private final SpriteBatch spriteBatch;
    private final EntityManager entityManager;

    /**
     * Set the aspect ratio for the screen
     * create the world and load the tiledMap.
     * Set the entityManager to create and updateEntities
     */

    public MainDisplay(){
        spriteBatch= new SpriteBatch();
        setAspectRatio();
        createWorld();

        entityManager = new EntityManager(world, gameCamera, mapManager.getSpawnAreaList());
    }


    /**
     * Sets the screen to allow the game to retain aspect ratio
     * on various sizes of screens
     */
    private void setAspectRatio(){
        gameCamera= new OrthographicCamera();
        viewport= new FitViewport(VIRTUAL_WIDTH/ PPM, VIRTUAL_HEIGHT/PPM, gameCamera);
        viewport.apply();
        gameCamera.position.set(viewport.getWorldWidth() / 2f, viewport.getWorldHeight() / 2f, 0);
        gameCamera.update();
    }

    /**
     * Creates a world, sets the renderer and tiledMap.
     * Tells the renderer to render the tileMap
     */
    private void createWorld(){
        world= new World(new Vector2(0f, 0f), true);

        //Init Box2DDebugRenderer and add no colors to objects
        // "set(0, 0, 0, 1)" : sets opacity to 100% for objects
        box2DDebugRenderer= new Box2DDebugRenderer();
        box2DDebugRenderer.SHAPE_STATIC.set(0, 0, 0, 1);
        box2DDebugRenderer.setDrawBodies(true);

        //load the very first TileMap into orthogonalTiledMapRenderer renderer
        tiledMap = new TmxMapLoader().load("map9.tmx");
        orthogonalTiledMapRenderer= new OrthogonalTiledMapRenderer(tiledMap, 1/PPM);

        mapManager = new MapManager(world, tiledMap, gameCamera);

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
        world.step(1/60f, 6, 2);
        entityManager.update(delta);

    }

    @Override
    public void render(float delta) {

        update(delta);

        //Clear the screen
        Gdx.gl.glClearColor(0,0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Change the camera
        gameCamera.update();

        //need to change this to allow dynamic tier loading
        orthogonalTiledMapRenderer.setView(gameCamera);
        orthogonalTiledMapRenderer.render(mapManager.getLowerTiles());
        box2DDebugRenderer.render(world, gameCamera.combined);
        mapManager.update();

        //Render the spriteBatch
        spriteBatch.setProjectionMatrix(gameCamera.combined);
        spriteBatch.begin();
        entityManager.drawEntities(spriteBatch);
        spriteBatch.end();


        //last layer ensures that enemies not inside place to be shown aren't shown
        orthogonalTiledMapRenderer.render(mapManager.getUpperTiles());
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return tiledMap;
    }
}
