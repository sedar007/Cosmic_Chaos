package weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import helpers.Collision;
import spacecraft.Spacecraft;
import weapon.ammo.Ammo;
import weapon.ammo.Ammo1;
import weapon.ammo.Ammo2;
import weapon.ammo.Laser;

import java.util.HashSet;
import java.util.Iterator;

public class Weapon {

    private String DEFAULT_NAME = "Weapon";
    private String name;
    HashSet<Ammo> munitions;
    SpriteBatch batch;
    Spacecraft spacecraft;
    long lastAmmoTime;


    public Weapon(SpriteBatch batch, Spacecraft spacecraft){
        this.name = DEFAULT_NAME;
        munitions = new HashSet<>();
        this.batch = batch;
        this.spacecraft = spacecraft;

    }


    public void shoot(Spacecraft ennemi) {
        Iterator<Ammo> iterator = munitions.iterator();

        while (iterator.hasNext()) {
            Ammo ammo = iterator.next();
            batch.draw(ammo.getImage(), ammo.getxPosition(), ammo.getyPosition());
            ammo.move();
            if (new Collision().checkCollision(ammo.getxPosition(), ammo.getyPosition(), ammo.getImage().getWidth(), ammo.getImage().getHeight(), ennemi.getPosX(),
                    ennemi.getPosY(), ennemi.getPicture().getWidth(), ennemi.getPicture().getHeight()) || ( ammo.getImage().getHeight() > Gdx.graphics.getHeight() + 1) ) {
                iterator.remove();
            }


        }
        if (TimeUtils.nanoTime() - lastAmmoTime > 100000000)
            spawnAmmo();

    }

    private void spawnAmmo(){
        float xPosition = spacecraft.getPosX() + (spacecraft.getPicture().getWidth()/3 ) ;
        float yPosition = spacecraft.getPosY() + spacecraft.getPicture().getHeight();
//        Ammo1 ammo = new Ammo1(xPosition,yPosition);



        for(int i = 0; i< 2; i++){
            munitions.add(new Ammo1(xPosition + (i)*15, yPosition));

        }

//        Laser ammo = new Laser(xPosition,yPosition);

//        munitions.add(ammo);
        lastAmmoTime = TimeUtils.nanoTime();
        Music soungShoot;
        soungShoot = Gdx.audio.newMusic(Gdx.files.internal("song/gunner-sound-43794.mp3"));

        soungShoot.play();
    }



}
