package com.mygdx.fallingblocks.entity.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.fallingblocks.GlobalStaticVariables.CATEGORY_ENEMY;
import static com.mygdx.fallingblocks.GlobalStaticVariables.CATEGORY_WALL;

public class NewEnemy {

    private Body body;
    private final EnemyAnimation enemyAnimation;

    public NewEnemy(World world, Texture texture, Vector2 spawnLocation, Vector2 movementSpeed){
        Vector2 bodyDimension= new Vector2(3, 3);
        defaultBody(world, spawnLocation, bodyDimension);
        enemyAnimation= new EnemyAnimation(texture);
        body.setLinearVelocity(movementSpeed);
    }


    public NewEnemy(World world, Texture texture, Vector2 spawnLocation, Vector2 bodyDimension, Vector2 movementSpeed){
        Vector2 finalBodyDimension = (bodyDimension != null) ? bodyDimension : new Vector2(3, 3);
        defaultBody(world, spawnLocation, finalBodyDimension);
        enemyAnimation= new EnemyAnimation(texture);
        body.setLinearVelocity(movementSpeed);
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



    public void draw(SpriteBatch spriteBatch, Vector2 playerPosition) {
        enemyAnimation.draw(spriteBatch, playerPosition);
    }


}
