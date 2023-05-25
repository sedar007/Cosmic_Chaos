package weapon.ammo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnergyOrbs extends Ammo{
    // Static
    public final static String DEFAULT_PICTURE = "pictures/projectiles/shotbig.png";
    public final static String DEFAULT_NAME = "Energy Orbs";
    public final static float DEFAULT_DEGATS = 0.3f;
    private final static int DEFAULT_SPEED = -5;

    // Constructor
    public EnergyOrbs(float xPosition, float yPosition, SpriteBatch batch){
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_DEGATS,DEFAULT_SPEED, xPosition, yPosition, batch);
    }

    // Methodes


}

