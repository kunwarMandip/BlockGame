package com.mygdx.fallingblocks.entity.enemies;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fallingblocks.levels.GameVariables;
import com.mygdx.fallingblocks.utilities.GameStateVariables;
import com.mygdx.fallingblocks.map.objects.EnemySpawnArea;
import com.mygdx.fallingblocks.utilities.EntityTextureChooser;
import com.mygdx.fallingblocks.utilities.SolidTextureCreator;


/**
 * Responsible for choosing:
 * Which Rectangle Spawn Area to spawn Enemy Object from
 * Create a fair System from to choose the Spawn Point
 */
public class EnemyGenerator {

    private EnemyManager enemyManager;
    private GameStateVariables gameStateVariables;

    private final World world;
    private GameVariables gameVariables;
    private final SolidTextureCreator solidColorCreator;

    private final EnemySpawner enemySpawner;
    private final EntityTextureChooser enemyTextureChooser;


    public EnemyGenerator(World world, SolidTextureCreator solidTextureCreator){
        this.world=world;
        this.solidColorCreator=solidTextureCreator;

        this.enemySpawner= new EnemySpawner(world, new TmxMapLoader().load("map/tiledMap.tmx"));
        this.enemyTextureChooser = new EntityTextureChooser(solidColorCreator.getSize());
    }

    public void spawnEnemy(Array<Enemy> currentEnemies, int spawnCount, Vector2 playerLocation, Vector2 enemySpeed){
        for(int i =0; i<spawnCount; i++){
            int enemyColor= enemyTextureChooser.getColorNumber(SolidTextureCreator.playerColorID);
            Vector4 enemySpawnAndSpeedInfo= getEnemySpawnAndSpeedInfo(playerLocation, enemySpeed);
            currentEnemies.add(new Enemy(world, enemyColor, solidColorCreator, enemySpawnAndSpeedInfo, null));
        }
    }

    private Vector4 getEnemySpawnAndSpeedInfo(Vector2 playerLocation, Vector2 enemyCurrentSpeed){

        EnemySpawnArea spawnArea= enemySpawner.getRandomSpawnArea();
        Vector2 enemyMovementSpeed, enemySpawnLocation;

        switch(spawnArea.getSpawnDirection()){
            case "bottom":
                enemySpawnLocation= new Vector2(playerLocation.x, spawnArea.getPosition().y);
                enemyMovementSpeed= new Vector2(0, enemyCurrentSpeed.y);
                break;
            case "left":
                enemySpawnLocation= new Vector2(spawnArea.getPosition().x, playerLocation.y);
                enemyMovementSpeed= new Vector2(enemyCurrentSpeed.x, 0);
                break;
            case "right":
                enemySpawnLocation= new Vector2(spawnArea.getPosition().y, playerLocation.y);
                enemyMovementSpeed= new Vector2(-enemyCurrentSpeed.x, 0);
                break;
            case "top":
            default:
                enemySpawnLocation= new Vector2(playerLocation.x, spawnArea.getPosition().y);
                enemyMovementSpeed= new Vector2(enemyCurrentSpeed.x, -enemyCurrentSpeed.y);
                break;
        }

        return new Vector4(enemyMovementSpeed.x, enemyMovementSpeed.y, enemySpawnLocation.x, enemySpawnLocation.y);
    }



//    public EnemyGenerator(World world, TiledMap tiledMap, GameStateVariables gameStateVariables, EnemyManager enemyManager, SolidTextureCreator solidColorCreator) {
//
//        this.world = world;
//        this.enemyManager = enemyManager;
//        this.solidColorCreator = solidColorCreator;
//        this.gameStateVariables = gameStateVariables;
//
//        this.enemyTextureChooser = new EntityTextureChooser(solidColorCreator.getSize());
//        this.enemySpawner = new EnemySpawner(world, tiledMap);
//    }


//    /**
//     * Checks if new enemies can be spawned in the map, and does so if possible
//     * @param playerLocation location of player Box2D body
//     */
//    public void spawnEnemies(Vector2 playerLocation){
//        int maxEnemyAllowed= gameStateVariables.getCurrentMaxEnemyAllowed();
//        int mapEnemyCount=enemyManager.getCurrentEnemies().size;
//        int numEnemiesToSpawn= maxEnemyAllowed - mapEnemyCount;
//
//        for(int i =0; i<numEnemiesToSpawn; i++){
//            createEnemy(playerLocation);
//        }
//    }

//    private void createEnemy(Vector2 playerLocation){
//        EnemySpawnArea spawnArea= enemySpawner.getRandomSpawnArea();
//        float spawnLocationX, spawnLocationY;
//        Vector2 enemyFallSpeed;
//
//        Vector2 movementSpeed= gameStateVariables.getEnemyMovementSpeed();
//        float enemySpeedX= movementSpeed.x;
//        float enemySpeedY= movementSpeed.y;
//
//        switch(spawnArea.getSpawnDirection()){
//            case "bottom":
//                spawnLocationX = playerLocation.x;
//                spawnLocationY = spawnArea.getPosition().y;
//                enemyFallSpeed = new Vector2(0, 10f + enemySpeedY);
//                System.out.println("Rectangle Bottom");
//                break;
//            case "left":
//                spawnLocationX = spawnArea.getPosition().x;
//                spawnLocationY = playerLocation.y;
//                enemyFallSpeed = new Vector2(10f + enemySpeedX, 0);
//                System.out.println("Rectangle Left");
//                break;
//            case "right":
//                spawnLocationX = spawnArea.getPosition().y;
//                spawnLocationY = playerLocation.y;
//                enemyFallSpeed = new Vector2(-10f - enemySpeedX, 0);
//                System.out.println("Rectangle Right");
//                break;
//            default:
//                spawnLocationX = playerLocation.x;
//                spawnLocationY = spawnArea.getPosition().y;
//                enemyFallSpeed = new Vector2(0, -10f - enemySpeedY);
//                System.out.println("Rectangle Top");
//                break;
//        }
//
//        System.out.println("Enemy speed: "+  enemySpeedX + " : "+ enemySpeedY);
//        float waitTime=gameStateVariables.getWaitTimer();
//        Vector2 spawnLocation = new Vector2(spawnLocationX, spawnLocationY);
//        int colorId=enemyTextureChooser.getColorNumber(solidColorCreator.getPlayerColorID());
//        Enemy enemy= new Enemy(world, solidColorCreator, colorId, spawnLocation, enemyFallSpeed, waitTime);
//        enemyManager.getCurrentEnemies().add(enemy);
//        enemyManager.getEnemySpawnDirection().setDirection(spawnArea.getSpawnDirection());
//    }

}
