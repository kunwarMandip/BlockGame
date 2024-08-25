package com.mygdx.fallingblocks.entity.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fallingblocks.GameStateVariables;
import com.mygdx.fallingblocks.utilities.SolidColorCreator;

import java.util.Iterator;


/**
 * Responsible for managing enemies:
 * manages enemies spawn
 * manages enemies death
 * manages difficulty
 */
public class EnemyManager {

    //For easy management of all enemies in the world
    private final Array<Enemy> currentEnemies= new Array<>();

    private final EnemyGenerator enemyGenerator;
    public final GameStateVariables gameStateVariables;
    private final EnemyIncomingDirection enemySpawnDirection;


    public EnemyManager(World world, TiledMap tiledMap,
                        GameStateVariables gameStateVariables,
                        SolidColorCreator solidColorCreator){
        this.gameStateVariables=gameStateVariables;
        this.enemyGenerator= new EnemyGenerator(world, tiledMap, gameStateVariables, this, solidColorCreator);
        this.enemySpawnDirection=new EnemyIncomingDirection();
    }


    /**
     * Removes Enemies. Updates Enemies. Increases Difficulty
     * @param delta time since last render
     */
    public void update(float delta, Vector2 playerPosition) {

        if(gameStateVariables.getReset()){
            gameStateVariables.reset(this);
            return;
        }

        updateEnemies(delta);

        //Check if new enemies can be created
        enemyGenerator.spawnEnemies(playerPosition);
        gameStateVariables.increaseGameDifficulty();
    }

    /**
     * Remove Enemy set to be removed. U
     * @param delta
     */
    private void updateEnemies(float delta) {
        for (Enemy enemy : currentEnemies) {
            if (enemy.isEnemyToBeRemoved) {
                enemy.dispose();
                currentEnemies.removeValue(enemy, true);
                continue;
            }
            enemy.update(delta);
        }
    }


    public void draw(SpriteBatch spriteBatch){
        enemySpawnDirection.draw(spriteBatch);
        for(Enemy enemy: currentEnemies){
            enemy.draw(spriteBatch);
        }
    }

    public Array<Enemy> getCurrentEnemies(){
        return currentEnemies;
    }

    public EnemyIncomingDirection getEnemySpawnDirection(){
        return enemySpawnDirection;
    }

}
