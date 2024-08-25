package com.mygdx.fallingblocks.screens;

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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.fallingblocks.FallingBlocks;
import com.mygdx.fallingblocks.GameStateVariables;
import com.mygdx.fallingblocks.entity.EntityManager;
import com.mygdx.fallingblocks.map.MapManager;

import static com.mygdx.fallingblocks.GlobalStaticVariables.*;

/**
 * This class is the main class which holds
 * everything aka the view the user will see.
 */
public class GameScreen implements Screen {

    //Need this to be public to manage how much the playerMoves
    public FallingBlocks fallingBlocks;

    private GameStateVariables gameStateVariables;
    private OrthographicCamera gameCamera;
    private Viewport viewport;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private SpriteBatch spriteBatch;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private TiledMap tiledMap;
    private MapManager mapManager;
    private Hud hudScene;
    private EntityManager entityManager;

    public GameScreen(FallingBlocks fallingBlocks){
        this.fallingBlocks= fallingBlocks;
    }

    /**
     * Set Aspect ratio, Set world, TiledMap, EntityManager, hudScene
     */
    @Override
    public void show() {
        this.spriteBatch= new SpriteBatch();
        this.gameStateVariables= new GameStateVariables();
        setCamera();
        setWorld();

        entityManager = new EntityManager(world, tiledMap, gameStateVariables);
        hudScene= new Hud(gameStateVariables, spriteBatch, tiledMap);
    }

    /**
     * Sets the screen to allow the game to retain aspect ratio on various sizes of screens
     */
    private void setCamera(){
        gameCamera= new OrthographicCamera();
        viewport= new FitViewport(VIRTUAL_WIDTH/ PPM, VIRTUAL_HEIGHT/PPM, gameCamera);
        viewport.apply();
        gameCamera.position.set(viewport.getWorldWidth() / 2f, viewport.getWorldHeight() / 2f, 0);
        gameCamera.update();
    }

    /**
     * Creates world, init renderer and tiledMap
     */
    private void setWorld(){
        world= new World(new Vector2(0f, 0f), true);

        //Init Box2DDebugRenderer and add no colors to objects
        //Set(0, 0, 0, 1) : Sets opacity to 100% for Objects/Box2D body
        box2DDebugRenderer= new Box2DDebugRenderer();
        box2DDebugRenderer.SHAPE_STATIC.set(0, 0, 0, 1);
        box2DDebugRenderer.setDrawBodies(true);

        //load the very first TileMap into orthogonalTiledMapRenderer renderer
        tiledMap = new TmxMapLoader().load("map/map1.tmx");
        orthogonalTiledMapRenderer= new OrthogonalTiledMapRenderer(tiledMap, 1/PPM);
        mapManager = new MapManager(world, tiledMap);
    }


    /**
     * Updates all entities inside world
     * @param delta time in seconds since last render
     */
    public void update(float delta){
        world.step(1/60f, 6, 2);
        entityManager.update(delta);
        hudScene.update();
    }

    @Override
    public void render(float delta) {
        update(delta);

        //Clear the screen
        Gdx.gl.glClearColor(0,0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //need to change this to allow dynamic tier loading
        orthogonalTiledMapRenderer.setView(gameCamera);
        orthogonalTiledMapRenderer.render(mapManager.getLowerTiles());
        box2DDebugRenderer.render(world, gameCamera.combined);
        mapManager.update(gameStateVariables.getScore(), gameStateVariables.getLastScore());

        //Render the spriteBatch
        spriteBatch.setProjectionMatrix(gameCamera.combined);
        spriteBatch.begin();
        entityManager.drawEntities(spriteBatch);

        //Set our batch to now draw what the Hud camera sees.
        spriteBatch.end();
        //last layer ensures that enemies not inside place to be shown aren't shown
        orthogonalTiledMapRenderer.render(mapManager.getUpperTiles());

        spriteBatch.setProjectionMatrix(hudScene.getStage().getCamera().combined);
        hudScene.getStage().draw();
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
