package weapon.ammo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class InfernalBlaze extends Ammo{
    // Static
    public final static String DEFAULT_PICTURE = "pictures/projectiles/shotoval.png";
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
    public InfernalBlaze(float xPosition, float yPosition, SpriteBatch batch){
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_DEGATS,DEFAULT_SPEED, xPosition, yPosition, batch);

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

