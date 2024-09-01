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
import com.mygdx.fallingblocks.utilities.GameStateVariables;

import java.util.Locale;

import static com.mygdx.fallingblocks.GlobalStaticVariables.*;

public class HudOverlayScreen implements Disposable {

    private final GameStateVariables gameStateVariables;

    private final Viewport viewport;
    private final SpriteBatch spriteBatch;
    private final OrthographicCamera hudCamera;

    private final Stage stage;
    private Skin skin;
    private Table topTable, bottomTable;

    private Window onDeathWindow;
    private Label score, fpsLabel;
    private TextButton pauseButton;


    public HudOverlayScreen(SpriteBatch spriteBatch, GameStateVariables gameStateVariables){
        this.spriteBatch=spriteBatch;
        this.gameStateVariables=gameStateVariables;
        this.hudCamera= new OrthographicCamera();
        this.viewport= new FitViewport(VIRTUAL_WIDTH/2f, VIRTUAL_HEIGHT/2f, hudCamera);
        this.stage= new Stage(viewport, spriteBatch);

        setSkin();
        setTable();
        addWidgetsToTable();
        onDeathPopUpWindow();
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
        topTable.add(pauseButton).expandX().width(100);
        topTable.add(score).expandX();

        //Lower hud
        fpsLabel = new Label("FPS: 60", skin);
        fpsLabel.setFontScale(2);
        bottomTable.add(fpsLabel);
    }


    private void setPauseButton(){
        this.pauseButton= new TextButton("Pause", skin);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            System.out.println("Clicked");
            gameStateVariables.invertGamePause();
            if (gameStateVariables.isGamePaused()) {
                pauseButton.setText("Resume");
            } else {
                pauseButton.setText("Pause");
            }
            }
        });
    }

    private void onDeathPopUpWindow() {
        onDeathWindow = new Window("Game Over", skin);
        onDeathWindow.setSize(200, 200);
        onDeathWindow.setPosition(
                (viewport.getWorldWidth() - onDeathWindow.getWidth()) / 2,
                (viewport.getWorldHeight() - onDeathWindow.getHeight()) / 2
        );

        // Create a label to show a message
        Label messageLabel = new Label("You Died! Try Again?", skin);
        onDeathWindow.add(messageLabel).center().pad(10);
        onDeathWindow.row();

        // Create a button to restart the game
        TextButton restartButton = new TextButton("Restart", skin);
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Restart Clicked");
                gameStateVariables.setPlayerDead(false);
                onDeathWindow.setVisible(false);
            }
        });

        onDeathWindow.add(restartButton).center().pad(10);
        onDeathWindow.setVisible(false);
        stage.addActor(onDeathWindow);
    }

    public void update(int score){
        this.score.setText(score);
        this.fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
        onDeathWindow.setVisible(gameStateVariables.isPlayerDead());
    }

    //Can be used to set input processor
    public Stage getStage(){
        return stage;
    }

    public void render(){
        spriteBatch.setProjectionMatrix(hudCamera.combined);
        stage.draw();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
