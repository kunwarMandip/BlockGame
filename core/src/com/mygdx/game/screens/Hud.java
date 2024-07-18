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
import com.mygdx.game.GlobalVariables;

public class Hud implements Disposable {

    private final Stage stage;
    private final Label scoreLabel;

    public Hud(SpriteBatch spriteBatch){
        Viewport viewport = new FitViewport(GlobalVariables.VIRTUAL_WIDTH / GlobalVariables.PPM,
                GlobalVariables.VIRTUAL_HEIGHT / GlobalVariables.PPM,
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
        int newScore=GlobalVariables.SCORE;
        scoreLabel.setText(String.format("%d", newScore));
    }

    @Override
    public void dispose() {

    }
}
