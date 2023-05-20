package weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import helpers.Collision;
import spacecraft.Spacecraft;
import weapon.ammo.Ammo;
import weapon.ammo.Laser;
import weapon.ammo.Rocket;

import java.util.HashSet;
import java.util.Iterator;

public class Weapon {

    private String DEFAULT_NAME = "Weapon";
    private String name;
    HashSet<Ammo> munitions;
    public SpriteBatch batch;
    Spacecraft spacecraft;
    private static long lastAmmoTime;


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
                    ennemi.getPosY(), ennemi.getPicture().getWidth(), ennemi.getPicture().getHeight())) {
                Texture boom = new Texture("pictures/explosion/boom06.png");
                batch.draw(boom,ammo.getxPosition() - (float) boom.getWidth() /2,ammo.getyPosition()- (float) boom.getHeight() /2);
                iterator.remove();


            }

            if(ammo.getyPosition() > Gdx.graphics.getHeight() + 2){
                iterator.remove();

            }


        }
        if (TimeUtils.nanoTime() - lastAmmoTime > 100000000)
            spawnAmmo();

    }

    private void spawnAmmo(){
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



}
