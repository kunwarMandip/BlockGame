package com.mygdx.fallingblocks.screens;

import box2dLight.RayHandler;
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
import com.mygdx.fallingblocks.map.TiledMapManager;
import com.mygdx.fallingblocks.utilities.AssetManagerWrapper;
import com.mygdx.fallingblocks.utilities.GameStateVariables;
import com.mygdx.fallingblocks.entity.EntityManager;
import com.mygdx.fallingblocks.utilities.InputListenersManager;

import static com.mygdx.fallingblocks.GlobalStaticVariables.*;

/**
 * This class is the main class which holds
 * everything aka the view the user will see.
 */
public class GameScreen implements Screen {

    private World world;
    private Viewport gameViewport;
    private RayHandler rayHandler;
    private SpriteBatch spriteBatch;
    private OrthographicCamera gameCamera;
    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

    private EntityManager entityManager;
    private GameStateVariables gameStateVariables;
    private HudOverlayScreen gameHud;

    private InputListenersManager inputListenersManager;

    private TiledMap tiledMap;
    private TiledMapManager tiledMapManager;

    private final FallingBlocks fallingBlocks;
    private final AssetManagerWrapper assetManagerWrapper;

    public GameScreen(FallingBlocks fallingBlocks, AssetManagerWrapper assetManagerWrapper){
        this.fallingBlocks= fallingBlocks;
        this.assetManagerWrapper=assetManagerWrapper;
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
        inputListenersManager= new InputListenersManager();
        entityManager= new EntityManager(this);

        gameHud = new HudOverlayScreen(spriteBatch, gameStateVariables);
        inputListenersManager.addInputListener(gameHud.getStage());
    }

    /**
     * Sets the screen to allow the game to retain aspect ratio on various sizes of screens
     */
    private void setCamera(){
        gameCamera= new OrthographicCamera();
        gameViewport = new FitViewport(VIRTUAL_WIDTH/ PPM, VIRTUAL_HEIGHT/PPM, gameCamera);
        gameViewport.apply();
        gameCamera.position.set(gameViewport.getWorldWidth() / 2f, gameViewport.getWorldHeight() / 2f, 0);
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

        RayHandler.useDiffuseLight(true); // Enables realistic light scattering
        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0.2f); // Sets the ambient light level
        rayHandler.setBlurNum(3); // Amount of blur for soft shadows

        //load the very first TileMap into orthogonalTiledMapRenderer renderer
        tiledMap= new TmxMapLoader().load("map/tiledMap.tmx");
        orthogonalTiledMapRenderer= new OrthogonalTiledMapRenderer(tiledMap, 1/PPM);
        tiledMapManager= new TiledMapManager(world, tiledMap);
    }



    /**
     * Updates all entities inside world
     * @param delta time in seconds since last render
     */
    public void update(float delta){
        if(gameStateVariables.isGamePaused() || gameStateVariables.isPlayerDead()){
            return;
        }

        world.step(1/60f, 6, 2);
        entityManager.update(delta);
        gameHud.update(gameStateVariables.getScore());
//        mapManager.update(gameStateVariables.getScore(), gameStateVariables.getLastScore());
    }

    @Override
    public void render(float delta) {
        update(delta);

        //Clear the screen
        Gdx.gl.glClearColor(0,0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameCamera.update();

        //Render tiled map
        orthogonalTiledMapRenderer.setView(gameCamera);

        //Render lower tiled
        orthogonalTiledMapRenderer.render( new int[]{0});

        //render box2d world
        box2DDebugRenderer.render(world, gameCamera.combined);

        //Render the spriteBatch
        spriteBatch.setProjectionMatrix(gameCamera.combined);
        spriteBatch.begin();
        entityManager.drawEntities(spriteBatch);
        spriteBatch.end();

        //Render Upper Tiled
        orthogonalTiledMapRenderer.render( new int[]{1});

        //draw hud
        gameHud.render();
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
        gameHud.resize(width, height);
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
        gameHud.dispose();
    }

    public World getWorld() {
        return world;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }
    public GameStateVariables getGameStateVariables(){return  gameStateVariables;}
    public RayHandler getRayHandler(){return rayHandler;}
    public AssetManagerWrapper getAssetManagerWrapper(){return assetManagerWrapper;}
    public InputListenersManager getInputListenersManager(){return inputListenersManager;}
}
