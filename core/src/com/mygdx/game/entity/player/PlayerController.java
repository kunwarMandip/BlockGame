package com.mygdx.game.entity.player;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.FallingBlocks;

/**
 * Responsible for handling the controls of the Player body.
 */
public class PlayerController implements InputProcessor {

    private final Body playerBody;
    private OrthographicCamera gameCamera;

    private boolean isDragging;
    private float glideDistance;

    /**
     * Used to hold initial Position when the user touches screen
     * can be used to check if the user dragged left or right
     */
    private Vector2 initalPosition = new Vector2();


    public PlayerController(Body playerBody, OrthographicCamera gameCamera){
        this.playerBody=playerBody;
        this.gameCamera=gameCamera;
        calculateDistanceToMove();
    }

    /**
     * When user scrolls, there's only a CERTAIN AMOUNT THE USER CAN MOVE.
     * Sets how much player can glide LEFT OR RIGHT (HORIZONTALLY) with every drag.
     */
    private void calculateDistanceToMove(){

        float screenWidth= FallingBlocks.VIRTUAL_WIDTH/FallingBlocks.PPM;
        float screenHeight= FallingBlocks.VIRTUAL_HEIGHT/FallingBlocks.PPM;

        glideDistance = screenWidth/4;
        System.out.println("Screen Size: " + screenWidth + screenHeight);

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Gets the initial position of the playerBody when user touches screen
     * Allows us to check if the user than dragged left or right
     * @param screenX The x coordinate, origin is in the upper left corner
     * @param screenY The y coordinate, origin is in the upper left corner
     * @param pointer the pointer for the event.
     * @param button the button
     * @return true if handled, false if it wasn't
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        System.out.println("Initial touch position: (" + screenX + ", " + screenY + ")");


        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("Screen Touched Up");
        isDragging = false;
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {


        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
