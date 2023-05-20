package weapon.ammo;

public class Rocket extends Ammo{
    public final static String DEFAULT_PICTURE = "pictures/projectiles/rocket.png";
    public final static String DEFAULT_NAME = "Rocket";
    public final static int DEFAULT_DEGATS = 10;
    private final static int DEFAULT_SPEED = 5;


    public Rocket(float xPosition, float yPosition){
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_DEGATS,DEFAULT_SPEED, xPosition, yPosition);
    }


    @Override
    public void move() {
        setyPosition(getyPosition() + getSpeed());
    }
}
