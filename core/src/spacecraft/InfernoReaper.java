package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screen.AllAssets;
import weapon.AlienWeapons.FireballStorm;
import weapon.ammo.Laser;

public class InfernoReaper extends Alien{
    private static final String DEFAULT_NAME = "Inferno Reaper";

    private static final int DEFAULT_MAX_LIFE = 10;


    public InfernoReaper(SpriteBatch batch, AllAssets assets) {
        super(DEFAULT_NAME,DEFAULT_MAX_LIFE, batch,assets.getInfernoReaper());
      // RocketStorm monster= new RocketStorm(new SpriteBatch(),this);
     setWeapon(new FireballStorm(batch,this, assets));
    }
}
