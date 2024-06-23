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

    private final World world;
    private OrthographicCamera gameCamera;

    private Player player;
    private EnemyManager enemyManager;

    public EntityManager(World world, OrthographicCamera gameCamera){
        this.world= world;
        this.gameCamera=gameCamera;
        init();

    }

    /**
     * Init objects/classes
     */
    private void init(){
        //X == height of body, Y== width of body
        player= new Player(world, new Vector2(100, 100), gameCamera);
        enemyManager= new EnemyManager();
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
