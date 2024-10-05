package com.mygdx.fallingblocks.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.fallingblocks.entity.enemies.Enemy;

import java.util.ArrayList;
import java.util.List;


public class EnemyManage {

    private final List<Enemy> activeEnemies= new ArrayList<>();

    public EnemyManage(){

    }

    public void update(float delta){
        for(Enemy enemy: activeEnemies){
            enemy.update(delta);
        }
    }

    public void draw(SpriteBatch spriteBatch){
        for(Enemy enemy: activeEnemies){
            enemy.draw(spriteBatch);
        }
    }

    public void addEnemy(Enemy enemy){
        activeEnemies.add(enemy);
    }
}
