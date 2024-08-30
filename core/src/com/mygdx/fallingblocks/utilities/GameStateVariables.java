package com.mygdx.fallingblocks.utilities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fallingblocks.entity.enemies.Enemy;
import com.mygdx.fallingblocks.entity.enemies.EnemyManager;

import static com.mygdx.fallingblocks.GlobalStaticVariables.*;


/**
 * Store and Manage all gameState variables for game progress
 */
public class GameStateVariables {

    private boolean isReset, isResetRequested, isResetReady;
    private float enemySpeedX, enemySpeedY, waitTime;
    private int score, lastScore, currentMaxEnemyAllowed;

    public GameStateVariables(){
        resetGameVariables();
    }

    private void resetGameVariables(){
        this.score=0;
        this.lastScore=0;
        this.currentMaxEnemyAllowed =1;
        this.enemySpeedX= ENEMY_BASE_MOVEMENT_SPEED.x;
        this.enemySpeedY= ENEMY_BASE_MOVEMENT_SPEED.y;
        this.waitTime=3f;
        this.isReset =false;
    }


    public void update(){
        if(score==lastScore){
            return;
        }

        increaseGameDifficulty();
    }

    /**
     * Reset all game state variables
     * Remove all box2D enemies bodies from the screen
     * @param enemyManager enemy controller
     */
    public void reset(EnemyManager enemyManager){
        resetGameVariables();

        Array<Enemy> currentEnemies= enemyManager.getCurrentEnemies();
        for(int i=0; i< currentEnemies.size; i++){
            Enemy enemy= currentEnemies.get(i);
            enemy.dispose();
        }
        currentEnemies.clear();
    }

    /**
     * Increase game difficulty
     */
    public void increaseGameDifficulty(){
        //if score hasn't changed at all
        if(score ==lastScore){
            return;
        }

        //Match last score to current score
        lastScore=score;

        // Increase Enemy Movement Speed. Lower wait time for new enemy to drop
        if(score % 10==0){
            enemySpeedX= ENEMY_BASE_MOVEMENT_SPEED.x + ENEMY_MOVEMENT_SPEED_HUGE_INCREASE;
            enemySpeedY= ENEMY_BASE_MOVEMENT_SPEED.x + ENEMY_MOVEMENT_SPEED_HUGE_INCREASE;
            waitTime= waitTime-0.1f;
        } else{
            enemySpeedX= ENEMY_BASE_MOVEMENT_SPEED.x + ENEMY_MOVEMENT_SPEED_TINY_INCREASE;
            enemySpeedY= ENEMY_BASE_MOVEMENT_SPEED.y+ ENEMY_MOVEMENT_SPEED_TINY_INCREASE;
            waitTime= waitTime-0.5f;
        }

        increaseMaxEnemyAllowed();
    }

    /**
     * Increase how many enemies are allowed to be in the map at the same time
     */
    private void increaseMaxEnemyAllowed(){
        currentMaxEnemyAllowed = MAX_ENEMY_ALLOWED;
        for(int i = 0; i< INCREASE_MAX_ENEMY_ALLOWED_THRESHOLDS.length; i++){
            //if current score is less than the number in that index
            if(score < INCREASE_MAX_ENEMY_ALLOWED_THRESHOLDS[i]){
                currentMaxEnemyAllowed = i+1;
                break;
            }
        }
    }

    public int getScore(){return this.score;}
    public int getLastScore(){return  this.lastScore;}
    public float getWaitTimer(){return this.waitTime;}
    public Boolean getReset(){
        return this.isReset;
    }
    public int getCurrentMaxEnemyAllowed(){return this.currentMaxEnemyAllowed;}
    public Vector2 getEnemyMovementSpeed(){return new Vector2(enemySpeedX, enemySpeedY);}
    public void resetGameVariables(Boolean reset){
        this.isReset =reset;
    }
    public void incrementScore() {
        this.score++;

    }

}
