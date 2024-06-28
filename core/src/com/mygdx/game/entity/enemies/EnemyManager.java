package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Responsible for managing enemies
 */
public class EnemyManager {

    //For easy management of all enemies in the world
    private final Array<Enemy> currentEnemies;
    private final Array<Enemy> enemiesToRemove;
    private int enemiesToSpawn;
    private final EnemyDifficulty enemyDifficulty;
    private EnemyGenerator enemyGenerator;

    public EnemyManager(World world){
        currentEnemies = new Array<>();
        enemiesToRemove = new Array<>();
        enemiesToSpawn =0;
        enemyDifficulty = new EnemyDifficulty(this);
        enemyGenerator= new EnemyGenerator(world);
        currentEnemies.add( new Enemy(world, new Vector2(100, 100), 5F));
    }

    public Array<Enemy> getEnemiesToRemove(){
        return enemiesToRemove;
    }

    public Array<Enemy> getCurrentEnemies(){
        return currentEnemies;
    }

    public void incrementEnemiesToSpawn(){
        enemiesToSpawn++;
    }



    public void update(float delta, Vector2 bodyLocation) {

        // Remove and dispose of enemies in one pass
        for (Iterator<Enemy> iterator = enemiesToRemove.iterator(); iterator.hasNext();) {
            Enemy enemy = iterator.next();
            enemy.dispose();
            currentEnemies.removeValue(enemy, true);
            iterator.remove();
        }

        enemyDifficulty.update(delta);
        enemyGenerator.update(currentEnemies);

        // Update existing enemies
        for (Enemy enemy : currentEnemies) {
            enemy.update();
        }
    }

    /**
     * Draws all enemies currently spawned in the screen to the ground
     * @param spriteBatch faster way to draw sprites
     */
    public void draw(SpriteBatch spriteBatch){
        if(currentEnemies.isEmpty()){return;}

        for(Enemy enemy: currentEnemies){
            enemy.draw(spriteBatch);
        }
    }


}
