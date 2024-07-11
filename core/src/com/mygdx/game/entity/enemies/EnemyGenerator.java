package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GlobalVariables;
import com.mygdx.game.map.objects.EnemySpawnArea;

import java.util.Random;


/**
 * Responsible for choosing:
 *      :which Rectangle Spawn Area to spawn Enemy Object from
 *      :Create a fair System from to choose the Spawn Point
 */
public class EnemyGenerator {

    private final World world;
    private final Array<EnemySpawnArea> spawnAreas;
    private final EnemyManager enemyManager;

    private int lastNumber = 1;
    private final Random random;
    private int lastScore=GlobalVariables.SCORE;

    public EnemyGenerator(World world, Array<EnemySpawnArea> spawnAreas, EnemyManager enemyManager){
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

    public void create(Vector2 playerLocation){
        if(GlobalVariables.SCORE> lastScore){
            createEnemy(playerLocation);
            lastScore=GlobalVariables.SCORE;
        }
    }

    /**
     * Creates Enemy class to be added to the screen
     * @param playerLocation location of the player
     */
    public void createEnemy(Vector2 playerLocation){
        EnemySpawnArea spawnArea= spawnAreas.get(chooseRectangleToSpawn()-1);

        //Mind that height and width are flipped for left and right rectangles
        //since they are dragged vertically instead of horizontally
        // In box2D world, left to right is X(starting 0), top to bottom is Y
        float spawnLocationX, spawnLocationY;
        Vector2 enemyFallSpeed, spawnLocation;

        switch(spawnArea.getSpawnDirection()){
            case "top":
                spawnLocationX=playerLocation.x;
                spawnLocationY=spawnArea.getPosition().y;
                enemyFallSpeed=new Vector2(0, -10f);
                System.out.println("Rectangle Top");
                break;
            case "bottom":
                spawnLocationX=playerLocation.x;
                spawnLocationY=spawnArea.getPosition().y;
                enemyFallSpeed=new Vector2(0, 10f);
                System.out.println("Rectangle Bottom");
                break;
            case "left":
                spawnLocationX=spawnArea.getPosition().x;
                spawnLocationY=playerLocation.y;
                enemyFallSpeed=new Vector2(10f, 0);
                System.out.println("Rectangle Left");
                break;
            case "right":
                spawnLocationX=spawnArea.getPosition().y;
                spawnLocationY=playerLocation.y;
                enemyFallSpeed=new Vector2(-10f, 0);
                System.out.println("Rectangle Right");
                break;
            default:
                spawnLocationX= 5;
                spawnLocationY=5;
                enemyFallSpeed=new Vector2(0, 0);
                System.out.println("Rectangle Default");
                break;
        }

        System.out.println("Creating New Rectangle. Player Location: "
                + playerLocation.x + " :: " + playerLocation.y
                + ", Spawn location: " + spawnLocationX + " :: " + spawnLocationY);


        spawnLocation = new Vector2(spawnLocationX, spawnLocationY);
        enemyManager.getCurrentEnemies().add(new Enemy(world, spawnLocation,  enemyFallSpeed));
    }

}
