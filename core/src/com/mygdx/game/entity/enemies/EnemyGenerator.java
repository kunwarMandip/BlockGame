package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GlobalVariables;
import com.mygdx.game.map.objects.EnemySpawnArea;

import java.util.Random;

import static com.mygdx.game.GlobalVariables.*;


/**
 * Responsible for choosing:
 *      :which Rectangle Spawn Area to spawn Enemy Object from
 *      :Create a fair System from to choose the Spawn Point
 */
public class EnemyGenerator {

    private final World world;
    private final EnemyManager enemyManager;
    private final Array<EnemySpawnArea> spawnAreas;

    private int lastNumber = 1;
    private final Random random;

    private float enemySpeedX= BASE_SPEED.x;
    private float enemySpeedY= BASE_SPEED.y;

    //Most amount of enemy that can be on the Map at the same time
    private int currentEnemyCountThreshold=1;

    public EnemyGenerator(World world, Array<EnemySpawnArea> spawnAreas, EnemyManager enemyManager){
        this.world=world;
        this.spawnAreas= spawnAreas;
        this.enemyManager= enemyManager;
        random = new Random();
    }


    /**
     * Increases Difficulty based on Score
     */
    public void increaseDifficulty(){
        //Increase Fall Speed
        if(GlobalVariables.SCORE % 10==0){
            enemySpeedX= BASE_SPEED.x + BOOST_SPEED_INCREASE;
            enemySpeedY= BASE_SPEED.y + BOOST_SPEED_INCREASE;
        } else{
            enemySpeedX= BASE_SPEED.x + STEADY_SPEED_INCREASE;
            enemySpeedY= BASE_SPEED.y+ STEADY_SPEED_INCREASE;
        }

        //Increase max enemy in the map at any given time
        int currentScore = GlobalVariables.SCORE;
        int[] scoreThresholds = {5, 15, 20, 40};
        currentEnemyCountThreshold = MAX_ENEMY_THRESHOLD;

        for (int i = 0; i < scoreThresholds.length; i++) {
            if (currentScore < scoreThresholds[i]) {
                currentEnemyCountThreshold = i + 1;
                break;
            }
        }
    }

    /**
     * To reset difficulty when the game ends
     */
    public void resetDifficulty(){
        GlobalVariables.SCORE=0;
        currentEnemyCountThreshold=1;
        enemySpeedX= BASE_SPEED.x;
        enemySpeedY= BASE_SPEED.y;
    }

    public void spawnEnemies(Vector2 playerLocation){
        //Amount of enemies we need to spawn
        int numEnemiesToSpawn=currentEnemyCountThreshold - enemyManager.getCurrentEnemies().size;
        if(numEnemiesToSpawn==0){return;}

        for(int i=0; i<numEnemiesToSpawn; i++){
            createEnemy(playerLocation);
        }
    }

    /**
     * Chooses a random number between 1 and 4 with
     * @return int (between 1 and 4
     */
    private int chooseRectangleToSpawn(){
        int number = random.nextInt(4) +1;

        while (number == lastNumber && random.nextDouble() < 0.75) {
            number = random.nextInt(4) + 1; // Retry generating a number
        }
        lastNumber = number; // Update lastNumber with the generated number
        return number;
    }


    private void createEnemy(Vector2 playerLocation){
        EnemySpawnArea spawnArea= spawnAreas.get(chooseRectangleToSpawn()-1);

        float spawnLocationX, spawnLocationY;
        Vector2 spawnLocation, enemyFallSpeed;

        switch(spawnArea.getSpawnDirection()){
            case "bottom":
                spawnLocationX = playerLocation.x;
                spawnLocationY = spawnArea.getPosition().y;
                enemyFallSpeed = new Vector2(0, 10f + enemySpeedY);
                System.out.println("Rectangle Bottom");
                break;
            case "left":
                spawnLocationX = spawnArea.getPosition().x;
                spawnLocationY = playerLocation.y;
                enemyFallSpeed = new Vector2(10f + enemySpeedX, 0);
                System.out.println("Rectangle Left");
                break;
            case "right":
                spawnLocationX = spawnArea.getPosition().y;
                spawnLocationY = playerLocation.y;
                enemyFallSpeed = new Vector2(-10f - enemySpeedX, 0);
                System.out.println("Rectangle Right");
                break;
            default:
                spawnLocationX = playerLocation.x;
                spawnLocationY = spawnArea.getPosition().y;
                enemyFallSpeed = new Vector2(0, -10f - enemySpeedY);
                System.out.println("Rectangle Top");
                break;
        }

        System.out.println("Creating New Rectangle: "+ spawnArea.getSpawnDirection());
        System.out.println("Spawn location: " + spawnLocationX + " : " + spawnLocationY);

        spawnLocation = new Vector2(spawnLocationX, spawnLocationY);
        enemyManager.getCurrentEnemies().add(new Enemy(world, spawnLocation, enemyFallSpeed));
    }




}
