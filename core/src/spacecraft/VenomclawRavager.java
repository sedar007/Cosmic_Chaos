package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import weapon.AlienWeapons.FireballStorm;
import weapon.ammo.EnergyOrbs;

public class VenomclawRavager extends Alien{


    private static final int DEFAULT_MAX_LIFE = 20;
    private static final String DEFAULT_PICTURE = "pictures/ships/spco_small.png";
    private static final String DEFAULT_NAME = "monster 1";


    public VenomclawRavager(SpriteBatch batch) {
        super(DEFAULT_NAME,DEFAULT_PICTURE, DEFAULT_MAX_LIFE, batch);
        setWeapon(new FireballStorm(batch,this));

    }
}
