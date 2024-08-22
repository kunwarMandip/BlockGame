package com.mygdx.fallingblocks.utilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * Define set of colors to create solid textures with
 */
public class SolidColorCreator {

    private int playerColorID=0;
    private final Map<Integer, Texture> colors= new HashMap<>();

    public SolidColorCreator(){
        String[] colorArray={"#FF0000", "#FF5733", "#33FF57", "#FF33A6",
                "#33FFF3", "#FFDB33", "#8A33FF", "#FF8633", "#33FF8A"};
        for(int i =0; i<colorArray.length; i++){
            defineColors(i, colorArray[i]);
            System.out.println("Colors: "+ i + " : " + colorArray[i]);
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
