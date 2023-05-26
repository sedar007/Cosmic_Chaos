package weapon.SkyBladeWeapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import spacecraft.Spacecraft;
import weapon.AlienWeapons.SingleRocket;
import weapon.Weapon;
import weapon.ammo.EnergyOrbs;
import weapon.ammo.Laser;
import weapon.ammo.Rocket;

public class LaserFury extends Weapon {
        // Static
        private static final String DEFAULT_NAME = "Laser Fury";
        private Weapon lastWeapon;
        private  float times, duration;

        // Constructor
        public LaserFury(SpriteBatch batch, Spacecraft spacecraft){
            super(batch,spacecraft);
            setName(DEFAULT_NAME);
            lastWeapon = spacecraft.getWeapon();
            times = 0f;
            duration = 5f;
        }

        // Methodes
        public void createAmmo(){//creer un seul pair de munition
            // munition 1
            float xPosition = getSpacecraft().getPosX() + ((float) getSpacecraft().getPicture().getWidth() /2 ) ;
            float yPosition = getSpacecraft().getPosY() + getSpacecraft().getPicture().getHeight();
            Laser laser = new Laser(xPosition,yPosition, getBatch());
            munitions.add(laser);

            lastAmmoTime = TimeUtils.nanoTime();
            Music soundShoot;
            soundShoot = Gdx.audio.newMusic(Gdx.files.internal("song/gunner-sound-43794.mp3"));
            soundShoot.play();
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


