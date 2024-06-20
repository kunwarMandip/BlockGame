package com.mygdx.game.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entity.enemies.Enemy;
import com.mygdx.game.entity.player.Player;

/**
 * Responsible for managing all entities that are supposed to be loaded
 * in the current Map including Players, enemies, objects i.e. Bullets
 */
public class EntityManager {

    private World world;

    private Player player;
    private Enemy enemy;

    public EntityManager(World world){
        initPlayer();
    }

    private void initPlayer(){
        player= new Player(world, new Vector2(100, 200);

    }

}
