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


    //For easy management of all enemies in the world
    private final Array<Enemy> enemies;
    private final Array<Enemy> enemiesToRemove;

    public EnemyManager(World world){
        enemies= new Array<>();
        enemiesToRemove= new Array<>();
        enemies.add( new Enemy(world, new Vector2(100, 100), 5F));
    }


    public Array<Enemy> getEnemiesToRemove(){
        return enemiesToRemove;
    }



    /**
     * Update all enemies
     */
    public void update(){
        if(enemies.isEmpty()){
//            System.out.println("Enemy Manager: No Enemies to update");
            return;
        }

        for (Enemy enemy: enemies){
            enemy.update();
        }

    }

    /**
     * Remove all enemies from the enemiesToRemove Array
     */
    public void removeEnemies(){

        if(enemiesToRemove.isEmpty()){
            return;
        }

        System.out.println("Enemy Manager: Removing enemies");
        for (Enemy enemy : enemiesToRemove) {
            enemy.dispose();
            enemies.removeValue(enemy, true);
        }
        enemiesToRemove.clear();

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
