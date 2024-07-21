package com.mygdx.game.map.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.StaticVariables;

public class TileObjects {

    protected World world;
    protected Body body;
    protected Rectangle bounds;
    protected TiledMap tiledMap;
    protected MapObject mapObject;
    protected Fixture fixture;

    public TileObjects(World world, TiledMap tiledMap, MapObject object) {
        this.mapObject = object;
        this.world = world;
        this.tiledMap = tiledMap;
        this.bounds = ((RectangleMapObject) object).getRectangle();

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / StaticVariables.PPM,
                (bounds.getY() + bounds.getHeight() / 2) / StaticVariables.PPM);

        body = world.createBody(bodyDef);

        shape.setAsBox(bounds.getWidth() / 2 / StaticVariables.PPM,
                bounds.getHeight() / 2 / StaticVariables.PPM);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);

    }


    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }



//    public TiledMapTileLayer.Cell getCell() {
//        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
//        return layer.getCell((int) (body.getPosition().x * MarioBros.PPM / 16),
//                (int) (body.getPosition().y * MarioBros.PPM / 16));
//    }

}

