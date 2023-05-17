package weapon.ammo;
import com.badlogic.gdx.graphics.Texture;
public class Ammo1 extends Ammo{
    public static String DEFAULT_PICTURE = "pictures/projectiles/rocket.png";
    public static String DEFAULT_NAME = "ammo 1";
    public static int DEFAULT_DEGATS = 10;


    public Ammo1(float xPosition, float yPosition){
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_DEGATS, xPosition, yPosition);
    }

}
