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


    //When new enemy is created, they are to be added to this list ASAP!!!
    //Allows for easier management of enemies
    private Array<Enemy> enemies;


    public EnemyManager(World world){
        enemies= new Array<>();
        initEnemy(world);
    }

    private void initEnemy(World world){
        enemies.add( new Enemy(world, new Vector2(100, 100), 5F));

    }


    /**
     * Draws all enemies currently spawned in the screen to the ground
     * @param spriteBatch faster way to draw sprites
     */
    public void draw(SpriteBatch spriteBatch){
        if(enemies.isEmpty()){return;}

        for(Enemy enemy: enemies){
            enemy.draw(spriteBatch);
        }
    }

}
