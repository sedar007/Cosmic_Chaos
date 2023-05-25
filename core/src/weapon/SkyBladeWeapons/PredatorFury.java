package weapon.SkyBladeWeapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import helpers.Collision;
import spacecraft.Alien;
import spacecraft.Spacecraft;
import weapon.Weapon;
import weapon.ammo.Ammo;
import weapon.ammo.Predator;
import weapon.ammo.Rocket;
import weapon.ammo.RocketEventail;

import java.util.HashSet;
import java.util.Iterator;

public class PredatorFury extends Weapon {
    // Static
    private static final String DEFAULT_NAME = "Predator Fury";
    protected HashSet<Predator> predators; // Hashset de munitions
    private boolean fire;

    public boolean isFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }

    // Methode
    public PredatorFury(SpriteBatch batch, Spacecraft spacecraft) {
        super(batch, spacecraft);
        setName(DEFAULT_NAME);
        this.predators = new HashSet<>();
        this.fire = true;
    }

    // Methodes
    public void createAmmo(){//creer un seul munition

        float xPosition = getSpacecraft().getPosX() + ((float) getSpacecraft().getPicture().getWidth() /2 ) ;
        float yPosition = getSpacecraft().getPosY() + getSpacecraft().getPicture().getHeight();
        Predator predator = new Predator(xPosition,yPosition, getBatch());
        munitions.add(predator);

        // Explosions a la creation du munition
        Texture boom = new Texture("pictures/explosion/explosion-5.png");
        getBatch().begin();
        getBatch().draw(boom,predator.getxPosition()-predator.getImage().getWidth()-8,predator.getyPosition()-predator.getImage().getHeight());
        getBatch().end();


        predators.add(predator);
        lastAmmoTime = TimeUtils.nanoTime(); // dernier ammo creer

//        Music soundShoot;
//        soundShoot = Gdx.audio.newMusic(Gdx.files.internal("song/gunner-sound-43794.mp3"));
//        soundShoot.play();
    }
    public void spawnAllAmmo(Alien target){
        /* Methode qui permet de creer les munitions, de supprimer les munitions hors de l'ecran et d'afficher les munitions */
        create(); // Create les ammos
        Iterator<Predator> iterator = predators.iterator();

        while (iterator.hasNext()) {
            Predator ammo = iterator.next();
            ammo.move(target); // gerer les deplacement des ammos

            if( (ammo.getyPosition() > Gdx.graphics.getHeight() + 2) || ( ammo.getyPosition() < 0)
                    || (ammo.getxPosition() < 0) || (ammo.getxPosition() > Gdx.graphics.getWidth()) ) // Supprimer les ammos
                iterator.remove();
        }
    }

    public void shoot(Spacecraft opponent) {
        /* Methode qui permet de verifier s'il y a des collisions entre l'ennemi et une munition */
        Iterator<Predator> iterator = predators.iterator();

        while (iterator.hasNext()) {
            Predator ammo = iterator.next();
            if (new Collision().checkCollision(ammo.getxPosition(), ammo.getyPosition(), ammo.getImage().getWidth(), ammo.getImage().getHeight(), opponent.getPosX(),
                    opponent.getPosY(), opponent.getPicture().getWidth(), opponent.getPicture().getHeight())) {//si les tirs ont touche les ennemis !!
                Texture boom = new Texture("pictures/explosion/boom06.png");
                getBatch().begin();
                getBatch().draw(boom,ammo.getxPosition() - (float) boom.getWidth() /2,ammo.getyPosition()- (float) boom.getHeight() /2);
                getBatch().end();
                opponent.shotBy(ammo);//il a ete tire !!
                iterator.remove();

            }
        }
    }

    @Override
    public void create() {
       if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && this.fire){
           createAmmo();
           this.fire = ! this.fire;
      }


    }

//    declaration de ressource
    // declaration
    // scolarite





}
