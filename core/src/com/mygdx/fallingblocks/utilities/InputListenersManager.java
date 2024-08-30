package com.mygdx.fallingblocks.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InputListenersManager {

    private final InputMultiplexer inputMultiplexer;

    public InputListenersManager(){
        this.inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }


    public InputMultiplexer getInputMultiplexer(){
        return inputMultiplexer;
    }
    public void setInputProcessor(InputProcessor inputProcessor){
        inputMultiplexer.addProcessor(inputProcessor);
    }

    public void addInputListener(Stage stage){
        inputMultiplexer.addProcessor(stage);
    }

    public void removeInputProcessor(InputProcessor inputProcessor){
        if(inputMultiplexer.getProcessors().contains(inputProcessor, true)){
            inputMultiplexer.removeProcessor(inputProcessor);
        }
    }
}
