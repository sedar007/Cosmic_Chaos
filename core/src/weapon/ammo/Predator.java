package weapon.ammo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import spacecraft.Spacecraft;

public class Predator extends Ammo{
    // Static
    public final static String DEFAULT_PICTURE = "pictures/projectiles/predator.png";
    public final static String DEFAULT_NAME = "Predator";
    public final static int DEFAULT_DEGATS = 10;
    private final static int DEFAULT_SPEED = 5;
    private Spacecraft cible;

    // Constructor
    public Predator(float xPosition, float yPosition, SpriteBatch batch, Spacecraft cible){
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_DEGATS,DEFAULT_SPEED, xPosition, yPosition, batch);
        this.cible = cible;
    }

    // Methodes
    public void move() {
        if(this.cible.getPosX() + (float) this.cible.getPicture().getWidth() /2 < getxPosition())
            setxPosition(getxPosition() - getSpeed());
        if (this.cible.getPosX() + (float) this.cible.getPicture().getHeight() /2> getxPosition())
            setxPosition(getxPosition() + getSpeed());

        setyPosition(getyPosition() + getSpeed());

        getBatch().begin();
        getBatch().draw(getImage(), getxPosition(), getyPosition());
        getBatch().end();


    }
}



