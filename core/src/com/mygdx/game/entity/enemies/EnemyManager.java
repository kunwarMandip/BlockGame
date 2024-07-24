package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameStateVariables;


/**
 * Responsible for managing enemies:
 * manages enemies spawn
 * manages enemies death
 * manages difficulty
 */
public class EnemyManager {

    //For easy management of all enemies in the world
    private final Array<Enemy> currentEnemies;
    private final Array<Enemy> enemiesToRemove;

    private final EnemyGenerator enemyGenerator;
    public final GameStateVariables gameStateVariables;
    private final EnemyIncomingDirection enemySpawnDirection;


    public EnemyManager(World world, TiledMap tiledMap, GameStateVariables gameStateVariables){
        this.gameStateVariables=gameStateVariables;
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

        removeEnemy();

        //Update enemies
        for(int i=0; i<currentEnemies.size; i++){
            Enemy enemy=currentEnemies.get(i);
            enemy.update(delta);
        }

        //Check if new enemies can be created
        enemyGenerator.spawnEnemies(playerPosition, playerColor);
        enemyGenerator.increaseDifficulty();

    }

    /**
     * Draws TEXTURES to the BOX2D body
     * @param spriteBatch faster way to draw sprites
     */
    public void draw(SpriteBatch spriteBatch){
        enemySpawnDirection.draw(spriteBatch);
        for(int i=0; i<currentEnemies.size; i++){
            currentEnemies.get(i).draw(spriteBatch);
        }
    }

    /**
     * Call dispose method on each ENEMY in enemiesToRemove
     * Then empty the enemiesToRemove Array
     */
    public void removeEnemy(){
        for (int i=0; i<enemiesToRemove.size; i++){
            System.out.println("Removing Enemy");
            Enemy enemy=enemiesToRemove.get(i);
            enemy.dispose();
            currentEnemies.removeValue(enemy, true);
        }
        enemiesToRemove.clear();

    }
    public Array<Enemy> getEnemiesToRemove(){
        return enemiesToRemove;
    }

    public Array<Enemy> getCurrentEnemies(){
        return currentEnemies;
    }

    public EnemyIncomingDirection getEnemySpawnDirection(){
        return enemySpawnDirection;
    }

}
