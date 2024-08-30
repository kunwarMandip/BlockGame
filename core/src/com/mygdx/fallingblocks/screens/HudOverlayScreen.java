package com.mygdx.fallingblocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Locale;

import static com.mygdx.fallingblocks.GlobalStaticVariables.*;

public class HudOverlayScreen implements Disposable {

    private final Viewport viewport;
    private final SpriteBatch spriteBatch;
    private final OrthographicCamera hudCamera;

    private final Stage stage;
    private Skin skin;
    private Table topTable, bottomTable;

    private Label score, fpsLabel;
    private TextButton pauseButton;
    private Boolean isGamePaused=false;

    public HudOverlayScreen(SpriteBatch spriteBatch){
        this.spriteBatch=spriteBatch;
        this.hudCamera= new OrthographicCamera();
        this.viewport= new FitViewport(VIRTUAL_WIDTH/2f, VIRTUAL_HEIGHT/2f, hudCamera);
        this.stage= new Stage(viewport, spriteBatch);

        setSkin();
        setTable();
        addWidgetsToTable();
    }

    private void setSkin(){
        this.skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("flat-earth/skin/flat-earth-ui.atlas")));
        skin.load(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"));
    }

    private void setTable(){
        topTable = new Table();
        topTable.top();
        topTable.setFillParent(true);
        topTable.setDebug(true);
        stage.addActor(topTable);

        bottomTable= new Table();
        bottomTable.bottom();
        stage.addActor(bottomTable);
    }

    private void addWidgetsToTable(){
        score = new Label(String.format(Locale.US, "%d", 0), skin);
        score.setFontScale(5.0f);

        setPauseButton();
        topTable.add(pauseButton).expandX();
        topTable.add(score).expandX();

        fpsLabel = new Label("FPS: 60", skin);
        fpsLabel.setFontScale(1.5f);
        bottomTable.add(fpsLabel);
    }

    /**
     * Init the pause button and its function
     */
    private void setPauseButton(){
        this.pauseButton= new TextButton("Pause", skin);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            System.out.println("Clicked");
            isGamePaused = !isGamePaused;
            if (isGamePaused) {
                pauseButton.setText("Resume");
            } else {
                pauseButton.setText("Pause");
            }
            }
        });
    }


    public void update(int score){
        this.score.setText(score);
        fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
    }


    public void render(){
        spriteBatch.setProjectionMatrix(hudCamera.combined);
        stage.draw();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    /**
     * Used to set InputProcessor for hud
     * @return
     */
    public Stage getStage(){
        return stage;
    }

    public Boolean getGamePaused() {
        return isGamePaused;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
