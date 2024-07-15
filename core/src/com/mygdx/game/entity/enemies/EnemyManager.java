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

    /**
     *
     * @param world Box2D world
     * @param spawnAreas the 4 Rectangles body where enemies are supposed to spawn from
     * @param entityManager has IMPORTANT variables
     * @param playerPosition position of the players
     */
    public EnemyManager(World world, Array<EnemySpawnArea> spawnAreas,
                        EntityManager entityManager, Vector2 playerPosition){
        this.entityManager = entityManager;
        this.numEnemiesToSpawn = 0;
        this.currentEnemies = new Array<>();
        this.enemiesToRemove = new Array<>();
        this.enemyGenerator= new EnemyGenerator(world, spawnAreas, this);
        enemyGenerator.createEnemy(playerPosition);
    }


    /**
     * Removes Enemies. Updates Enemies. Creates Enemies. Increases Difficulty
     * @param delta time since last render
     */
    public void update(float delta) {

        //Remove and dispose of enemies in one pass
        for (Iterator<Enemy> iterator = enemiesToRemove.iterator(); iterator.hasNext();) {
            System.out.println("Removing Enemy");
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

//        if(readyToSpawn){
//
//            readyToSpawn=false;
//        }

        //Create new Enemies
        while(numEnemiesToSpawn>0){
            System.out.println("Creating new Enemies");
            enemyGenerator.create(entityManager.getPlayer().getBody().getPosition());
            numEnemiesToSpawn--;
        }

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
//    public static boolean isReadyToSpawn(){
//        return readyToSpawn;
//    }

}
