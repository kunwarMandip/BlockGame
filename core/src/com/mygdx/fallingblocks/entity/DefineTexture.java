package com.mygdx.fallingblocks.entity;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Create a PUBLIC STATIC Hashmap of String and texture to be accessed easily
 */
public class DefineTexture {

    public static final HashMap<String, Texture> textureHashMap;
    public static final int textureHashMapSize;
    public static String[] textureNames={"red", "yellow", "purple"};

    static{
        textureHashMap= new HashMap<>();
        textureHashMap.put("red", new Texture("characters/red.png"));
        textureHashMap.put("purple", new Texture("characters/purple.png"));
        textureHashMap.put("yellow", new Texture("characters/yellow.png"));
        textureHashMapSize= textureHashMap.size();
    }


    public static Texture getTexture(String color){
        return textureHashMap.get(color);
    }

    public void dispose(){
        textureHashMap.clear();
    }


}
