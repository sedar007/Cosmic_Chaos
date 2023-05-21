package weapon.ammo;

public class EnergyOrbs extends Ammo{
    public final static String DEFAULT_PICTURE = "pictures/projectiles/shotbig.png";
    public final static String DEFAULT_NAME = "Energy Orbs";
    public final static int DEFAULT_DEGATS = 3;
    private final static int DEFAULT_SPEED = -5;


    public EnergyOrbs(float xPosition, float yPosition){
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_DEGATS,DEFAULT_SPEED, xPosition, yPosition);
    }

    @Override
    public void move() {
        setyPosition(getyPosition() + getSpeed());
    }

}

