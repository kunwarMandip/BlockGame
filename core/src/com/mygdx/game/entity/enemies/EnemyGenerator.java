package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GlobalVariables;
import com.mygdx.game.map.SpawnArea;

import java.util.Random;


/**
 * Responsible for choosing:
 *      :which Rectangle Spawn Area to spawn Enemy Object from
 *      :Create a fair System from to choose the Spawn Point
 */
public class EnemyGenerator {

    private final World world;
    private final Array<SpawnArea> spawnAreas;
    private final EnemyManager enemyManager;

    private int lastNumber = -1;
    private final Random random;

    public EnemyGenerator(World world, Array<SpawnArea> spawnAreas, EnemyManager enemyManager){
        this.world=world;
        this.spawnAreas= spawnAreas;
        this.enemyManager= enemyManager;
        random = new Random();
    }

    /**
     * Choose which Rectangle Spawn Area to Spawn Enemy from
     */
    private int chooseRectangleToSpawn(){
        int number = random.nextInt(4) +1;

        while (number == lastNumber && random.nextDouble() < 0.75) {
            number = random.nextInt(4) + 1; // Retry generating a number
        }
        lastNumber = number; // Update lastNumber with the generated number
        return number;
    }

    /**
     * Choose the X and Y location to spawn Enemy from using playerLocation
     * @param playerLocation the location of the body of the player
     */
    public void createEnemy(Vector2 playerLocation){

        System.out.println("Spawn: " +chooseRectangleToSpawn());
        SpawnArea spawnArea= spawnAreas.get(chooseRectangleToSpawn());

        Vector2 enemySpeed; //how fast the enemy moves
        //Y Coordinate doesn't matter unless its left or right rectangle
        Vector2 spawnLocation;
        float spawnLocationX;
        float spawnLocationY;

        switch(spawnArea.getRectangleDirection()){
            case "top":
                spawnLocationX= playerLocation.x;
                spawnLocationY= (spawnArea.getRectangleSpawnArea().y/GlobalVariables.PPM)/2;
                enemySpeed= new Vector2(0, -10f);
                break;
            case "bottom":
                spawnLocationX= playerLocation.x;
                spawnLocationY= (spawnArea.getRectangleSpawnArea().y/GlobalVariables.PPM)/2;
                enemySpeed= new Vector2(0, 10f);
                break;
            case "left":
                spawnLocationY= playerLocation.y;
                spawnLocationX= (spawnArea.getRectangleSpawnArea().x/GlobalVariables.PPM)/2;
                enemySpeed= new Vector2(-10f, 0);
                break;
            default: //For right and default
                spawnLocationY= playerLocation.y;
                spawnLocationX= (spawnArea.getRectangleSpawnArea().x/GlobalVariables.PPM)/2;
                enemySpeed= new Vector2(10f, 0);
                break;
        }

        spawnLocation= new Vector2(spawnLocationX, spawnLocationY);
        enemyManager.getCurrentEnemies().add(new Enemy(world, spawnLocation, enemySpeed));

    }


    public void chooseEnemy(){
    }



}
