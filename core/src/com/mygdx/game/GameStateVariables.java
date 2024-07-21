package com.mygdx.game;

import static com.mygdx.game.StaticVariables.BASE_SPEED;

/**
 * Used to store all the game state variables which contains the game progress.
 * Makes it easier to reset game when player dies OR wants to restart without resetting everything.
 */
public class GameStateVariables {

    public int score;
    public float enemySpeedX, enemySpeedY, waitTime;

    public GameStateVariables(){
        reset();
    }

    /**
     * Resets the variables to beginning
     */
    public void reset(){
        this.score=0;
        this.enemySpeedX=BASE_SPEED.x;
        this.enemySpeedY=BASE_SPEED.y;
        this.waitTime=5f;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }


}
