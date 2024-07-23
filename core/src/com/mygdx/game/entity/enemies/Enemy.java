package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


import static com.mygdx.game.StaticVariables.*;


public class Enemy {

    private Body body;
    private final World world;
    private final EnemyManager enemyManager;
    private final EnemyAnimation enemyAnimation;

    private final Vector2 movementSpeed, spawnLocation;
    private final float waitTime;
    private float timeCounter;
    private boolean hasEnemySpawned;

    public Enemy(EnemyManager enemyManager, World world, String textureColor, Vector2 spawnLocation,  Vector2 speed, float waitTime){
        this.enemyManager=enemyManager;
        this.world=world;
        this.movementSpeed=speed;
        this.waitTime=0;
        this.timeCounter=0;
        this.spawnLocation=spawnLocation;
        this.hasEnemySpawned=false;
        enemyAnimation= new EnemyAnimation(textureColor);

    }

    /**
     * Update the timeCounter until it reaches waitTime
     * when reached, create the BOX2D body, update it to enemyManager
     * @param delta time passed since last render(not even sure atp)
     */
    public void update(float delta){
        if(!hasEnemySpawned){
            updateTimer(delta);
            return;
        }
        body.setLinearVelocity(movementSpeed);

    }

    private void updateTimer(float delta){
        if(timeCounter>=waitTime){
            defineEnemy(spawnLocation);
            enemyManager.getCurrentEnemies().add(this);
            enemyManager.getEnemiesToAdd().removeValue(this, true);
            hasEnemySpawned=true;
        }
        timeCounter+=delta;
    }


    public void draw(SpriteBatch spriteBatch) {
        Vector2 bodyPosition=body.getPosition();
        enemyAnimation.draw(new Vector2(bodyPosition.x, bodyPosition.y), spriteBatch);
    }

    private void defineEnemy(Vector2 spawnLocation) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(spawnLocation.x, spawnLocation.y);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);

        //Creates shape for the body
        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(3, 3);

        //Creates fixture definition and applies to body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangleShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        fixtureDef.filter.categoryBits = CATEGORY_ENEMY;
        // Collides with everything except walls
        fixtureDef.filter.maskBits =~(CATEGORY_WALL | CATEGORY_ENEMY);

        body.createFixture(fixtureDef).setUserData(this);
        rectangleShape.dispose();
    }

    public void dispose() {
        world.destroyBody(body);
        enemyAnimation.dispose();
    }
}
