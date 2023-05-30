package weapon.ammo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.AllAssets;

public class EnergyOrbs extends Ammo{
    // Static
    public final static String DEFAULT_NAME = "Energy Orbs";
    public final static float DEFAULT_DEGATS = 0.9f;
    private final static int DEFAULT_SPEED = -5;

    // Constructor
    public EnergyOrbs(float xPosition, float yPosition, SpriteBatch batch, AllAssets assets){
        super(DEFAULT_NAME,DEFAULT_DEGATS,DEFAULT_SPEED, xPosition, yPosition, batch, assets, assets.getShotbigPicture());
    }

    // Methodes


}

