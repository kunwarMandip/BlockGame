package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class AssetsLoader {

    public AssetManager assetManager;

    public AssetsLoader(){
        assetManager = new AssetManager();
        loadTmx();
    }

    private void loadTmx(){
        assetManager.setLoader(TiledMap.class, new TmxMapLoader());
        assetManager.load( "map7.tmx", TiledMap.class);
        assetManager.finishLoading();
    }

    public void disposeAssets(){
        assetManager.clear();
        assetManager.dispose();
    }

}