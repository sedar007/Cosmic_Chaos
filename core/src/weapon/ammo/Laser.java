package weapon.ammo;

public class Laser extends Ammo{
    public static String DEFAULT_PICTURE = "pictures/projectiles/laser.png";
    public static String DEFAULT_NAME = "laser";
    public static int DEFAULT_DEGATS = 15;
    private int speed = 99999;



    public Laser(float xPosition, float yPosition) {
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_DEGATS, xPosition, yPosition);

    }

    @Override
    public void move(){
        setxPosition(getxPosition());
        setyPosition(getyPosition() + speed);

    }
}
