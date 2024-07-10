package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.map.objects.EnemySpawnArea;


import java.util.Iterator;

/**
 * Responsible for managing enemies:
 * manages enemies spawn
 * manages enemies death
 * manages difficulty
 */
public class EnemyManager {

    private final EntityManager entityManager;

    //For easy management of all enemies in the world
    private int numEnemiesToSpawn;
    private final Array<Enemy> currentEnemies;
    private final Array<Enemy> enemiesToRemove;
    private final EnemyGenerator enemyGenerator;

    public EnemyManager(World world, Array<EnemySpawnArea> spawnAreas, EntityManager entityManager, Vector2 playerPosition){
        this.entityManager = entityManager;
        numEnemiesToSpawn = 0;
        currentEnemies = new Array<>();
        enemiesToRemove = new Array<>();
        enemyGenerator= new EnemyGenerator(world, spawnAreas, this);
//        enemyGenerator.create(playerPosition);
        enemyGenerator.createEnemy(playerPosition);
    }


    /**
     * Remove and dispose of enemies in one pass.
     * Update existing enemies.
     * Increase difficulty and create new Enemies.
     * @param delta time since last render
     * @param bodyLocation Box2D player Body location
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

        //Create new Enemies
//        for(int i=0; i<numEnemiesToSpawn; i++){
//            System.out.println("Creating enemies");
//            enemyGenerator.create(bodyLocation);
//        }

//        enemyGenerator.create(bodyLocation);

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
