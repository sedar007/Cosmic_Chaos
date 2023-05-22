package weapon;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.Collision;
import spacecraft.Spacecraft;
import weapon.ammo.Ammo;
import weapon.ammo.Rocket;
import weapon.ammo.RocketEventail;

import java.util.HashSet;
import java.util.Iterator;

public class RocketCyclone extends Weapon{
    // Static
    private static final String DEFAULT_NAME = "Rocket Cyclone";

//    private Ammo[] AmmosTab;
    protected HashSet<RocketEventail> munitionsTab; // Hashset de munitions



    // Methode
    public RocketCyclone(SpriteBatch batch, Spacecraft spacecraft) {
        super(batch, spacecraft);
        setName(DEFAULT_NAME);
        this.munitionsTab = new HashSet<>();
    }

    // Methodes
    public void createAmmo(){//creer un seul munition

        float x1Position = getSpacecraft().getPosX() + ((float) getSpacecraft().getPicture().getWidth() /2 ) - 10;
        float y1Position = getSpacecraft().getPosY() + getSpacecraft().getPicture().getHeight();
        float x2Position = getSpacecraft().getPosX() + ((float) getSpacecraft().getPicture().getWidth() /2 );
        float y2Position = getSpacecraft().getPosY() + getSpacecraft().getPicture().getHeight();
        float x3Position = getSpacecraft().getPosX() + ((float) getSpacecraft().getPicture().getWidth() /2 ) + 10;
        float y3Position = getSpacecraft().getPosY() + getSpacecraft().getPicture().getHeight();
        RocketEventail rocketEventail = new RocketEventail(x1Position,  y1Position, x2Position, y2Position, x3Position, y3Position, getBatch());

        Texture boom = new Texture("pictures/explosion/explosion-5.png");
        getBatch().draw(boom,x2Position-rocketEventail.AmmosTab[0].getImage().getWidth()-10,y2Position-rocketEventail.AmmosTab[0].getImage().getHeight());

        munitionsTab.add(rocketEventail);

//        Music soundShoot;
//        soundShoot = Gdx.audio.newMusic(Gdx.files.internal("song/gunner-sound-43794.mp3"));
//        soundShoot.play();
    }

    @Override
    public void spawnAllAmmo(){
        /* Methode qui permet de creer les munitions, de supprimer les munitions hors de l'ecran et d'afficher les munitions */
        create(); // Create les ammos
        Iterator<RocketEventail> iterator = this.munitionsTab.iterator();

        while (iterator.hasNext()) {
            RocketEventail ammo = iterator.next();
            ammo.move();
            for(int i =0; i< 3; i++)
                if(ammo.AmmosTab[i] != null)
                    if (ammo.AmmosTab[i].getyPosition() > Gdx.graphics.getHeight() + 2 || ammo.AmmosTab[i].getyPosition() < 0 || ammo.AmmosTab[i].getxPosition() < 0 || ammo.AmmosTab[i].getxPosition() > Gdx.graphics.getWidth()) // Supprimer les ammos
                        ammo.AmmosTab[i]  = null;

            int test = 0;
            for(int i=0; i < 3; i++)
                if(ammo.AmmosTab[i]  == null)
                    test ++;
            if(test == 3)
                iterator.remove();

        }

    }

    public void shoot(Spacecraft ennemi) {
        /* Methode qui permet de verifier s'il y a des collisions entre l'ennemi et une munition */
        Iterator<RocketEventail> iterator = this.munitionsTab.iterator();


        while (iterator.hasNext()) {
            RocketEventail ammo = iterator.next();
            for(int i =0; i< 3; i++){
                if(ammo.AmmosTab[i] != null) {
                    if (new Collision().checkCollision(ammo.AmmosTab[i].getxPosition(), ammo.AmmosTab[i].getyPosition(), ammo.AmmosTab[i].getImage().getWidth(), ammo.AmmosTab[i].getImage().getHeight(), ennemi.getPosX(),
                            ennemi.getPosY(), ennemi.getPicture().getWidth(), ennemi.getPicture().getHeight())) {//si les tirs ont touche les ennemis !!
                        Texture boom = new Texture("pictures/explosion/boom06.png");
                        getBatch().draw(boom,ammo.AmmosTab[i].getxPosition() - (float) boom.getWidth() /2,ammo.AmmosTab[i].getyPosition()- (float) boom.getHeight() /2);
                        ennemi.shotBy(ammo.AmmosTab[i]);//il a ete tire !!
                        ammo.AmmosTab[i] = null;
                    }
                }
            }
        }

    }

    @Override
    public void create() {
        if (Gdx.input.isTouched())
            createAmmo();
    }

}


/*

public class RocketStorm extends Weapon {





}
*/