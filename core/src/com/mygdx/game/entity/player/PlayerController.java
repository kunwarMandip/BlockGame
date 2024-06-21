package com.mygdx.game.entity.player;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Responsible for handling the controls of the Player body.
 */
public class PlayerController implements InputProcessor {

    private Player player;
    private final Body playerBody;
    public PlayerController(Player player){
        this.player= player;
        playerBody=player.body;
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
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("Screen Touched Up");
        playerBody.setTransform(5, 10, playerBody.getAngle());
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        System.out.println("Screen Dragged");
//        Vector2 touchPos = new Vector2(screenX, screenY);
//        viewport.unproject(touchPos);  // Convert screen coordinates to world coordinates
//
//        // Move the player body
//        playerBody.setTransform(touchPos.x, playerBody.getPosition().y, playerBody.getAngle());

        return true;  // Return true to indicate the event was handled
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
