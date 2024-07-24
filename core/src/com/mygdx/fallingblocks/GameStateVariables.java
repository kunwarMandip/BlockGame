package com.mygdx.fallingblocks;

import com.badlogic.gdx.utils.Array;
import com.mygdx.fallingblocks.entity.enemies.Enemy;
import com.mygdx.fallingblocks.entity.enemies.EnemyManager;

import static com.mygdx.fallingblocks.StaticVariables.*;
import static com.mygdx.fallingblocks.StaticVariables.scoreThresholds;

/**
 * Used to store all the game state variables which contains the game progress.
 * Makes it easier to reset game when player dies OR wants to restart without resetting everything.
 */
public class GameStateVariables {

    public int score, lastScore, maxEnemyThreshold;
    public float enemySpeedX, enemySpeedY, waitTime;
    private boolean reset;

    public GameStateVariables(){
        resetVariables();
    }

    private void resetVariables(){
        this.score=0;
        this.lastScore=0;
        this.maxEnemyThreshold=1;
        this.enemySpeedX= ENEMY_BASE_SPEED.x;
        this.enemySpeedY= ENEMY_BASE_SPEED.y;
        this.waitTime=3f;
        this.reset=false;
    }


    /**
     * Reset all game state variables
     * Remove all box2D enemies bodies from the screen
     * @param enemyManager enemy controller
     */
    public void reset(EnemyManager enemyManager){
        resetVariables();
        Array<Enemy> currentEnemies= enemyManager.getCurrentEnemies();
        Array<Enemy> enemiesToRemove= enemyManager.getEnemiesToRemove();

        enemiesToRemove.clear();
        for(int i=0; i< currentEnemies.size; i++){
            Enemy enemy= currentEnemies.get(i);
            enemy.dispose();
        }
        currentEnemies.clear();
    }

    public void increaseDifficulty(){
        //if score hasn't changed at all
        if(score ==lastScore){
            return;
        }
        //Update last score so now the score needs to be increased again
        lastScore++;

        //Increase Fall Speed. Lower wait time for new enemy to drop
        if(score % 10==0){
            enemySpeedX= ENEMY_BASE_SPEED.x + ENEMY_BOOST_SPEED_INCREASE;
            enemySpeedY= ENEMY_BASE_SPEED.x + ENEMY_BOOST_SPEED_INCREASE;
            waitTime= waitTime-0.1f;
        } else{
            enemySpeedX= ENEMY_BASE_SPEED.x + ENEMY_STEADY_SPEED_INCREASE;
            enemySpeedY= ENEMY_BASE_SPEED.y+ ENEMY_STEADY_SPEED_INCREASE;
            waitTime= waitTime-0.5f;
        }

        //Increase max enemy in the map at any given time
        int tempCurrentScore=score;
        maxEnemyThreshold=MAX_ENEMY_THRESHOLD;
        for(int i =0; i< scoreThresholds.length; i++){
            //if current score is less than the number in that index
            if(tempCurrentScore < scoreThresholds[i]){
                maxEnemyThreshold= i+1;
                break;
            }
        }
    }


    public void incrementScore() {
        score++;
    }

    public void resetGame(Boolean reset){
        this.reset=reset;
    }

    public Boolean getReset(){
        return this.reset;
    }


}
