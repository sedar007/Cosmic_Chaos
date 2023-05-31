package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import helpers.AllAssets;
import weapon.AlienWeapons.SingleRocket;

public class TyrantOfDesolation extends Alien {
    private static final String DEFAULT_NAME = "Tyrant Of Desolation";
    private static final int DEFAULT_MAX_LIFE = 100; // 100

    private static final int DEFAULT_POINTS = 10;

    public TyrantOfDesolation(SpriteBatch batch, AllAssets assets) {
        super(DEFAULT_NAME, DEFAULT_MAX_LIFE, batch, assets.getTyrantOfDesolation());
        setPoints(DEFAULT_POINTS);
        setWeapon(new SingleRocket(batch,this, assets));
        setxSpeed(5);
        setySpeed(5);


    }
}