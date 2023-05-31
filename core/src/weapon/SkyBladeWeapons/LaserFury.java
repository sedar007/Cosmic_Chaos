package weapon.SkyBladeWeapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import exceptions.NoWeaponExeption;
import helpers.AllAssets;
import spacecraft.Spacecraft;
import weapon.Weapon;
import weapon.ammo.Laser;

public class LaserFury extends Weapon {
        // Static
        private static final String DEFAULT_NAME = "Laser Fury";
        private Weapon lastWeapon;
        private  float times, duration;

        // Constructor
        public LaserFury(SpriteBatch batch, Spacecraft spacecraft, AllAssets assets){
            super(batch,spacecraft, assets);
            setName(DEFAULT_NAME);
            try {
                lastWeapon = spacecraft.getWeapon();
            }
            catch (NoWeaponExeption e){
                lastWeapon = new RocketStorm(getBatch(),spacecraft, getAssets());
            }
            times = 0f;
            duration = 9f;
        }

        // Methodes
        public void createAmmo(){//creer un seul pair de munition
            // munition 1
            float xPosition = getSpacecraft().getPosX() + ((float) getSpacecraft().getPicture().getWidth() /2 ) ;
            float yPosition = getSpacecraft().getPosY() + getSpacecraft().getPicture().getHeight();
            Laser laser = new Laser(xPosition,yPosition, getBatch(), getAssets());
            munitions.add(laser);

            lastAmmoTime = TimeUtils.nanoTime();

        }

        @Override
        public void create() {
            times += Gdx.graphics.getDeltaTime();
            if (times >= duration ) {
                getSpacecraft().setWeapon(lastWeapon);
                times = 0;
            }
            else
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
                    createAmmo();
        }

}


