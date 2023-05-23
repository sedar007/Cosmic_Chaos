package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import weapon.AlienWeapons.FireballStorm;
import weapon.ammo.Laser;

public class InfernoReaper extends Alien{
    private static final String DEFAULT_NAME = "monster 1";
    private static final String DEFAULT_PICTURE = "pictures/ships/enemy_2_1.png" ;

    private static final int DEFAULT_MAX_LIFE = 10;


    public InfernoReaper(SpriteBatch batch) {
        super(DEFAULT_NAME, DEFAULT_PICTURE,DEFAULT_MAX_LIFE, batch);
      // RocketStorm monster= new RocketStorm(new SpriteBatch(),this);
     setWeapon(new FireballStorm(batch,this));
    }
}
