package com.mygdx.game.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GlobalVariables;

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
            System.out.println("Checking new one: "+ obj);
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
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / GlobalVariables.PPM,
                    (rect.getY() + rect.getHeight() / 2) / GlobalVariables.PPM);

            body = world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth() / 2 / GlobalVariables.PPM, rect.getHeight() / 2 / GlobalVariables.PPM);

            fixtureDef.shape = shape;
            body.createFixture(fixtureDef).setUserData(layerName);

            staticMapBodies.add(body);
        }
    }

    /**
     * //Given a String, check if a corresponding layer can be found
     * @param map the map file to search within
     * @param layerName the name of the layer
     * @return true if found, false if not
     */
    public static boolean checkLayer(TiledMap map, String layerName){
        for (MapLayer layer : map.getLayers()) {
            if (layer.getName().equalsIgnoreCase(layerName)) {
                System.out.println("Map Layer: "+ layerName+ " found.");
                return true;
            }
        }
        System.out.println("Map Layer: "+ layerName +" not found.");
        return  false;
    }

    public Array<Body> getStaticMapBodies(){
        return staticMapBodies;
    }

}
