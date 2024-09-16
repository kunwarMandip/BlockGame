package com.mygdx.fallingblocks.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.fallingblocks.FallingBlocks;
import com.mygdx.fallingblocks.utilities.AssetManagerWrapper;



import static com.mygdx.fallingblocks.GlobalStaticVariables.*;


public class LoadingScreen implements Screen {

    private final FallingBlocks fallingBlocks;
    private final SpriteBatch spriteBatch;
    private final AssetManagerWrapper assetManagerWrapper;

    private Viewport viewport;
    private OrthographicCamera orthographicCamera;

    //Set animations
    private float stateTime;
    private Animation<TextureRegion> movingArrowAnimation;


    public LoadingScreen(FallingBlocks fallingBlocks, AssetManagerWrapper assetManagerWrapper){
        this.fallingBlocks=fallingBlocks;
        this.assetManagerWrapper=assetManagerWrapper;
        this.spriteBatch=fallingBlocks.getSpriteBatch();
    }

    @Override
    public void show() {
        orthographicCamera= new OrthographicCamera();
        viewport = new FitViewport(VIRTUAL_WIDTH/PPM, VIRTUAL_HEIGHT/PPM, orthographicCamera);
        viewport.apply();
        orthographicCamera.position.set(viewport.getWorldWidth() / 2f, viewport.getWorldHeight() / 2f, 0);
        orthographicCamera.update();

        setAnimation();
        assetManagerWrapper.loadSounds();
    }


    private void setAnimation() {
        stateTime = 0f;
        int FRAME_ROWS=3, FRAME_COLS=20;
        Texture texture = new Texture("movingArrow/movingArrow.png");

        TextureRegion[][] tmp = TextureRegion.split(texture,
                texture.getWidth() / FRAME_COLS,
                texture.getHeight() / FRAME_ROWS);

        TextureRegion[] movingArrow = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                movingArrow[index++] = tmp[i][j];
            }
        }
        movingArrowAnimation = new Animation<>(0.016f, movingArrow);
    }

    private void update(float delta){
        if(assetManagerWrapper.getAssetManagerUpdate()) {
            fallingBlocks.setScreen(fallingBlocks.getGameScreen());
        }

        float progress = assetManagerWrapper.getAssetManagerProgress();
        System.out.println("Loading progress: " + (progress * 100) + "% | Delta: " + delta);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthographicCamera.update();
        stateTime+= delta;
        TextureRegion currentFrame = movingArrowAnimation.getKeyFrame(stateTime, true);

        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame,
                viewport.getWorldWidth() / 2 - currentFrame.getRegionWidth() / 2f,
                viewport.getWorldHeight() / 2 - currentFrame.getRegionHeight() / 2f);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
