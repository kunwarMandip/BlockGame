package com.mygdx.fallingblocks.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;

public class GameVariables {

    private int score=0;
    private int lastScore=0;

    private int maxConcurrentEnemies;
    private int currentConcurrentEnemies;

    private boolean isGamePaused= false;
    private boolean isPlayerAlive= false;
    private boolean isLevelFinished= false;

    private Vector2 enemyCurrentSpeed, playerCurrentSpeed;
    private Vector2 enemyBaseSpeed, enemySpeedIncrease;
    private Vector2 playerBaseSpeed, playerSpeedIncrease;

    public GameVariables(){};
    public GameVariables(Vector4 enemyInfo, Vector4 playerInfo){
        this.enemyBaseSpeed= new Vector2(enemyInfo.x, enemyInfo.y);
        this.enemySpeedIncrease= new Vector2(enemyInfo.z, enemyInfo.w);

        this.playerBaseSpeed= new Vector2(playerInfo.x, playerInfo.y);
        this.playerSpeedIncrease= new Vector2(playerInfo.z, playerInfo.w);

        this.enemyCurrentSpeed= new Vector2(enemyInfo.x, enemyInfo.y);
        this.playerCurrentSpeed= new Vector2(playerInfo.x, playerInfo.y);
    }

    public void update(float deltaTime){

    }

    /**
     * Increase Enemy current speed by new Vector2 by adding them together
     * @param enemySpeed the speed to increase enemy Speed by
     */
    public void increaseEnemySpeed(Vector2 enemySpeed){
        this.enemyCurrentSpeed.x+= enemySpeed.x;
        this.enemyCurrentSpeed.y+= enemySpeed.y;
    }

    /**
     * Increase Player current speed by new Vector2 by adding them together
     * @param playerSpeed the speed to increase player Speed by
     */
    public void increasePlayerSpeed(Vector2 playerSpeed){
        this.playerCurrentSpeed.x+= playerSpeed.x;
        this.playerCurrentSpeed.y+= playerSpeed.y;
    }

    /**
     * Reset Player and Enemy Speed to their base speed
     */
    private void reset(){
        this.enemyCurrentSpeed= new Vector2(enemyBaseSpeed.x, enemyBaseSpeed.y);
        this.playerCurrentSpeed= new Vector2(playerBaseSpeed.x, playerBaseSpeed.y);
    }

    /**
     * Change Last-Score to current-Score and increment current-Score by new Score
     * @param points Increment current Score by points given
     */
    public void setScore(int points) {
        lastScore=score;
        score+=points;
    }

    public Vector2 getPlayerBaseSpeed() {
        return playerBaseSpeed;
    }

    public Vector2 getPlayerSpeedIncrease() {
        return playerSpeedIncrease;
    }

    public Vector2 getEnemySpeedIncrease() {
        return enemySpeedIncrease;
    }

    public Vector2 getEnemyBaseSpeed() {
        return enemyBaseSpeed;
    }

    public int getLastScore() {
        return lastScore;
    }

    public int getScore() {
        return score;
    }

    public boolean isPlayerAlive() {
        return isPlayerAlive;
    }

    public void setPlayerAlive(boolean playerAlive) {
        isPlayerAlive = playerAlive;
    }

    public boolean isLevelFinished() {
        return isLevelFinished;
    }

    public void setLevelFinished(boolean levelFinished) {
        isLevelFinished = levelFinished;
    }

    public boolean isGamePaused() {
        return isGamePaused;
    }

    public void setGamePaused(boolean gamePaused) {
        isGamePaused = gamePaused;
    }
}
