package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import weapon.AlienWeapons.SingleRocket;
import weapon.SkyBladeWeapons.RocketCyclone;

public class DeathspikeMarauder extends Alien {
    private static final int DEFAULT_MAX_LIFE = 100 ;

    private static final String DEFAULT_NAME = "Monster4" ;
    private static final String DEFAULT_PICTURE = "pictures/ships/roundysh_large.png" ;
    public DeathspikeMarauder(SpriteBatch batch) {
        super(DEFAULT_NAME,DEFAULT_PICTURE, DEFAULT_MAX_LIFE, batch);
       setWeapon(new SingleRocket(batch,this));
    }
}
