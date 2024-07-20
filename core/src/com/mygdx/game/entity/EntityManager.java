package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.contactlistener.GameContactListener;
import com.mygdx.game.entity.enemies.EnemyManager;
import com.mygdx.game.entity.player.Player;
import com.mygdx.game.map.objects.EnemySpawnArea;

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
     * @param gameCamera camera that shows the viewport
     */
    public EntityManager(World world, TiledMap tiledMap, OrthographicCamera gameCamera){
        player= new Player(world, gameCamera);
        enemyManager= new EnemyManager(world, tiledMap, this);
        GameContactListener gameContactListener = new GameContactListener(this);
        world.setContactListener(gameContactListener);
    }

    /**
     * Update Entities
     */
    public void update(float delta){
        enemyManager.update(delta, player.getBody().getPosition());
    }

    /**
     * Calls to draw every entity such as Players and Enemies
     * Enemy has to be drawn first due to the EnemySpawnDirection.class
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
