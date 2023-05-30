package weapon.AlienWeapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import helpers.AllAssets;
import spacecraft.Spacecraft;
import weapon.Weapon;
import weapon.ammo.RocketJet;

public class SingleRocket  extends Weapon{

        // Static
        private static final String DEFAULT_NAME = "Single Rocket";

        // Constructor
        public SingleRocket(SpriteBatch batch, Spacecraft spacecraft, AllAssets assets){
            super(batch,spacecraft, assets);
            setName(DEFAULT_NAME);
        }

        // Methodes
        public void createAmmo(){//creer un seul pair de munition
            // munition 1
            RocketJet ammo = new RocketJet(getSpacecraft().getPosX() + (float) getSpacecraft().getPicture().getWidth() /2,getSpacecraft().getPosY(),getBatch(), getAssets());
            Texture boom = getAssets().getExplosion5();
            getBatch().begin();
            getBatch().draw(boom,ammo.getxPosition()-ammo.getImage().getWidth()-8,ammo.getyPosition()-ammo.getImage().getHeight());
            getBatch().end();
            munitions.add(ammo);

            lastAmmoTime = TimeUtils.nanoTime();
//        Music soundShoot;
//        soundShoot = Gdx.audio.newMusic(Gdx.files.internal("song/gunner-sound-43794.mp3"));
//        soundShoot.play();
        }

        @Override
        public void create() {
            if (TimeUtils.nanoTime() - lastAmmoTime > 100505500)
                createAmmo();
        }




}
