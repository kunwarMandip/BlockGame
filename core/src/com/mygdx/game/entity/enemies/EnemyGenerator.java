package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameStateVariables;
import com.mygdx.game.entity.DefineTexture;
import com.mygdx.game.map.objects.EnemySpawnArea;

import java.util.Iterator;
import java.util.Random;

import static com.mygdx.game.StaticVariables.*;
import static com.mygdx.game.entity.DefineTexture.textureHashMap;
import static com.mygdx.game.entity.DefineTexture.textureNames;


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

    private int lastNumber = 1;
    private final Random random;

    private final EnemyTextureChooser enemyTextureChooser;
    public EnemyGenerator(World world, TiledMap tiledMap, GameStateVariables gameStateVariables, EnemyManager enemyManager){
        this.world=world;
        this.enemyManager= enemyManager;
        this.gameStateVariables=gameStateVariables;
        this.enemyTextureChooser= new EnemyTextureChooser();

        random = new Random();
        spawnAreaRectangles=new Array<>();
        loadEnemyRectangleSpawnArea(tiledMap);
    }

    /**
     * Increases Difficulty based on Score
     */
    public void increaseDifficulty(){

        //if score hasn't changed at all
        if(gameStateVariables.score ==gameStateVariables.lastScore){
            return;
        }
        //Update last score so now the score needs to be increased again
        gameStateVariables.lastScore++;

        //Increase Fall Speed. Lower wait time for new enemy to drop
        if(gameStateVariables.getScore() % 10==0){
            gameStateVariables.enemySpeedX=BASE_SPEED.x + BOOST_SPEED_INCREASE;
            gameStateVariables.enemySpeedY=BASE_SPEED.x + BOOST_SPEED_INCREASE;
            gameStateVariables.waitTime= gameStateVariables.waitTime-0.1f;
        } else{
            gameStateVariables.enemySpeedX= BASE_SPEED.x + STEADY_SPEED_INCREASE;
            gameStateVariables.enemySpeedY= BASE_SPEED.y+ STEADY_SPEED_INCREASE;
            gameStateVariables.waitTime= gameStateVariables.waitTime-0.5f;
        }

        //Increase max enemy in the map at any given time
        int tempCurrentScore=gameStateVariables.score;
        gameStateVariables.maxEnemyThreshold=MAX_ENEMY_THRESHOLD;
        for(int i =0; i< scoreThresholds.length; i++){
            //if current score is less than the number in that index
            if(tempCurrentScore < scoreThresholds[i]){
                gameStateVariables.maxEnemyThreshold= i+1;
                break;
            }
        }
    }

    /**
     * Checks if new enemies can be spawned in the map, and does so if possible
     * @param playerLocation location of player Box2D body
     */
    public void spawnEnemies(Vector2 playerLocation, String playerColor){
        //Amount of enemies we need to spawn
        int enemiesToAddSize=enemyManager.getEnemiesToAdd().size;
        int currentEnemiesSize=enemyManager.getCurrentEnemies().size;
        int numEnemiesToSpawn=gameStateVariables.maxEnemyThreshold - (enemiesToAddSize + currentEnemiesSize);

        if(numEnemiesToSpawn<0){
            return;
        }

        for(int i=0; i<numEnemiesToSpawn; i++){
            createEnemy(playerLocation, playerColor);
        }
    }

    private void createEnemy(Vector2 playerLocation, String playerColor){
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
        String enemyColor= chooseEnemyTexture(playerColor);
        spawnLocation = new Vector2(spawnLocationX, spawnLocationY);
        enemyManager.getEnemiesToAdd().add(new Enemy(enemyManager, world, enemyColor, spawnLocation, enemyFallSpeed, waitTime));
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


    private String chooseEnemyTexture(String playerColor){
        //Enemy and Player match color
        if(enemyTextureChooser.update()){
           return playerColor;
        }

        Random random = new Random();
        String enemyColor;
        int randomIndex;
        do {
            randomIndex = random.nextInt(DefineTexture.textureHashMapSize);
            enemyColor = DefineTexture.textureNames[randomIndex];
        } while (enemyColor.equals(playerColor));
        return  enemyColor;
    }

    /**
     * Sets Areas for where the enemy can be spawned from
     * @param tiledMap map to search the objects from
     */
    private void loadEnemyRectangleSpawnArea(TiledMap tiledMap){
        MapLayer targetLayer= tiledMap.getLayers().get("EnemySpawn");
        for (RectangleMapObject object : targetLayer.getObjects().getByType(RectangleMapObject.class)) {
            spawnAreaRectangles.add(new EnemySpawnArea(world, tiledMap, object));
        }
    }

    /**
     * To reset difficulty when the game ends
     */
    public void resetDifficulty(){
       gameStateVariables.reset();
    }
}
