package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.FallingBlocks;


import static com.mygdx.game.GlobalVariables.*;


public class Enemy {

    private final EnemyManager enemyManager;
    private Body body;
    private final World world;
    private final EnemyAnimation enemyAnimation;


    private final Vector2 movementSpeed, spawnLocation;
    private final float waitTime;
    private float timeCounter;
    private boolean hasEnemySpawned;


    public Enemy(EnemyManager enemyManager, World world, Vector2 spawnLocation, Vector2 speed, float waitTime){
        this.enemyManager=enemyManager;
        this.world=world;
        this.movementSpeed=speed;
        this.waitTime=waitTime;
        this.timeCounter=0;
        this.spawnLocation=spawnLocation;
        this.hasEnemySpawned=false;
        enemyAnimation= new EnemyAnimation(new Texture("box.png"));
    }

    public void update(float delta){
        if(!hasEnemySpawned){
            if(timeCounter>=waitTime){
                defineEnemy(spawnLocation);
                enemyManager.getCurrentEnemies().add(this);
                enemyManager.getEnemiesToAdd().removeValue(this, true);
                hasEnemySpawned=true;
            }
            timeCounter+=delta;
            System.out.println("Time: "+timeCounter);
        }else{
            body.setLinearVelocity(movementSpeed);
        }
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
        rectangleShape.setAsBox(2, 2);

        //Creates fixture definition and applies to body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangleShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        fixtureDef.filter.categoryBits = CATEGORY_ENEMY;
        fixtureDef.filter.maskBits =~(CATEGORY_WALL | CATEGORY_ENEMY); // Collides with everything except walls

        body.createFixture(fixtureDef).setUserData(this);
        rectangleShape.dispose();

    }

    public void dispose() {
        world.destroyBody(body);
        enemyAnimation.dispose();
    }
}
