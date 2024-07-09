package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.FallingBlocks;


import static com.mygdx.game.GlobalVariables.*;
import static com.mygdx.game.map.SpawnArea.getRandomSpawnPosition;


public class Enemy {

    private Body body;
    private final World world;
    private final EnemyAnimation enemyAnimation;

    private int waitTime = 0;
    private boolean finishedWaiting = false;


    public Enemy(World world, Vector2 spawnLocation, Vector2 speed){
        this.world=world;
        defineEnemy(spawnLocation, speed);
        enemyAnimation= new EnemyAnimation();

    }


    /**
     * Update gameBodies and texture
     */
    public void update() {
        if (!finishedWaiting) {
            if (waitTime > 100) {
                body.setType(BodyDef.BodyType.DynamicBody);
                finishedWaiting = true;
            }
            waitTime++;
        }
        body.applyLinearImpulse(new Vector2(0, 10f), body.getWorldCenter(), true);
        FallingBlocks.score++;
    }


    public void draw(SpriteBatch spriteBatch) {
        Vector2 bodyPosition=body.getPosition();
        enemyAnimation.draw(new Vector2(bodyPosition.x, bodyPosition.y), spriteBatch);
    }


    public void dispose() {
        world.destroyBody(body);
        enemyAnimation.dispose();
    }




    private void defineEnemy(Vector2 spawnLocation, Vector2 speed) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

//        bodyDef.position.set(spawnLocationX, 13);
        System.out.println("Enemy Location: " + getRandomSpawnPosition().x);
        Vector2 enemyPos=getRandomSpawnPosition();
        float x=enemyPos.x/100;
        float y=enemyPos.y/100;

        bodyDef.position.set(spawnLocation);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);

        //Creates shape for the body
        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(5, 5);

        //Creates fixture definition and applies to body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangleShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        fixtureDef.filter.categoryBits = CATEGORY_ENEMY;
        fixtureDef.filter.maskBits = ~CATEGORY_WALL; // Collides with everything except walls


        body.createFixture(fixtureDef).setUserData(this);
        rectangleShape.dispose();


    }
}
