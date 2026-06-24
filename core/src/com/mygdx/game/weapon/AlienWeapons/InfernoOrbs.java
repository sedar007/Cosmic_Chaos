package com.mygdx.game.weapon.AlienWeapons;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.helpers.AllAssets;
import com.mygdx.game.spacecraft.Spacecraft;
import com.mygdx.game.weapon.Weapon;
import com.mygdx.game.weapon.ammo.EnergyOrbs;

public class InfernoOrbs extends Weapon {
    // Static
    private static final String DEFAULT_NAME = "InfernoOrbs";

    // Constructor
    public InfernoOrbs(SpriteBatch batch, Spacecraft spacecraft, AllAssets assets){
        super(batch,spacecraft, assets);
        setName(DEFAULT_NAME);
    }

    // Methodes
    public void createAmmo(){//creer un seul pair de munition
       // munition 1
        EnergyOrbs ammo = new EnergyOrbs(getSpacecraft().getPosX() + 15,getSpacecraft().getPosY(),getBatch(), getAssets());
        Texture boom = getAssets().getExplosion5();

        getBatch().draw(boom,ammo.getxPosition()-ammo.getImage().getWidth()-8,ammo.getyPosition()-ammo.getImage().getHeight());

        munitions.add(ammo);

        // munition 2
        EnergyOrbs ammo2 = new EnergyOrbs(getSpacecraft().getPosX() + getSpacecraft().getPicture().getWidth() - 15,getSpacecraft().getPosY(), getBatch(), getAssets());
        Texture boom2 = getAssets().getExplosion5();


        getBatch().draw(boom2,ammo2.getxPosition()-ammo2.getImage().getWidth()-8,ammo2.getyPosition()-ammo2.getImage().getHeight());


        munitions.add(ammo2);

        lastAmmoTime = TimeUtils.nanoTime();

    }

    @Override
    public void create() {
        if (TimeUtils.nanoTime() - lastAmmoTime > 100505500)
            createAmmo();
    }


}
