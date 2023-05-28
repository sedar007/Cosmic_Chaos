package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screen.AllAssets;
import weapon.AlienWeapons.MegaInferno;
import weapon.AlienWeapons.SingleRocket;

public class RavagerScourge extends Alien{

    private static final String DEFAULT_NAME = "Ravager Scourge";
    private static final int DEFAULT_MAX_LIFE = 5;

    public RavagerScourge(SpriteBatch batch, AllAssets assets){
        super(DEFAULT_NAME, DEFAULT_MAX_LIFE, batch, assets.getRavagerScourge());

        setWeapon(new SingleRocket(batch,this, assets));
    }
}
