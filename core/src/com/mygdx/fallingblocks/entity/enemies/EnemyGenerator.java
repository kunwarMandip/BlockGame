package com.mygdx.fallingblocks.entity.enemies;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fallingblocks.GameStateVariables;
import com.mygdx.fallingblocks.map.objects.EnemySpawnArea;
import com.mygdx.fallingblocks.utilities.EntityTextureChooser;
import com.mygdx.fallingblocks.utilities.SolidColorCreator;

import java.util.Random;


/**
 * Responsible for choosing:
 * Which Rectangle Spawn Area to spawn Enemy Object from
 * Create a fair System from to choose the Spawn Point
 */
public class EnemyGenerator {

    private final World world;
    private final EnemyManager enemyManager;
    private final GameStateVariables gameStateVariables;
    private final Array<EnemySpawnArea> spawnAreaRectangles;

    private final SolidColorCreator solidColorCreator;

    private int lastNumber = 1;
    private final Random random= new Random();

    private final EntityTextureChooser enemyTextureChooser;

    public EnemyGenerator(World world,
                          TiledMap tiledMap,
                          GameStateVariables gameStateVariables,
                          EnemyManager enemyManager,
                          SolidColorCreator solidColorCreator){
        this.world=world;
        this.enemyManager= enemyManager;
        this.gameStateVariables=gameStateVariables;
        this.solidColorCreator=solidColorCreator;
        this.enemyTextureChooser= new EntityTextureChooser(solidColorCreator.getSize());

        //Get the rectangleArea Spawn Objects
        this.spawnAreaRectangles=new Array<>();
        MapLayer targetLayer= tiledMap.getLayers().get("EnemySpawn");
        for (RectangleMapObject object : targetLayer.getObjects().getByType(RectangleMapObject.class)) {
            this.spawnAreaRectangles.add(new EnemySpawnArea(world, tiledMap, object));
        }
    }


    /**
     * Checks if new enemies can be spawned in the map, and does so if possible
     * @param playerLocation location of player Box2D body
     */
    public void spawnEnemies(Vector2 playerLocation){
        //Amount of enemies we need to spawn
        int numEnemiesToSpawn=gameStateVariables.maxEnemyThreshold - enemyManager.getCurrentEnemies().size;

        if(numEnemiesToSpawn<0){
            return;
        }

        for(int i=0; i<numEnemiesToSpawn; i++){
            createEnemy(playerLocation);
        }
    }

    private void createEnemy(Vector2 playerLocation){
        EnemySpawnArea spawnArea= spawnAreaRectangles.get(chooseRectangleToSpawn()-1);

        float spawnLocationX, spawnLocationY;
        Vector2 spawnLocation, enemyFallSpeed;
        float enemySpeedX= gameStateVariables.enemySpeedX;
        float enemySpeedY= gameStateVariables.enemySpeedY;

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

        float waitTime=gameStateVariables.waitTime;
        spawnLocation = new Vector2(spawnLocationX, spawnLocationY);
        int colorId=enemyTextureChooser.getColorNumber(solidColorCreator.getPlayerColorID());
        enemyManager.getCurrentEnemies().add(new Enemy(world, solidColorCreator, colorId, spawnLocation, enemyFallSpeed, waitTime));
        enemyManager.getEnemySpawnDirection().setDirection(spawnArea.getSpawnDirection());
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

}
