package weapon.ammo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InfernalBlaze extends Ammo{
    // Static
    public final static String DEFAULT_PICTURE = "pictures/projectiles/shotoval.png";
    public final static String DEFAULT_NAME = "InfernalBlaze";
    public final static int DEFAULT_DEGATS = 3;
    private final static int DEFAULT_SPEED = -5;

    // Constructor
    public InfernalBlaze(float xPosition, float yPosition, SpriteBatch batch){
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_DEGATS,DEFAULT_SPEED, xPosition, yPosition, batch);
    }

    // Methodes


}

