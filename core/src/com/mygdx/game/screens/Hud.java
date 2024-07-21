package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameStateVariables;
import com.mygdx.game.StaticVariables;

public class Hud implements Disposable {

    private final Stage stage;
    private final Label scoreLabel;
    private final GameStateVariables gameStateVariables;

    public Hud(GameStateVariables gameStateVariables, SpriteBatch spriteBatch){

        this.gameStateVariables= gameStateVariables;
        Viewport viewport = new FitViewport(StaticVariables.VIRTUAL_WIDTH / StaticVariables.PPM,
                StaticVariables.VIRTUAL_HEIGHT / StaticVariables.PPM,
                new OrthographicCamera());
        stage= new Stage(viewport, spriteBatch);

        Table table= new Table();
        table.top();
        table.setFillParent(true);

        int score=0;
        scoreLabel =new Label(String.format("%d", score), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        table.add(scoreLabel).expandX();

        stage.addActor(table);
    }


    public Stage getStage(){
        return stage;
    }

    public void update(){
        scoreLabel.setText(String.format("%d", gameStateVariables.getScore()));
    }

    @Override
    public void dispose() {

    }
}
