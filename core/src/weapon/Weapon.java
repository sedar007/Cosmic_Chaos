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

abstract public class Weapon {

    private static final String DEFAULT_NAME = "Weapon";
    private String name;
    protected HashSet<Ammo> munitions;
    public SpriteBatch batch;
    protected Spacecraft spacecraft;
    protected  long lastAmmoTime;



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
            ammo.move();
            if (new Collision().checkCollision(ammo.getxPosition(), ammo.getyPosition(), ammo.getImage().getWidth(), ammo.getImage().getHeight(), ennemi.getPosX(),
                    ennemi.getPosY(), ennemi.getPicture().getWidth(), ennemi.getPicture().getHeight())) {//si les tirs ont touche les ennemis !!
                Texture boom = new Texture("pictures/explosion/boom06.png");
                batch.draw(boom,ammo.getxPosition() - (float) boom.getWidth() /2,ammo.getyPosition()- (float) boom.getHeight() /2);
                ennemi.shotBy(ammo);//il a ete tire !!
                iterator.remove();

            }

            if(ammo.getyPosition() > Gdx.graphics.getHeight() + 2){
                iterator.remove();

            }

        }


    }

    abstract public void spawnAmmo();//creer un seul munition



    public void spawnAllAmmo(){
        if (TimeUtils.nanoTime() - lastAmmoTime > 100000000)
            spawnAmmo();
        for (Ammo ammo : munitions) {
            batch.draw(ammo.getImage(), ammo.getxPosition(), ammo.getyPosition());
            ammo.move();
        }
    }

}
