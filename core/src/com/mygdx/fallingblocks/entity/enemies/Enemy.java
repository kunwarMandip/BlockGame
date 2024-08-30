package com.mygdx.fallingblocks.entity.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.fallingblocks.utilities.DynamicTextureCreator;

import static com.mygdx.fallingblocks.GlobalStaticVariables.*;

public class Enemy {

    private Body body;
    private final World world;
    private final EnemyAnimation enemyAnimation;

    private final int colorID;
    private final float waitTimer;
    private float waitTimeCounter=0;
    private final Vector2 movementSpeed, spawnLocation;

    public boolean hasEnemySpawned, isEnemyToBeRemoved, isFriendly;

    public Enemy(World world,
                 DynamicTextureCreator solidColorCreator,
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
//        enemyAnimation.dispose();
        if(body!=null){
            world.destroyBody(body);
        }
    }
}
