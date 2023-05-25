package weapon.ammo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import spacecraft.Spacecraft;

public class Predator extends Ammo{
    // Static
    public final static String DEFAULT_PICTURE = "pictures/projectiles/predator.png";
    public final static String DEFAULT_NAME = "Predator";
    public final static float DEFAULT_DEGATS = 5;
    private final static int DEFAULT_SPEED = 5;

    // Constructor
    public Predator(float xPosition, float yPosition, SpriteBatch batch){
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_DEGATS,DEFAULT_SPEED, xPosition, yPosition, batch);

    }

    // Methodes
    public void move(Spacecraft target) {
        if(target.getPosX() + (float) target.getPicture().getWidth() /2 < getxPosition())
            setxPosition(getxPosition() - getSpeed());
        if (target.getPosX() + (float) target.getPicture().getHeight() /2> getxPosition())
            setxPosition(getxPosition() + getSpeed());

        if (target.getPosY() + (float) target.getPicture().getHeight() /2> getyPosition())
            setyPosition(getyPosition() + getSpeed());
        else
            setyPosition(getyPosition() - getSpeed());

        // aim sur le target
        Texture cible = new Texture("pictures/target/aim.png");

        getBatch().begin();
        getBatch().draw(cible, target.getPosX() + (float) (target.getPicture().getWidth() /2) - (float) cible.getWidth() /2, target.getPosY() - (float) cible.getHeight() /2);
        getBatch().draw(getImage(), getxPosition(), getyPosition());
        getBatch().end();


    }
}

/*
*  if(targetPredator == null)
                targetPredator = monster;
                *
                *
                if(targetPredator != null  ){
            captain.getPredatorFury().spawnAllAmmo(targetPredator);
        }
        if(targetPredator != null){
            captain.getPredatorFury().shoot(targetPredator);
            targetPredator = null;


        }
        *
        * */



