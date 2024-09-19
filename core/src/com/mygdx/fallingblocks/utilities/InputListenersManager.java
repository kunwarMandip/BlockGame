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


    public void addInputProcessor(InputProcessor inputProcessor){
        System.out.println("Adding Input Listener");
        inputMultiplexer.addProcessor(inputProcessor);
    }


    public void addInputListener(Stage stage){
        System.out.println("Adding Stage Listener");
        inputMultiplexer.addProcessor(stage);
    }

    public void removeInputProcessor(InputProcessor inputProcessor){
        System.out.println("Removing Input Listener");
        if(inputMultiplexer.getProcessors().contains(inputProcessor, true)){
            System.out.println("Removing Input Listener");
            inputMultiplexer.removeProcessor(inputProcessor);
        }
    }

    public void logActiveProcessors(){
        System.out.println("Active Input Processors:");
        for (InputProcessor processor : inputMultiplexer.getProcessors()) {
            System.out.println(" - " + processor.getClass().getSimpleName());
        }
    }

    public InputMultiplexer getInputMultiplexer(){
        return  this.inputMultiplexer;
    }
}
