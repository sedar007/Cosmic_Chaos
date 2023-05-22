package weapon.AlienWeapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import spacecraft.Spacecraft;
import weapon.Weapon;
import weapon.ammo.EnergyOrbs;
import weapon.ammo.RocketJet;

public class SingleRocket  extends Weapon{

        // Static
        private static final String DEFAULT_NAME = "Single Rocket";

        // Constructor
        public SingleRocket(SpriteBatch batch, Spacecraft spacecraft){
            super(batch,spacecraft);
            setName(DEFAULT_NAME);
        }

        // Methodes
        public void createAmmo(){//creer un seul pair de munition
            // munition 1
            RocketJet ammo = new RocketJet(getSpacecraft().getPosX() + getSpacecraft().getPicture().getWidth()/2,getSpacecraft().getPosY(),getBatch());
            Texture boom = new Texture("pictures/explosion/explosion-5.png");
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
