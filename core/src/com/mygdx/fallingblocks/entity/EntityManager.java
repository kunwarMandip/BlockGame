package com.mygdx.fallingblocks.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.fallingblocks.GameStateVariables;
import com.mygdx.fallingblocks.contactlistener.GameContactListener;
import com.mygdx.fallingblocks.entity.enemies.EnemyManager;
import com.mygdx.fallingblocks.entity.player.Player;

/**
 * Responsible for managing all entities that are supposed to be loaded
 * in the current Map including Players, enemies, objects i.e. Bullets
 */
public class EntityManager {

    private final Player player;
    private final EnemyManager enemyManager;
    /**
     * Sets the player, enemies, and the contact listener
     * @param world box2D world to deploy body in
     */
    public EntityManager(World world, TiledMap tiledMap, GameStateVariables gameStateVariables){
        player= new Player(world);
        enemyManager= new EnemyManager(world, tiledMap, gameStateVariables);
        GameContactListener gameContactListener = new GameContactListener(this, gameStateVariables);
        world.setContactListener(gameContactListener);
    }

    /**
     * Update Entities
     */
    public void update(float delta){
        enemyManager.update(delta, player.getBody().getPosition(), player.getPlayerTextureName());
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

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }
}
