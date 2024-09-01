package com.mygdx.fallingblocks.utilities;

import com.badlogic.gdx.assets.AssetDescriptor;
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

/**
 *  Sound tapSound = assetManagerWrapper.get(assetManagerWrapper.tapSoundDescriptor)
 */
public class AssetManagerWrapper {

    private final AssetManager assetManager;

    private final AssetDescriptor<Sound> tapSoundDescriptor;
    private final AssetDescriptor<Sound> levelUpSoundDescriptor;
    private final AssetDescriptor<Music> gameMusicDescriptor;
    private final AssetDescriptor<Sound> notificationSoundDescriptor;

//    private final AssetDescriptor<TiledMap> imagesMapDescriptor;
//    private final AssetDescriptor<TiledMap> map1Descriptor;
//    private final AssetDescriptor<TiledMap> map3Descriptor;
//
//    private final AssetDescriptor<Pixmap> largeImageDescriptor;

    public AssetManagerWrapper(){
        this.assetManager= new AssetManager();
        this.assetManager.setLoader(TiledMap.class, new TmxMapLoader());

        this.tapSoundDescriptor = new AssetDescriptor<>("sound/tap.wav", Sound.class);
        this.levelUpSoundDescriptor = new AssetDescriptor<>("sound/levelUp.wav", Sound.class);
        this.gameMusicDescriptor = new AssetDescriptor<>("sound/gameMusic.wav", Music.class);
        this.notificationSoundDescriptor = new AssetDescriptor<>("sound/notification.wav", Sound.class);


//        this.imagesMapDescriptor = new AssetDescriptor<>("map/images.tmx", TiledMap.class);
//        this.map1Descriptor = new AssetDescriptor<>("map/map1.tmx", TiledMap.class);
//        this.map3Descriptor = new AssetDescriptor<>("map/map3.tmx", TiledMap.class);

//        this.largeImageDescriptor = new AssetDescriptor<>("map/images/largeImage.png", Pixmap.class);
    }

    public void loadAssets(){
        assetManager.load(tapSoundDescriptor);
        assetManager.load(levelUpSoundDescriptor);
        assetManager.load(gameMusicDescriptor);
        assetManager.load(notificationSoundDescriptor);

//        assetManager.load(imagesMapDescriptor);
//        assetManager.load(map1Descriptor);
//        assetManager.load(map3Descriptor);

//        assetManager.load(largeImageDescriptor);
    }



    /**
     * Retrieve asset using AssetDescriptor
     * @param descriptor
     * @return
     * @param <T>
     */
    public <T> T get(AssetDescriptor<T> descriptor) {
        return assetManager.get(descriptor);
    }


    /**
     * Retrieve asset using file name and type
     * @param fileName
     * @param type
     * @return
     * @param <T>
     */
    public <T> T get(String fileName, Class<T> type) {
        return assetManager.get(fileName, type);
    }

    public float getAssetManagerProgress(){
        return assetManager.getProgress();
    }

    public boolean getAssetManagerUpdate(){
        return assetManager.update();
    }

    public void dispose() {
        assetManager.dispose();
    }
}
