package com.mygdx.fallingblocks.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.physics.box2d.World;


public class TiledMapManager {

    private final TiledMap tiledMap;
    private final TiledMapImageLayer backGroundTiledLayer;
    private final TiledMapImageLayer foreGroundTiledLayer;

    public TiledMapManager(World world, TiledMap tiledMap){
//        this.tiledMap= new TmxMapLoader().load("map/tiledMap.tmx");
        this.tiledMap=tiledMap;
        this.backGroundTiledLayer = (TiledMapImageLayer) tiledMap.getLayers().get("background");
        this.foreGroundTiledLayer = (TiledMapImageLayer) tiledMap.getLayers().get("foreground");
        setImages();

        new LoadMapObjects(world, tiledMap);
    }


    public void update(int currentScore, int lastScore){
        if(currentScore>lastScore){
            setImages();
        }
    }

    /**
     * Change image in the TiledMap to different images
     */
    private void setImages(){
        Texture background = new Texture(Gdx.files.internal("map/images/map2/background.png"));
        backGroundTiledLayer.setTextureRegion(new TextureRegion(background));

        Texture foreGround = new Texture(Gdx.files.internal("map/images/map2/upper.png"));
        foreGroundTiledLayer.setTextureRegion(new TextureRegion(foreGround));
    }


    public TiledMap getTiledMap(){
        return tiledMap;
    }
}
