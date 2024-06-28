package com.mygdx.game.entity.enemies;

public class EnemyDifficulty {

    private final EnemyManager enemyManager;

    private int maxEnemySpawn = 1; // Max enemies on the map
    private float minSpawnDelay = 5; // Delay between enemy spawns
    private final float maxEnemySpawnGrowthRate = 0.1f; // How fast the max enemies grow
    private final float minSpawnDelayGrowth = 0.1f; // Growth in delay rate between enemy spawns

    private float timeSinceLastSpawn = 0; // Time since last enemy spawn
    private float difficultyIncreaseInterval = 30; // Time interval to increase difficulty
    private float timeSinceDifficultyIncrease = 0; // Time since the last difficulty increase

    public EnemyDifficulty(EnemyManager enemyManager) {
        this.enemyManager = enemyManager;
    }


    public void update(float deltaTime) {
        // Update time since last spawn
        timeSinceLastSpawn += deltaTime;
        timeSinceDifficultyIncrease += deltaTime;

        // Check if it's time to spawn a new enemy
        if (timeSinceLastSpawn >= minSpawnDelay && enemyManager.getCurrentEnemies().size < maxEnemySpawn) {
            enemyManager.incrementEnemiesToSpawn();
            timeSinceLastSpawn = 0; // Reset spawn timer
        }

        // Check if it's time to increase difficulty
        if (timeSinceDifficultyIncrease >= difficultyIncreaseInterval) {
            increaseDifficulty();
            timeSinceDifficultyIncrease = 0; // Reset difficulty timer
        }
    }


    private void increaseDifficulty() {

        maxEnemySpawn += 1;  //Increase maxEnemySpawn by 1

        // Decrease minSpawnDelay
        if (minSpawnDelay - minSpawnDelayGrowth > 1) {
            minSpawnDelay -= minSpawnDelayGrowth;
        }
    }
}


