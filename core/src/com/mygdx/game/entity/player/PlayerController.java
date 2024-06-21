package com.mygdx.game.entity.player;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.main.screen.MainDisplay;

/**
 * Responsible for handling the controls of the Player body.
 */
public class PlayerController implements InputProcessor {

    private final Body playerBody;
    private OrthographicCamera gameCamera;

    private float initialX;
    private float initialY;
    private boolean isDragging;


    public PlayerController(Body playerBody, OrthographicCamera gameCamera){
        this.playerBody=playerBody;
        this.gameCamera=gameCamera;
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Screen Touched Down");
        Vector3 worldCoordinates = gameCamera.unproject(new Vector3(screenX, screenY, 0));
        initialX = worldCoordinates.x;
        initialY = worldCoordinates.y;
        isDragging = true;
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

        if (isDragging) {
            System.out.println("Screen Dragging");
            // Convert screen coordinates to world coordinates
            Vector3 worldCoordinates = gameCamera.unproject(new Vector3(screenX, screenY, 0));

            float deltaX = worldCoordinates.x - initialX;
            float deltaY = worldCoordinates.y - initialY;

            // Calculate the proportional movement
            float movementX = deltaX / (MainDisplay.VIRTUAL_WIDTH / 3); // Scale the movement

            // Move the Box2D body
            playerBody.setTransform(playerBody.getPosition().x + movementX, playerBody.getPosition().y, playerBody.getAngle());

            // Update initial position to the current position
            initialX = worldCoordinates.x;
            initialY = worldCoordinates.y;
        }
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
