package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.map.SpawnArea;


/**
 * When called update method, spawns enemies
 */
public class EnemyGenerator {

    private final World world;
    private final EnemyManager enemyManager;
    private final float screenHeight;
    private final float screenWidth;

    public EnemyGenerator(World world, OrthographicCamera gameCamera, EnemyManager enemyManager){
        this.world=world;
        this.enemyManager=enemyManager;
        screenHeight = gameCamera.position.y + gameCamera.viewportHeight / 2;
        screenWidth= gameCamera.position.x + gameCamera.viewportWidth /2;
    }

    private void calculateEnemyPosition(Vector2 bodyDimension){
        float bodyWidth= bodyDimension.x;
        float bodyHeight=bodyDimension.y;
        float spawnLocationY= (screenWidth/bodyWidth)/2;
    }

    public void createEnemy(int numEnemiesToSpawn, Vector2 playerPosition){

        float playerPositionX= playerPosition.x;

        for(int i=0; i<numEnemiesToSpawn; i++){
            SpawnArea.getRandomSpawnPosition();
            enemyManager.getCurrentEnemies().add(new Enemy(world, new Vector2(5, 5), playerPositionX));
            enemyManager.decrementNumEnemiesToSpawn();
        }
    }

    public void chooseEnemy(){
    }



}
