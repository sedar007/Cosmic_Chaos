package weapon;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import helpers.Collision;
import spacecraft.Spacecraft;
import weapon.ammo.Ammo;
import weapon.ammo.EnergyOrbs;
import weapon.ammo.Rocket;

import java.util.HashSet;
import java.util.Iterator;

public class WeaponAlien extends Weapon{
    private static final String DEFAULT_NAME = "WeaponAlien";

    public WeaponAlien(SpriteBatch batch, Spacecraft spacecraft){
        super(batch,spacecraft);

    }

    public void spawnAmmo(){//creer un seul pair de munition
       // munition 1
        EnergyOrbs ammo = new EnergyOrbs(spacecraft.getPosX() + 10,spacecraft.getPosY());
        Texture boom = new Texture("pictures/explosion/explosion-5.png");
        this.batch.draw(boom,ammo.getxPosition()-ammo.getImage().getWidth()-8,ammo.getyPosition()-ammo.getImage().getHeight());
        munitions.add(ammo);

        // munition 2
        EnergyOrbs ammo2 = new EnergyOrbs(spacecraft.getPosX() + spacecraft.getPicture().getWidth() - 10,spacecraft.getPosY());
        Texture boom2 = new Texture("pictures/explosion/explosion-5.png");
        this.batch.draw(boom2,ammo2.getxPosition()-ammo2.getImage().getWidth()-8,ammo2.getyPosition()-ammo2.getImage().getHeight());
        munitions.add(ammo2);

        lastAmmoTime = TimeUtils.nanoTime();
//        Music soundShoot;
//        soundShoot = Gdx.audio.newMusic(Gdx.files.internal("song/gunner-sound-43794.mp3"));
//        soundShoot.play();
    }







}
