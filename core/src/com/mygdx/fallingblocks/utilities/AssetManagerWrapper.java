package com.mygdx.fallingblocks.utilities;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 *  Sound tapSound = assetManagerWrapper.get(assetManagerWrapper.tapSoundDescriptor)
 */
public class AssetManagerWrapper {

    private final AssetManager assetManager;

    private final AssetDescriptor<Sound> tapSoundDescriptor;
    private final AssetDescriptor<Sound> levelUpSoundDescriptor;
    private final AssetDescriptor<Music> gameMusicDescriptor;
    private final AssetDescriptor<Sound> notificationSoundDescriptor;

    private final AssetDescriptor<Texture> loadingArrow;

    public AssetManagerWrapper(){
        assetManager= new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader());
//        assetManager.setLoader(Texture.class, new );

        loadingArrow= new AssetDescriptor<>("movingArrow/movingArrow.png", Texture.class);

        this.tapSoundDescriptor = new AssetDescriptor<>("sound/tap.wav", Sound.class);
        this.levelUpSoundDescriptor = new AssetDescriptor<>("sound/levelUp.wav", Sound.class);
        this.gameMusicDescriptor = new AssetDescriptor<>("sound/gameMusic.wav", Music.class);
        this.notificationSoundDescriptor = new AssetDescriptor<>("sound/notification.wav", Sound.class);

    }

    public void loadLoadingArrow(){
        assetManager.load(loadingArrow);
    }

    public void loadSounds(){
        assetManager.load(tapSoundDescriptor);
        assetManager.load(levelUpSoundDescriptor);
        assetManager.load(gameMusicDescriptor);
        assetManager.load(notificationSoundDescriptor);
    }


    public <T> T get(AssetDescriptor<T> descriptor) {
        return assetManager.get(descriptor);
    }

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
