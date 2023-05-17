package weapon.ammo;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ammo2 extends Ammo {
    public static String DEFAULT_PICTURE = "";
    public static String DEFAULT_NAME = "ammo 2";
    private int speed = 5;
    public static int DEFAULT_DEGATS = 10;
    SpriteBatch batch;

    private Ammo munitions[];
    public Ammo2(float xPosition, float yPosition,SpriteBatch batch){
//        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_DEGATS, xPosition, yPosition);
        super();
        this.batch = batch;
        munitions = new Ammo[3];
        for(int i = 0; i<3; i++) {
            munitions[i] = new Ammo1(xPosition + i, yPosition);
            batch.draw( munitions[i].getImage(),  munitions[i].getxPosition(),  munitions[i].getyPosition());

        }

    }


    @Override
    public void move(){



    }
}
