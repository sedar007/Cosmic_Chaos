package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import bonus.BonusScore;
import helpers.AllAssets;
import weapon.SkyBladeWeapons.PredatorFury;
import weapon.ammo.Ammo;
import com.badlogic.gdx.Gdx;

import bonus.Shield;

public class Skyblade extends Spacecraft {
    private static final int DEFAULT_PUISSANCE = 500;
    private static final String DEFAULT_NAME = "captain";

    public Shield shield;//bouclier du Capitaine.

    private boolean Protected;//si le vaisseau du Héro possède un bouclier !

    public BonusScore bonusScore;
    private PredatorFury predatorFury; // Missiles tete chercheuses
    private final AllAssets assets;

    public Shield getShield() {
        return shield;
    }


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


        protect(false);
        setShield(new Shield(this, getPosX(), getPosY(), getBatch(), this.assets));
        setPredatorFury(new PredatorFury(batch, this, assets));

    }

    public Skyblade(SpriteBatch batch, AllAssets assets){
       this(DEFAULT_NAME, batch, assets);
    }

    @Override
    public void move(Spacecraft spacecraft) {

        getBatch().begin();

        getBatch().draw(getPicture(), getPosX(), getPosY());
        if(isProtected())
            getBatch().draw(getShield().getShieldPicture(), getPosX()- 25, getPosY() );

        getBatch().end();


        float positionX = Gdx.input.getX() - ((float) getPicture().getWidth() /2);

        if(positionX < 0)
            setPosX(0);

        else if(positionX > Gdx.graphics.getWidth() - getPicture().getWidth())
            setPosX(Gdx.graphics.getWidth() - getPicture().getWidth());
        else
            setPosX(positionX);

        float positionY = Gdx.graphics.getHeight() - Gdx.input.getY() - ((float) getPicture().getHeight() /2); // Calculate paddle y position based on mouse position

        if(positionY < 0)
            setPosY(0);
        else if(positionY > Gdx.graphics.getHeight() - getPicture().getHeight())
            setPosY(Gdx.graphics.getHeight() - getPicture().getHeight());
        else
            setPosY(positionY);

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
}
