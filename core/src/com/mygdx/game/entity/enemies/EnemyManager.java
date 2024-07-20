package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
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

    //For easy management of all enemies in the world
    private final Array<Enemy> enemiesToAdd;
    private final Array<Enemy> currentEnemies;
    private final Array<Enemy> enemiesToRemove;
    private final EnemyGenerator enemyGenerator;
    private final EnemyIncomingDirection enemySpawnDirection;
    /**
     *
     * @param world Box2D world
     * @param spawnAreas the 4 Rectangles body where enemies are supposed to spawn from
     * @param entityManager has IMPORTANT variables
     */
    public EnemyManager(World world, TiledMap tiledMap, EntityManager entityManager){
        this.enemiesToAdd = new Array<>();
        this.currentEnemies = new Array<>();
        this.enemiesToRemove = new Array<>();
        this.enemyGenerator= new EnemyGenerator(world, tiledMap, this);
        this.enemySpawnDirection=new EnemyIncomingDirection(world, tiledMap);

    }


    /**
     * Removes Enemies. Updates Enemies. Increases Difficulty
     * @param delta time since last render
     */
    public void update(float delta, Vector2 playerPosition) {

        //Dispose of enemies
        for (Iterator<Enemy> iterator = enemiesToRemove.iterator(); iterator.hasNext();) {
            System.out.println("Removing Enemy");
            Enemy enemy = iterator.next();
            enemy.dispose();
            currentEnemies.removeValue(enemy, true);
            iterator.remove();
        }

        //Check if new enemies need to be spawned
        enemyGenerator.spawnEnemies(playerPosition);

        //Update enemies
        for (Enemy enemy : currentEnemies) {
            enemy.update(delta);
        }

        for(Enemy enemy: enemiesToAdd){
            enemy.update(delta);
        }

    }

    /**
     * Draws all enemies currently spawned in the screen to the ground
     * @param spriteBatch faster way to draw sprites
     */
    public void draw(SpriteBatch spriteBatch){

        enemySpawnDirection.draw(spriteBatch);

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

    public EnemyGenerator getEnemyGenerator() {
        return enemyGenerator;
    }

    public EnemyIncomingDirection getEnemySpawnDirection(){
        return enemySpawnDirection;
    }

    public Array<Enemy> getEnemiesToAdd() {
        return enemiesToAdd;
    }
}
