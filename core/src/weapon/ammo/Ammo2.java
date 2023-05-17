package weapon.ammo;
import com.badlogic.gdx.graphics.Texture;
public class Ammo2 extends Ammo {
    public static String DEFAULT_PICTURE = "pictures/projectiles/rocket.png";
    public static String DEFAULT_NAME = "ammo 2";
    public static int DEFAULT_DEGATS = 10;
    public Ammo2(float xPosition, float yPosition){
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_DEGATS, xPosition, yPosition);
    }
}
