package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import weapon.AlienWeapons.MegaInferno;
import weapon.AlienWeapons.SingleRocket;

public class RavagerScourge extends Alien{

    private static final String DEFAULT_NAME = "Monster5";
    private static final int DEFAULT_MAX_LIFE = 5;
    private static final String DEFAULT_PICTURE = "pictures/ships/RavagerScourge.png" ;

    public RavagerScourge(SpriteBatch batch){
        super(DEFAULT_NAME,DEFAULT_PICTURE, DEFAULT_MAX_LIFE, batch);

        setWeapon(new MegaInferno(batch,this));
    }
}
