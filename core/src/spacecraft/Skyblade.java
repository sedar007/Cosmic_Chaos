package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gift.BonusScore;
import gift.Gift;
import weapon.ammo.Ammo;
import com.badlogic.gdx.Gdx;

import java.util.HashSet;

import gift.Shield;

public class Skyblade extends Spacecraft {
    private static final int DEFAULT_MAX_PUISSANCE = 1000 ;
    private static final int DEFAULT_PUISSANCE = 1000 ;
    private static final String DEFAULT_PICTURE ="pictures/ships/blueships1_small.png";
    private static final String DEFAULT_NAME = "captain";

   /* public HashSet<Ammo> Ammos ;*/
    public Shield shield;//bouclier du Capitaine.
    private Double score;
    private boolean Protected;//si le vaisseau du Héro possède un bouclier !

    public BonusScore bonusScore;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void protect(boolean value){ this.Protected = value;}

    public Skyblade(String name){
        super(name,DEFAULT_PICTURE);
       // Ammos=new HashSet<>();
        setPuissance(DEFAULT_PUISSANCE);
        setMaxPuissance( DEFAULT_MAX_PUISSANCE );
        setPosX(0);
        setPosY(0);
        setScore(0.0);
        protect(false);
    }

    public Skyblade(){
       this(DEFAULT_NAME);
    }

    @Override
    public void move(SpriteBatch spriteBatch,Spacecraft spacecraft) {
        spriteBatch.draw(getPicture(), getPosX(), getPosY());
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

    public void shoot(){

    }



}
