package weapon.ammo;

public class Laser extends Ammo{
    public static final String DEFAULT_PICTURE = "pictures/projectiles/laser.png";
    public static final String DEFAULT_NAME = "laser";
    public static final int DEFAULT_DEGATS = 15;
    private final static int DEFAULT_SPEED = 99999;




    public Laser(float xPosition, float yPosition) {
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_DEGATS,DEFAULT_SPEED, xPosition, yPosition);

    }

    @Override
    public void move(){
        setyPosition(getyPosition() + getSpeed());

    }
}
