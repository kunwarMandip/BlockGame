package com.mygdx.game.entity.player;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * For Android only
 */
public class GesturePlayerController implements GestureDetector.GestureListener {

    private final Body playerBody;

    public GesturePlayerController(Body playerBody, OrthographicCamera gameCamera){
        this.playerBody=playerBody;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > 0) {
                playerBody.setLinearVelocity(new Vector2(10f, 0));
                System.out.println("Fling Right");
            } else {
                playerBody.setLinearVelocity(new Vector2(-10f, 0));
//                playerBody.applyLinearImpulse(new Vector2(-10f, 0), playerBody.getWorldCenter(), true);
                System.out.println("Fling Left");
            }

        } else {
            if (velocityY > 0) {
                playerBody.setLinearVelocity(new Vector2(0f, -10f));
//                playerBody.applyLinearImpulse(new Vector2(0f, -10f), playerBody.getWorldCenter(), true);
                System.out.println("Fling Down");
            } else {
                playerBody.setLinearVelocity(new Vector2(0f, 10f));
//                playerBody.applyLinearImpulse(new Vector2(0f, 10f), playerBody.getWorldCenter(), true);
                System.out.println("Fling Up");
            }

        }
        return false;

    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
