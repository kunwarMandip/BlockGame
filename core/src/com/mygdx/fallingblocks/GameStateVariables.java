package com.mygdx.fallingblocks;

import com.badlogic.gdx.utils.Array;
import com.mygdx.fallingblocks.entity.enemies.Enemy;
import com.mygdx.fallingblocks.entity.enemies.EnemyManager;


import static com.mygdx.fallingblocks.StaticVariables.BASE_SPEED;

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
        this.enemySpeedX=BASE_SPEED.x;
        this.enemySpeedY=BASE_SPEED.y;
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

    public int getScore() {
        return score;
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
