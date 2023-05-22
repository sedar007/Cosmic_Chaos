package weapon.ammo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Rocket extends Ammo{
    // Static
    public final static String DEFAULT_PICTURE = "pictures/projectiles/rocket.png";
    public final static String DEFAULT_NAME = "Rocket";
    public final static int DEFAULT_DEGATS = 10;
    private final static int DEFAULT_SPEED = 5;

    // Constructor
    public Rocket(float xPosition, float yPosition, SpriteBatch batch){
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_DEGATS,DEFAULT_SPEED, xPosition, yPosition, batch);
    }

    // Methodes

}
