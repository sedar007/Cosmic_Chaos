package weapon.ammo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.AllAssets;
import spacecraft.Spacecraft;

public class Laser extends Ammo{
    // Static
    public static final String DEFAULT_NAME = "laser";
    public static final float DEFAULT_DEGATS = 10f;
    private final static int DEFAULT_SPEED = 9;

    // Constructor
    public Laser(float xPosition, float yPosition, SpriteBatch batch, AllAssets assets) {
        super(DEFAULT_NAME,DEFAULT_DEGATS,DEFAULT_SPEED, xPosition, yPosition, batch, assets, assets.getLaserPicture());
    }

    // Methodes
    public void move(Spacecraft owner) {
        setxPosition(owner.getPosX());
        setyPosition(owner.getPosY());

        getBatch().begin();
        getBatch().draw(getImage(), getxPosition(), getyPosition());
        getBatch().end();


    }



}
