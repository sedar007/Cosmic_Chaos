package weapon.ammo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Laser extends Ammo{
    // Static
    public static final String DEFAULT_PICTURE = "pictures/projectiles/laser.png";
    public static final String DEFAULT_NAME = "laser";
    public static final float DEFAULT_DEGATS = 0.15f;
    private final static int DEFAULT_SPEED = 99999;

    // Constructor
    public Laser(float xPosition, float yPosition, SpriteBatch batch) {
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_DEGATS,DEFAULT_SPEED, xPosition, yPosition, batch);
    }

    // Methodes


}
