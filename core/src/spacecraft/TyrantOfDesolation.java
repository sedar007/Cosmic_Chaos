package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screen.AllAssets;
import weapon.AlienWeapons.SingleRocket;

import weapon.ammo.Rocket;
import weapon.ammo.RocketEventail;

public class TyrantOfDesolation extends Alien {
    private static final String DEFAULT_NAME = "Monster3";
    private static final int DEFAULT_MAX_LIFE = 60; // 100

    private static final int DEFAULT_POINTS = 10;

    public TyrantOfDesolation(SpriteBatch batch, AllAssets assets) {
        super(DEFAULT_NAME, DEFAULT_MAX_LIFE, batch, assets.getTyrantOfDesolation());
        setPoints(DEFAULT_POINTS);
        setWeapon(new SingleRocket(batch,this, assets));


    }
}