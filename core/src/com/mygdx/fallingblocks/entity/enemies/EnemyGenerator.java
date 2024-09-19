package com.mygdx.fallingblocks.entity.enemies;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.fallingblocks.utilities.GameStateVariables;
import com.mygdx.fallingblocks.map.objects.EnemySpawnArea;
import com.mygdx.fallingblocks.utilities.EntityTextureChooser;
import com.mygdx.fallingblocks.utilities.DynamicTextureCreator;


/**
 * Responsible for choosing:
 * Which Rectangle Spawn Area to spawn Enemy Object from
 * Create a fair System from to choose the Spawn Point
 */
public class EnemyGenerator {

    private final World world;
    private final EnemyManager enemyManager;
    private final GameStateVariables gameStateVariables;
    private final DynamicTextureCreator solidColorCreator;

    private final EnemySpawner enemySpawner;
    private final EntityTextureChooser enemyTextureChooser;

    public EnemyGenerator(World world,
                          TiledMap tiledMap,
                          GameStateVariables gameStateVariables,
                          EnemyManager enemyManager,
                          DynamicTextureCreator solidColorCreator) {

        this.world = world;
        this.enemyManager = enemyManager;
        this.solidColorCreator = solidColorCreator;
        this.gameStateVariables = gameStateVariables;

        this.enemyTextureChooser = new EntityTextureChooser(solidColorCreator.getSize());
        this.enemySpawner = new EnemySpawner(world, tiledMap);
    }


    /**
     * Checks if new enemies can be spawned in the map, and does so if possible
     * @param playerLocation location of player Box2D body
     */
    public void spawnEnemies(Vector2 playerLocation){
        int maxEnemyAllowed= gameStateVariables.getCurrentMaxEnemyAllowed();
        int mapEnemyCount=enemyManager.getCurrentEnemies().size;
        int numEnemiesToSpawn= maxEnemyAllowed - mapEnemyCount;

        for(int i =0; i<numEnemiesToSpawn; i++){
            createEnemy(playerLocation);
        }
    }


    private void createEnemy(Vector2 playerLocation){
        EnemySpawnArea spawnArea= enemySpawner.getRandomSpawnArea();
        float spawnLocationX, spawnLocationY;
        Vector2 enemyFallSpeed;

        Vector2 movementSpeed= gameStateVariables.getEnemyMovementSpeed();
        float enemySpeedX= movementSpeed.x;
        float enemySpeedY= movementSpeed.y;

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

        float waitTime=gameStateVariables.getWaitTimer();
        Vector2 spawnLocation = new Vector2(spawnLocationX, spawnLocationY);
        int colorId=enemyTextureChooser.getColorNumber(solidColorCreator.getPlayerColorID());
        Enemy enemy= new Enemy(world, solidColorCreator, colorId, spawnLocation, enemyFallSpeed, waitTime);
        enemyManager.getCurrentEnemies().add(enemy);
        enemyManager.getEnemySpawnDirection().setDirection(spawnArea.getSpawnDirection());
    }


}
