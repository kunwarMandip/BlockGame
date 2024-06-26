package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.contactlistener.GameContactListener;
import com.mygdx.game.entity.enemies.EnemyManager;
import com.mygdx.game.entity.player.Player;

/**
 * Responsible for managing all entities that are supposed to be loaded
 * in the current Map including Players, enemies, objects i.e. Bullets
 */
public class EntityManager {

    private Player player;
    private EnemyManager enemyManager;
    private GameContactListener gameContactListener;

    /**
     * Sets the player, enemies, and the contact listener
     * @param world box2D world to deploy body in
     * @param gameCamera camera that shows the viewport
     */
    public EntityManager(World world, OrthographicCamera gameCamera){
        player= new Player(world, gameCamera);
        enemyManager= new EnemyManager(world);
        gameContactListener = new GameContactListener(world, enemyManager.getEnemiesToRemove());
        world.setContactListener(gameContactListener);

        float topBoundary = gameCamera.position.y + gameCamera.viewportHeight / 2;
        float widthSize= gameCamera.position.x + gameCamera.viewportWidth /2;

        System.out.println("Top Boundary " + topBoundary);
        System.out.println("Width Size " + widthSize);
    }

    /**
     * Update Entities
     */
    public void update(){
        enemyManager.update();
        enemyManager.removeEnemies();
    }

    /**
     * Calls to draw every entity such as Players and Enemies
     * @param spriteBatch used to draw sprites faster and efficiently
     */
    public void drawEntities(SpriteBatch spriteBatch){
        player.draw(spriteBatch);
        enemyManager.draw(spriteBatch);
    }

}
