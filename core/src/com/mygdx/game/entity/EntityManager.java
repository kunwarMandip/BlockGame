package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entity.enemies.EnemyManager;
import com.mygdx.game.entity.player.Player;

/**
 * Responsible for managing all entities that are supposed to be loaded
 * in the current Map including Players, enemies, objects i.e. Bullets
 */
public class EntityManager {

    private Player player;
    private EnemyManager enemyManager;
    private OrthographicCamera gameCamera;

    private float topBoundry;
    private float widthSize;
    public EntityManager(World world, OrthographicCamera gameCamera){
        this.gameCamera=gameCamera;
        init(world);

        topBoundry = gameCamera.position.y + gameCamera.viewportHeight / 2;
        widthSize= gameCamera.position.x + gameCamera.viewportWidth /2;


        System.out.println("Top Boundary " + topBoundry);
        System.out.println("Width Size " + widthSize);

    }

    /**
     * Init player and enemyManager class
     */
    private void init(World world){
        player= new Player(world, new Vector2(100, 100), gameCamera);
        enemyManager= new EnemyManager(world);
    }

    /**
     * Calls to draw every entity such as Players and Enemies
     * @param spriteBatch used to draw sprites faster and efficiently
     */
    public void drawEntities(SpriteBatch spriteBatch){
        player.draw(spriteBatch);
        enemyManager.draw(spriteBatch);
        //TODO add enemy draw
    }

}
