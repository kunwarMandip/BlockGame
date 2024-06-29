package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.utils.Array;


/**
 * When called update method, spawns enemies
 */
public class EnemyGenerator {

    private final World world;
    private EnemyManager enemyManager;
    private float screenHeight;
    private float screenWidth;

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

    public void createEnemy(int numEnemiesToSpawn){
        for(int i=0; i<numEnemiesToSpawn; i++){
            enemyManager.getCurrentEnemies().add(new Enemy(world, new Vector2(5, 5), 6));
            enemyManager.decrementNumEnemiesToSpawn();
        }
    }

    public void chooseEnemy(){
    }



}
