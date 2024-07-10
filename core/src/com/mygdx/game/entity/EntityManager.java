package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    public EntityManager(World world, OrthographicCamera gameCamera, Array<EnemySpawnArea> spawnAreas){
        player= new Player(world, gameCamera);
        enemyManager= new EnemyManager(world, spawnAreas, this, player.getBody().getPosition());
        GameContactListener gameContactListener = new GameContactListener(world, this);
        world.setContactListener(gameContactListener);
    }

    /**
     * Update Entities
     */
    public void update(float delta){
        enemyManager.update(delta);
        enemyManager.getEnemyGenerator().create(player.getBody().getPosition());
    }

    /**
     * Calls to draw every entity such as Players and Enemies
     * @param spriteBatch used to draw sprites faster and efficiently
     */
    public void drawEntities(SpriteBatch spriteBatch){
        player.draw(spriteBatch);
        enemyManager.draw(spriteBatch);
    }

    public Player getPlayer() {
        return player;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }
}
