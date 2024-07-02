package com.mygdx.game.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GlobalVariables;


/**
 * This class is responsible for loading the map onto the world.
 * Should only be init once and in the mainDisplay class
 */
public class MapLoader {

    private Array<Body> mapBodies;

    public MapLoader(){
        mapBodies= new Array<>();
    }

    public Array<Body> getMapBodies(){
        return mapBodies;
    }

    public void destroyMap(World world){
        for(Body body: mapBodies){
            world.destroyBody(body);
        }
    }

    public void mapWorld(World world, TiledMap map){

        BodyDef bodyDef =new BodyDef();
        PolygonShape shape= new PolygonShape();
        FixtureDef fixtureDef= new FixtureDef();
        Body body;

        for (RectangleMapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = object.getRectangle();

            //static Map bodies
            bodyDef.type=BodyDef.BodyType.KinematicBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / GlobalVariables.PPM,
                    (rect.getY() + rect.getHeight() / 2) / GlobalVariables.PPM);

            body=world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth() / 2 / GlobalVariables.PPM,
                    rect.getHeight() / 2 / GlobalVariables.PPM);
            fixtureDef.shape= shape;
            body.createFixture(fixtureDef);

            mapBodies.add(body);
        }
    }



}
