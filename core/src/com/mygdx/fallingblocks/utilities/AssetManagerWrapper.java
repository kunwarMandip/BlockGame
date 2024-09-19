package com.mygdx.fallingblocks.utilities;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.awt.*;

public class AssetManagerWrapper {

    private final AssetManager assetManager;

    public AssetManagerWrapper(){
        this.assetManager= new AssetManager();
        this.assetManager.setLoader(TiledMap.class, new TmxMapLoader());
    }

    public void loadAssets(){
        assetManager.load("sound/tap.wav", Sound.class);
        assetManager.load("sound/levelUp.wav", Sound.class);
        assetManager.load("sound/gameMusic.wav", Music.class);
        assetManager.load("sound/notification.wav", Sound.class);

        assetManager.load("map/images.tmx", TiledMap.class);
        assetManager.load("map/map1.tmx", TiledMap.class);
        assetManager.load("map/map3.tmx", TiledMap.class);

        assetManager.load("map/images/largeImage.png", Pixmap.class);

    }

    public AssetManager getAssetManager(){
        return assetManager;
    }

    public boolean getUpdate() {
        return assetManager.update(); // Returns true if all assets are loaded
    }

    public Pixmap getImage(){return assetManager.get("map/images/largeImage.png", Pixmap.class);}

    public TiledMap getMap1() {
        return assetManager.get("map/images.tmx", TiledMap.class);
    }

    public TiledMap getMap2() {
        return assetManager.get("map/map1.tmx", TiledMap.class);
    }

    public TiledMap getMap3() {
        return assetManager.get("map/map3.tmx", TiledMap.class);
    }

    public Sound getTapSound() {
        return assetManager.get("sound/tap.wav", Sound.class);
    }

    public Sound getLevelUpSound() {
        return assetManager.get("sound/levelUp.wav", Sound.class);
    }

    public Music getGameMusic() {
        return assetManager.get("sound/gameMusic.wav", Music.class);
    }

    public Sound getNotificationSound() {
        return assetManager.get("sound/notification.wav", Sound.class);
    }

    public void dispose() {
        assetManager.dispose();
    }
}
