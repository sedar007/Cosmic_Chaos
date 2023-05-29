package weapon.ammo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screen.AllAssets;

public class Rocket extends Ammo{
    // Static
    public final static String DEFAULT_NAME = "Rocket";
    public final static float DEFAULT_DEGATS = 5f;
    private final static int DEFAULT_SPEED = 5;

    // Constructor
    public Rocket(float xPosition, float yPosition, SpriteBatch batch, AllAssets assets){
        super(DEFAULT_NAME,DEFAULT_DEGATS,DEFAULT_SPEED, xPosition, yPosition, batch, assets, assets.getRocketPicture());
    }

    // Methodes

}
