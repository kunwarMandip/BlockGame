package com.mygdx.game.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.FallingBlocks;

/**
 * Responsible for handling the controls of the Player body.
 */
public class PlayerController implements InputProcessor {

    /**
     * For Applying Force, choose between these two:
     * APPLY LINEAR IMPULSE, SET LINEAR VELOCITY
     * Apply linear impulse looks better
     */

    private final Body playerBody;
    private OrthographicCamera gameCamera;

    private float startingPositionX;
    private float startingPositionY;
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
        startingPositionX = screenX;
        startingPositionY = screenY;
        isDragging = true;
        System.out.println("Initial touch position: (" + screenX + ", " + screenY + ")");

        //Just checking screen coordinates
        Vector3 screenCords = new Vector3(screenX, screenY, 0);
        gameCamera.unproject(screenCords);
        System.out.println("World Coordinates: (" + screenCords.x + ", " + screenCords.y + ")");

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
            // User dragged screen left
            if (startingPositionX > screenX) {
                System.out.println("Screen dragging left");
                playerBody.applyLinearImpulse(new Vector2(-10f, 0), playerBody.getWorldCenter(), true);
                return true;
            }
            // User dragged screen right
            if (startingPositionX < screenX) {
                System.out.println("Screen dragging right");
                playerBody.applyLinearImpulse(new Vector2(10f, 0), playerBody.getWorldCenter(), true);
                return true;
            }
            // User dragged screen up
            if (startingPositionY > screenY) {
                System.out.println("Screen dragging up");
                playerBody.applyLinearImpulse(new Vector2(0, 10f), playerBody.getWorldCenter(), true);
                return true;
            }
            // User dragged screen down
            if (startingPositionY < screenY) {
                System.out.println("Screen dragging down");
                playerBody.applyLinearImpulse(new Vector2(0, -10f), playerBody.getWorldCenter(), true);
                return true;
            }
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
