package com.mygdx.fallingblocks.utilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

import static com.mygdx.fallingblocks.GlobalStaticVariables.COLOR_ARRAY;

/**
 * Creates a list of solid dynamic textures which can be accessed via a hashMap
 */
public class SolidTextureCreator {

    public static int playerColorID=0;
    private final Map<Integer, Texture> colors= new HashMap<>();

    public SolidTextureCreator(){
        for(int i =0; i<COLOR_ARRAY.length; i++){
            defineColors(i, COLOR_ARRAY[i]);
            System.out.println("Colors: "+ i + " : " + COLOR_ARRAY[i]);
        }
        System.out.println(colors.size());
    }

    public void defineColors(int colorNumber, String color){
        Pixmap pixmap= new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.valueOf(color));
        pixmap.fill();
        Texture texture= new Texture(pixmap);
        colors.put(colorNumber, texture);
        pixmap.dispose();
    }


    public Texture getColor(int colorNumber, boolean isPlayer){
        if(isPlayer){
            this.playerColorID=colorNumber;
        }
        return colors.get(colorNumber);
    }

    public int getPlayerColorID(){return playerColorID;}

    public int getSize(){return colors.size()-1;}

    public void dispose(){
        for(Texture texture: colors.values()){
            texture.dispose();
        }
        colors.clear();
    }
}
