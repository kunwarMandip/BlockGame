package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameStateVariables;


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
    public final GameStateVariables gameStateVariables;
    private final EnemyIncomingDirection enemySpawnDirection;


    public EnemyManager(World world, TiledMap tiledMap, GameStateVariables gameStateVariables){
        this.gameStateVariables=gameStateVariables;
        this.enemiesToAdd = new Array<>();
        this.currentEnemies = new Array<>();
        this.enemiesToRemove = new Array<>();
        this.enemyGenerator= new EnemyGenerator(world, tiledMap, gameStateVariables, this);
        this.enemySpawnDirection=new EnemyIncomingDirection(tiledMap);
    }


    /**
     * Removes Enemies. Updates Enemies. Increases Difficulty
     * @param delta time since last render
     */
    public void update(float delta, Vector2 playerPosition, String playerColor) {

        if(gameStateVariables.getReset()){
            gameStateVariables.reset(this);
            return;
        }

        //Dispose of enemies
        for (Iterator<Enemy> iterator = enemiesToRemove.iterator(); iterator.hasNext();) {
            System.out.println("Removing Enemy");
            Enemy enemy = iterator.next();
            enemy.dispose();
            currentEnemies.removeValue(enemy, true);
            iterator.remove();
        }

        //Update enemies
        for(int i=0; i<currentEnemies.size; i++){
            Enemy enemy=currentEnemies.get(i);
            enemy.update(delta);
        }

        //Check if new enemies can be created
        enemyGenerator.spawnEnemies(playerPosition, playerColor);
        enemyGenerator.increaseDifficulty();

        //Update the enemies timer
        for(int i=0; i<enemiesToAdd.size; i++){
            Enemy enemy=enemiesToAdd.get(i);
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
