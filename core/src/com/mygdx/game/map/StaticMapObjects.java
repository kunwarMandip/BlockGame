package com.mygdx.game.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.GlobalVariables.*;


public class StaticMapObjects {

    private final Array<Body> staticMapBodies;

    public StaticMapObjects(World world, TiledMap map){
        System.out.println("Init StaticMapObjects.class");
        staticMapBodies= new Array<>();

        STATIC_TILED_MAP_OBJECTS[] staticMapObjectsArrayTypes = STATIC_TILED_MAP_OBJECTS.values();
        for (STATIC_TILED_MAP_OBJECTS obj : staticMapObjectsArrayTypes) {
            System.out.println("Checking new Object: "+ obj);
            loadStaticBodies(world, map, obj.toString());
        }

    }


    private void loadStaticBodies(World world, TiledMap map, String layerName) {

        if(!CHECK_LAYER(map, layerName)){return;}

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        // Get the target layer
        MapLayer targetLayer = map.getLayers().get(layerName);

        for (RectangleMapObject object : targetLayer.getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody; // Static Map bodies

            float positionX = (rect.getX() +rect.getWidth()/2) /PPM;
            float positionY = (rect.getY() + rect.getHeight()/2) /PPM;
            bodyDef.position.set(positionX, positionY);

            body = world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth() / 2 / PPM, rect.getHeight() / 2 / PPM);

            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = CATEGORY_WALL;

            body.createFixture(fixtureDef).setUserData(layerName);

            staticMapBodies.add(body);
        }


    }





}
