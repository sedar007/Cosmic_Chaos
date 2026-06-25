package com.mygdx.game.spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.bonus.BonusScore;
import com.mygdx.game.commands.ICommand;
import com.mygdx.game.commands.MouseCommand;
import com.mygdx.game.helpers.AllAssets;
import com.mygdx.game.weapon.SkyBladeWeapons.PredatorFury;
import com.mygdx.game.weapon.ammo.Ammo;
import com.badlogic.gdx.Gdx;

import com.mygdx.game.bonus.Shield;

public class Skyblade extends Spacecraft {
    private static final int DEFAULT_PUISSANCE = 1500;
    private static final String DEFAULT_NAME = "captain";

    public Shield shield;//bouclier du Capitaine.

    private boolean Protected;//si le vaisseau du Héro possède un bouclier !

    public BonusScore bonusScore;
    private PredatorFury predatorFury; // Missiles tete chercheuses
    private final AllAssets assets;

    public Shield getShield() {
        return shield;
    }

    private ICommand command;


    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public void protect(boolean value){ this.Protected = value;}

    public PredatorFury getPredatorFury() {
        return predatorFury;
    }

    public void setPredatorFury(PredatorFury predatorFury) {
        this.predatorFury = predatorFury;
    }

    public Skyblade(String name, SpriteBatch batch, AllAssets assets){
        super(name, batch);
        setPuissance(DEFAULT_PUISSANCE);
        setMaxPuissance( DEFAULT_PUISSANCE );
        setPosX(0);
        setPosY(0);
        this.assets = assets;
        setPicture(getAssets().getSkybladePicture());
        this.command = new MouseCommand();


        protect(false);
        setShield(new Shield(this, getPosX(), getPosY(), getBatch(), this.assets));
        setPredatorFury(new PredatorFury(batch, this, assets));

    }


    public Skyblade(SpriteBatch batch, AllAssets assets){
       this(DEFAULT_NAME, batch, assets);
    }

    @Override
    public void move(Spacecraft spacecraft) {

        getBatch().draw(getPicture(), getPosX(), getPosY());
        if(isProtected())
            getBatch().draw(getShield().getShieldPicture(), getPosX()- 25, getPosY() );


        if (command != null) {
            command.handleMovement(this, Gdx.graphics.getDeltaTime());
        }

        if (getPosX() < 0) {
            setPosX(0);
        } else if (getPosX() > Gdx.graphics.getWidth() - getPicture().getWidth()) {
            setPosX(Gdx.graphics.getWidth() - getPicture().getWidth());
        }

        if (getPosY() < 0) {
            setPosY(0);
        } else if (getPosY() > Gdx.graphics.getHeight() - getPicture().getHeight()) {
            setPosY(Gdx.graphics.getHeight() - getPicture().getHeight());
        }

    }

    public boolean isProtected(){
        return this.Protected;
    }

    public void equipShield(){//pour renforcer le vaisseau
        this.Protected = true;
    }

    public void shotBy(Ammo ammo) {// qund il a ete tire
       if(isProtected()){
           shield.touched();//on décrémente la charge du bouclier
       }
       else {
           setPuissance(getPuissance() - ammo.getDegats());
       }
    }

    public AllAssets getAssets() {
        return assets;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }
}
