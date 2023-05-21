package weapon.ammo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import spacecraft.Spacecraft;
import weapon.Weapon;

public class RocketStorm extends Weapon {

    public RocketStorm(SpriteBatch batch, Spacecraft spacecraft) {
        super(batch, spacecraft);
    }

    public void spawnAmmo(){//creer un seul munition
        float xPosition = spacecraft.getPosX() + ((float) spacecraft.getPicture().getWidth() /2 ) ;
        float yPosition = spacecraft.getPosY() + spacecraft.getPicture().getHeight();
        Rocket ammo = new Rocket(xPosition,yPosition);
//        Laser ammo = new Laser(xPosition,yPosition);

        Texture boom = new Texture("pictures/explosion/explosion-5.png");
        this.batch.draw(boom,ammo.getxPosition()-ammo.getImage().getWidth()-8,ammo.getyPosition()-ammo.getImage().getHeight());
        munitions.add(ammo);
        lastAmmoTime = TimeUtils.nanoTime();
        Music soundShoot;
        soundShoot = Gdx.audio.newMusic(Gdx.files.internal("song/gunner-sound-43794.mp3"));
        soundShoot.play();
    }

    public void spawnAllAmmo() {
        if (Gdx.input.isTouched())
            super.spawnAllAmmo();


    }
}
