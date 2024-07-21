package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Create PUBLIC STATIC hashMap to allow for easier access to textures
 */
public class DefineTexture {

    public static final HashMap<String, Texture> textureHashMap;
    public static final int textureHashMapSize;

    static{
        textureHashMap= new HashMap<>();
        textureHashMap.put("red", new Texture("characters/brown.png"));
        textureHashMap.put("brown", new Texture("characters/brown.png"));
        textureHashMap.put("yellow", new Texture("characters/brown.png"));
        textureHashMapSize= textureHashMap.size();
    }

    /**
     * Given a color in String, return a corresponding String and Texture Pair
     * @param color the color we want in string
     * @return String and Texture Pair
     */
    public static StringTexturePair getTexturePair(String color){
         Texture texture= textureHashMap.get(color);
         if(texture!=null){
             return new StringTexturePair(color, texture);
         }

         return new StringTexturePair("red",new Texture("characters/red.png"));
    }


}
