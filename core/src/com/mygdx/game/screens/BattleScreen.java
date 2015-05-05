/**
 * Created by wpower on 4/24/15.
 */
package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GunslingerGameClass;
import com.mygdx.game.stages.BattleStage;

public class BattleScreen implements Screen {

    final GunslingerGameClass gungame;

    Texture texture;
    BattleStage battlestage;

    public BattleScreen( GunslingerGameClass gun){
        gungame = gun;

        battlestage = new BattleStage();

        Gdx.input.setInputProcessor(battlestage);
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        battlestage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
        battlestage.dispose();
        texture.dispose();
    }
}
