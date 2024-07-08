package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entity.EntityManager;


import java.util.Iterator;

/**
 * Responsible for managing enemies:
 * manages enemies spawn
 * manages enemies death
 * manages difficulty
 */
public class EnemyManager {

    private EntityManager entityManager;

    //For easy management of all enemies in the world
    private int numEnemiesToSpawn;
    private final Array<Enemy> currentEnemies;
    private final Array<Enemy> enemiesToRemove;
//    private final EnemyDifficulty enemyDifficulty;
    private final EnemyGenerator enemyGenerator;

    public EnemyManager(World world, OrthographicCamera gameCamera, EntityManager entityManager){
        this.entityManager=entityManager;
        numEnemiesToSpawn =0;
        currentEnemies = new Array<>();
        enemiesToRemove = new Array<>();
//        enemyDifficulty = new EnemyDifficulty(this);
        enemyGenerator= new EnemyGenerator(world, gameCamera, this);
//        currentEnemies.add(new Enemy(world, new Vector2(100, 100), 5F));
    }


    /**
     * Remove and dispose of enemies in one pass.
     * Update existing enemies.
     * Increase difficulty and create new Enemies.
     * @param delta time since last render
     * @param bodyLocation location of where the player is currently at.
     */
    public void update(float delta, Vector2 bodyLocation) {

        //Remove and dispose of enemies in one pass
        for (Iterator<Enemy> iterator = enemiesToRemove.iterator(); iterator.hasNext();) {
            Enemy enemy = iterator.next();
            enemy.dispose();
            currentEnemies.removeValue(enemy, true);
            numEnemiesToSpawn++;
            iterator.remove();
        }

        //Update existing enemies
        for (Enemy enemy : currentEnemies) {
            enemy.update();
        }

        //Increase difficulty and create new Enemy
//        enemyDifficulty.update(delta);
//        currentEnemies.add(enemyGenerator.createEnemy());
        enemyGenerator.createEnemy(numEnemiesToSpawn, entityManager.getPlayer().getBody().getPosition());
    }

    /**
     * Draws all enemies currently spawned in the screen to the ground
     * @param spriteBatch faster way to draw sprites
     */
    public void draw(SpriteBatch spriteBatch){
        if(currentEnemies.isEmpty()){return;}

        for (Enemy enemy : currentEnemies) {
            enemy.draw(spriteBatch);
        }

    }

    public Array<Enemy> getEnemiesToRemove(){
        return enemiesToRemove;
    }

    public Array<Enemy> getCurrentEnemies(){
        return currentEnemies;
    }

    public void incrementEnemiesToSpawn(){
        numEnemiesToSpawn++;
    }


    public EnemyGenerator getEnemyGenerator() {
        return enemyGenerator;
    }

    public void decrementNumEnemiesToSpawn(){
        numEnemiesToSpawn--;
    }

    public void setNumEnemiesToSpawn(int numEnemiesToSpawn) {
        this.numEnemiesToSpawn = numEnemiesToSpawn;
    }


}
