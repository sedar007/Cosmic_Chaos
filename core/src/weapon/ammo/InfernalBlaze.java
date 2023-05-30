package weapon.ammo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.AllAssets;

public class InfernalBlaze extends Ammo{
    // Static
    public final static String DEFAULT_NAME = "InfernalBlaze";
    public final static float DEFAULT_DEGATS = 0.15f;
    private final static int DEFAULT_SPEED = -10;

    private int xSpeed =  DEFAULT_SPEED /2;

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    // Constructor
    public InfernalBlaze(float xPosition, float yPosition, SpriteBatch batch, AllAssets assets){
        super(DEFAULT_NAME,DEFAULT_DEGATS,DEFAULT_SPEED, xPosition, yPosition, batch, assets, assets.getRocketInversePicture());

    }

    // Methodes
    public void move() {
        setxPosition(getxPosition() + xSpeed);
        setyPosition(getyPosition() + DEFAULT_SPEED);


        getBatch().begin();
        getBatch().draw(getImage(), getxPosition(), getyPosition());
        getBatch().end();


    }


    }

