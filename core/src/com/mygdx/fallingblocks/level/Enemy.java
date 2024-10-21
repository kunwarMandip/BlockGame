package com.mygdx.fallingblocks.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy {
    protected Vector2 position;
    protected int health;
    protected float speed;
    protected boolean isActive;

    public Enemy(Vector2 position, int health, float speed) {
        this.position = position;
        this.health = health;
        this.speed = speed;
        this.isActive = true;
    }

    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch batch);

    // Getters and setters
    public Vector2 getPosition() { return position; }
    public void setPosition(Vector2 position) { this.position = position; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}
