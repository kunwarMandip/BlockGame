package com.mygdx.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.GlobalVariables.PPM;
import static com.mygdx.game.GlobalVariables.checkLayer;

/**
 * Maps all static objects in the TiledMap
 * User data for body is set to string as objects are static
 * if user data is needed, make it dynamic Object type
 */
public class StaticMapObjects {

    //holds all staticMapObjects
    private final Array<Body> staticMapBodies;

    public StaticMapObjects(World world, TiledMap map){
        System.out.println("Init StaticMapObjects.class");
        staticMapBodies= new Array<>();

        StaticMapObjectTypes[] staticMapObjectsArrayTypes = StaticMapObjectTypes.values();
        for (StaticMapObjectTypes obj : staticMapObjectsArrayTypes) {
            System.out.println("Checking new Object: "+ obj);
            loadStaticBodies(world, map, obj.toString());
        }

    }


    private void loadStaticBodies(World world, TiledMap map, String layerName) {

        if(!checkLayer(map, layerName)){return;}

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        // Get the target layer
        MapLayer targetLayer = map.getLayers().get(layerName);

        for (RectangleMapObject object : targetLayer.getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();

            // Static Map bodies
            bodyDef.type = BodyDef.BodyType.KinematicBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / PPM,
                    (rect.getY() + rect.getHeight() / 2) / PPM);

            body = world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth() / 2 / PPM, rect.getHeight() / 2 / PPM);

            fixtureDef.shape = shape;
            body.createFixture(fixtureDef).setUserData(layerName);

            staticMapBodies.add(body);
        }
    }



    public void draw(SpriteBatch spriteBatch){
    }


}
