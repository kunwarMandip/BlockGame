package com.mygdx.game;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Assets {

    public static final AssetManager manager = new AssetManager();

    public static final AssetDescriptor<TiledMap> map1 = new AssetDescriptor<TiledMap>("tiledMap/startingMap.tmx", TiledMap.class);


    public static void load() {
        manager.setLoader(TiledMap.class, new TmxMapLoader());
        manager.load(map1);
    }

    public static void dispose(){
        manager.dispose();
    }


}