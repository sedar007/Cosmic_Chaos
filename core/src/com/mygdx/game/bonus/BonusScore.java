package com.mygdx.game.bonus;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.helpers.AllAssets;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.spacecraft.Skyblade;

public class BonusScore extends Bonus {
    private static final String DEFAULT_NAME = " BONUS DE SCORE " ;
    private static final float BONUS = 250f ;
    GameScreen gameScreen;


    public BonusScore(Skyblade skyblade, float x , float y, SpriteBatch batch, GameScreen gameScreen, AllAssets assets) {
        super(DEFAULT_NAME,skyblade,x,y,batch, assets);
        setBonus(BONUS);
        this.gameScreen = gameScreen;
        setPicture(assets.getBonusScorePicture());
    }
    public void collect(){//Sert a collecter le bonus et a modifier le score

        //Le son une fois qu'on a collecté un bonus !
        getAssets().getBonuCollect().play();

        //modifier le score
        this.gameScreen.setScore(this.gameScreen.getScore() + BONUS);
    }

    public float getBonus(){
        return BONUS;
    }

}
