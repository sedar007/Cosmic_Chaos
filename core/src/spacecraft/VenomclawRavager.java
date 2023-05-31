package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.AllAssets;
import weapon.AlienWeapons.SingleRocket;

public class VenomclawRavager extends Alien{


    private static final int DEFAULT_MAX_LIFE = 300;
    private static final String DEFAULT_NAME = "Venom Claw Ravager";

    public VenomclawRavager(SpriteBatch batch, AllAssets assets) {
        super(DEFAULT_NAME, DEFAULT_MAX_LIFE, batch, assets.getVenomClawRavager());
        setWeapon(new SingleRocket(batch,this, assets));
    }
}
