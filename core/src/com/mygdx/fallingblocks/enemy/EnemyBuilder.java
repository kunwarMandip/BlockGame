package com.mygdx.fallingblocks.enemy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class EnemyBuilder {

    private final World world;

    private Vector2 spawnPosition = new Vector2(0, 0);
    private Vector2 size = new Vector2(1, 1);
    private float health = 1.0f;
    private float speed = 1.0f;

    public EnemyBuilder(World world) {
        this.world = world;
    }

    public EnemyBuilder setSpawnPosition(Vector2 position) {
        this.spawnPosition = position;
        return this;
    }

    public EnemyBuilder setSize(Vector2 size) {
        this.size = size;
        return this;
    }

    public EnemyBuilder setHealth(float health) {
        this.health = health;
        return this;
    }

    public EnemyBuilder setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public FakeEnemy build() {
        return new FakeEnemy(world, spawnPosition, size, health, speed);
    }

}
