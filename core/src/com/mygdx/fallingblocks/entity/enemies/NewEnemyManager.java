package com.mygdx.fallingblocks.entity.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fallingblocks.utilities.SolidTextureCreator;

public class NewEnemyManager {


    private final Array<Enemy> currentEnemies= new Array<>();

    private final EnemyGenerator enemyGenerator;
    public NewEnemyManager(World world, SolidTextureCreator solidTextureCreator){
        this.enemyGenerator= new EnemyGenerator(world, solidTextureCreator);
    }


    public void update(int numEnemiesToSpawn, Vector2 playerPosition, Vector2 enemySpeed){
        enemyGenerator.spawnEnemy(currentEnemies, numEnemiesToSpawn, playerPosition, enemySpeed);
    }


    public void draw(SpriteBatch spriteBatch){
        for(Enemy enemy: currentEnemies){
            enemy.draw(spriteBatch);
        }
    }
}
