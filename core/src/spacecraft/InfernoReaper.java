package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import helpers.AllAssets;
import weapon.AlienWeapons.FireballStorm;

public class InfernoReaper extends Alien{
    private static final String DEFAULT_NAME = "Inferno Reaper";

    private static final int DEFAULT_MAX_LIFE = 250;


    public InfernoReaper(SpriteBatch batch, AllAssets assets) {
        super(DEFAULT_NAME,DEFAULT_MAX_LIFE, batch,assets.getInfernoReaper());
     setWeapon(new FireballStorm(batch,this, assets));
    }
}
