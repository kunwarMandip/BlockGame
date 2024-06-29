package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GlobalVariables;

public class EnemyDifficulty {

    private final EnemyManager enemyManager;

    public EnemyDifficulty(EnemyManager enemyManager) {
        this.enemyManager = enemyManager;
    }

    public void update(){

    }


//    //Manage Game Difficulty
//    private final int[] difficultyThresholds = {10, 20, 50, 100, 150};
//    private int currentDifficultyThreshold = difficultyThresholds[0]; // Initial difficulty threshold
//    private int scoreSinceLastIncrease = 0;
//    private int currentDifficultyLevelIndex = 0; // Track current difficulty level index
//
//
//
//    /**
//     * Increases the difficulty level when the score reaches a certain threshold
//     */
//    private void updateDifficultyLevel() {
//        if(scoreSinceLastIncrease == currentDifficultyThreshold) {
//            // Increase the index to move to the next difficulty level
//            currentDifficultyLevelIndex++;
//            // Ensure the index is within bounds of the array
//            if(currentDifficultyLevelIndex < difficultyThresholds.length) {
//                currentDifficultyThreshold = difficultyThresholds[currentDifficultyLevelIndex];
//            }
//        }
//        scoreSinceLastIncrease++;
//    }

//    public void update(float deltaTime) {
//
//        if (GlobalVariables.score == 0) {
//            return;
//        }
//
//        if (GlobalVariables.score % minScoreToUpdate == 0) {
//
//            minScoreToUpdate += 10;
//        }
//    }


        // Update time since last spawn
//        timeSinceLastSpawn += deltaTime;
//        timeSinceDifficultyIncrease += deltaTime;
//
//
//        // Check if it's time to spawn a new enemy
//        if (timeSinceLastSpawn >= minSpawnDelay && enemyManager.getCurrentEnemies().size < maxEnemySpawn) {
//            enemyManager.incrementEnemiesToSpawn();
//            timeSinceLastSpawn = 0; // Reset spawn timer
//        }
//
//        // Check if it's time to increase difficulty
//        if (timeSinceDifficultyIncrease >= difficultyIncreaseInterval) {
//            increaseDifficulty();
//            timeSinceDifficultyIncrease = 0; // Reset difficulty timer
//        }
//    }


    private void increaseDifficulty() {

//        maxEnemySpawn += 1;  //Increase maxEnemySpawn by 1
//
//        // Decrease minSpawnDelay
//        if (minSpawnDelay - minSpawnDelayGrowth > 1) {
//            minSpawnDelay -= minSpawnDelayGrowth;
//        }
    }
}


