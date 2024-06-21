package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entity.enemies.Enemy;
import com.mygdx.game.entity.player.Player;

/**
 * Responsible for managing all entities that are supposed to be loaded
 * in the current Map including Players, enemies, objects i.e. Bullets
 */
public class EntityManager {

    private final World world;
    private Player player;

    public EntityManager(World world){
        this.world= world;
        initPlayer();
    }

    /**
     * Init the player so it can be accessed by the classes
     * that references this class/ class-instance
     */
    private void initPlayer(){
        player= new Player(world, new Vector2(100, 100));
    }

    public Player getPlayer(){
        return player;
    }


    /**
     * Calls to draw every entity such as Players and Enemies
     * @param spriteBatch used to draw sprites faster and efficiently
     */
    public void drawEntities(SpriteBatch spriteBatch){
        player.draw(spriteBatch);
        //TODO add enemy draw
    }

}
