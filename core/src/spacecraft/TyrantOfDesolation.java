package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import weapon.AlienWeapons.SingleRocket;

import weapon.ammo.Rocket;
import weapon.ammo.RocketEventail;

public class TyrantOfDesolation extends Alien {
    private static final String DEFAULT_NAME = "Monster3";
    private static final int DEFAULT_MAX_LIFE = 60; // 100

    private static final String DEFAULT_PICTURE = "pictures/ships/TyrantOfDesolation.png";
    private static final int DEFAULT_POINTS = 10;

    public TyrantOfDesolation(SpriteBatch batch) {
        super(DEFAULT_NAME, DEFAULT_PICTURE, DEFAULT_MAX_LIFE, batch);
        setPoints(DEFAULT_POINTS);
    setWeapon(new SingleRocket(batch,this));


    }
}