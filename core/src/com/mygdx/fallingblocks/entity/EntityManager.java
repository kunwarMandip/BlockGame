package com.mygdx.fallingblocks.entity;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.fallingblocks.game.EndlessGameScreen;
import com.mygdx.fallingblocks.utilities.AssetManagerWrapper;
import com.mygdx.fallingblocks.utilities.GameStateVariables;
import com.mygdx.fallingblocks.contactlistener.GameContactListener;
import com.mygdx.fallingblocks.entity.enemies.EnemyManager;
import com.mygdx.fallingblocks.entity.player.Player;
import com.mygdx.fallingblocks.utilities.DynamicTextureCreator;

/**
 * Manage all dynamic box2D entities. Player, Enemies
 */
public class EntityManager {

    private final Player player;
    private final EnemyManager enemyManager;
    private final AssetManagerWrapper assetManagerWrapper;
    public DynamicTextureCreator solidColorCreator;

    public EntityManager(EndlessGameScreen endlessGameScreen){
        World world= endlessGameScreen.getWorld();
        TiledMap tiledMap= endlessGameScreen.getTiledMap();
        GameStateVariables gameStateVariables= endlessGameScreen.getGameStateVariables();
        RayHandler rayHandler= endlessGameScreen.getRayHandler();

        this.solidColorCreator= new DynamicTextureCreator();
        this.assetManagerWrapper= endlessGameScreen.getAssetManagerWrapper();
        this.player= new Player(endlessGameScreen.getWorld(), gameStateVariables, solidColorCreator, rayHandler, endlessGameScreen.getInputListenersManager());
        this.enemyManager= new EnemyManager(world, tiledMap, gameStateVariables, solidColorCreator);

        GameContactListener gameContactListener= new GameContactListener(this, gameStateVariables);
        world.setContactListener(gameContactListener);
    }

    public void update(float delta){
        Vector2 playerPosition=player.getBody().getPosition();
        player.update(solidColorCreator.getPlayerColorID());
        enemyManager.update(delta, playerPosition);
    }

    /**
     * Calls to draw every entity such as Players and Enemies
     * Enemy has to be drawn first due to the EnemySpawnDirection.Class
     * @param spriteBatch used to draw sprites faster and efficiently
     */
    public void drawEntities(SpriteBatch spriteBatch){
        enemyManager.draw(spriteBatch);
        player.draw(spriteBatch);
    }

    public Player getPlayer() {
        return player;
    }

}
