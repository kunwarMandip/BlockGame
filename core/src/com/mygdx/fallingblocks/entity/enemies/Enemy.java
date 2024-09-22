package com.mygdx.fallingblocks.entity.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.fallingblocks.utilities.SolidTextureCreator;

import static com.mygdx.fallingblocks.GlobalStaticVariables.*;

public class Enemy {

    private Body body;
    private World world;
    private final EnemyAnimation enemyAnimation;

    private int colorID;
    private float waitTimer;
    private float waitTimeCounter=0;
    private Vector2 movementSpeed, spawnLocation;

    public boolean hasEnemySpawned, isEnemyToBeRemoved, isFriendly;

    public Enemy(World world,
                 SolidTextureCreator solidColorCreator,
                 int colorID,
                 Vector2 spawnLocation,
                 Vector2 movementSpeed,
                 float waitTimer){
        this.world=world;
        this.colorID=colorID;
        this.spawnLocation=spawnLocation;
        this.movementSpeed=movementSpeed;
        this.waitTimer=waitTimer;
        enemyAnimation= new EnemyAnimation(solidColorCreator.getColor(colorID, false));
    }


    public Enemy(World world, int colorID, SolidTextureCreator solidTextureCreator, Vector4 enemySpawnAndSpeedInfo, Vector2 bodyDimension){
        this.colorID=colorID;

        Vector2 enemySpeed= new Vector2(enemySpawnAndSpeedInfo.z, enemySpawnAndSpeedInfo.w);
        Vector2 spawnLocation= new Vector2(enemySpawnAndSpeedInfo.x, enemySpawnAndSpeedInfo.y);
        Vector2 finalBodyDimension = (bodyDimension != null) ? bodyDimension : new Vector2(3, 3);

        defaultBody(world, spawnLocation, finalBodyDimension);
        enemyAnimation= new EnemyAnimation(solidTextureCreator.getColor(colorID, false));
        body.setLinearVelocity(enemySpeed);
    }



    public void defaultBody(World world, Vector2 spawnLocation, Vector2 bodyDimensions) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(spawnLocation.x, spawnLocation.y);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);

        //Creates shape for the body
        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(bodyDimensions.x, bodyDimensions.y);

        //Creates fixture definition and applies to body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangleShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        // Collides with everything except walls
        fixtureDef.filter.categoryBits = CATEGORY_ENEMY;
        fixtureDef.filter.maskBits =~(CATEGORY_WALL | CATEGORY_ENEMY);
        body.createFixture(fixtureDef).setUserData(this);
        rectangleShape.dispose();
    }


    /**
     * Check to waitTimeCounter to see if it has reached the waitTimer
     * if true, spawn enemy, else increment waitTimeCounter;
     * @param deltaTime time since last render
     */
    public void update(float deltaTime){
        //Check if enemy has been created
        if(hasEnemySpawned){
            body.setLinearVelocity(movementSpeed);
            return;
        }

        if(waitTimeCounter >=waitTimer){
            defineEnemy();
            this.hasEnemySpawned=true;
            return;
        }
        waitTimeCounter +=deltaTime;
    }

    public void draw(SpriteBatch spriteBatch) {
        if(hasEnemySpawned){
            Vector2 bodyPosition=body.getPosition();
            enemyAnimation.draw(new Vector2(bodyPosition.x, bodyPosition.y), spriteBatch);
        }
    }

    private void defineEnemy() {
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

    public int getColorID(){return this.colorID;}

    public void dispose() {
        if(body!=null){
            world.destroyBody(body);
        }

    }

    public void dispose(World world){
        if(body==null){return;}
        world.destroyBody(body);
    }
}
