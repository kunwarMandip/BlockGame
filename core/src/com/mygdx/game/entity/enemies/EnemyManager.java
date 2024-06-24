package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Responsible for drawing and updating all enemies objects
 * in the world
 */
public class EnemyManager {

    private World world;

    /**
     * When new enemy is created, they are to be added to this list ASAP!!!
     * Allows for easier management of enemies
     */
    private Array<Enemy> enemies;


    public EnemyManager(World world){
        this.world= world;
        enemies= new Array<>();
        initEnemy();
    }

    private void initEnemy(){
        enemies.add(new Enemy(world, new Vector2(100, 100), new Vector2(1, 2)));
    }

    /**
     * Draws all enemies currently spawned in the screen to the ground
     * @param spriteBatch faster way to draw sprites
     */
    public void draw(SpriteBatch spriteBatch){
        if(enemies.isEmpty()){return;}

        update();
        for(Enemy enemy: enemies){
            enemy.draw(spriteBatch);
        }
    }


    /**
     * Updates all entities before drawing them to show them moving
     * To be called only locally
     */
    private void update(){
        for(Enemy enemy: enemies){
            enemy.update();
        }
    }
}
