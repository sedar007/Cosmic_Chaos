package weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import exceptions.NoWeaponExeption;
import helpers.Collision;
import screen.AllAssets;
import spacecraft.Spacecraft;
import weapon.ammo.Ammo;
import weapon.ammo.Laser;
import weapon.ammo.Rocket;

import java.util.HashSet;
import java.util.Iterator;

abstract public class Weapon {

    private String name; // nom de l'arme
    protected HashSet<Ammo> munitions; // Hashset de munitions
    private SpriteBatch batch; // Garder le SpriteBatch du jeu
    private Spacecraft spacecraft; // Savoir le Spacecraft qui utilise l'arme
    protected  long lastAmmoTime; // Le temps du dernier tire
    private final AllAssets assets;

    public AllAssets getAssets() {
        return assets;
    }

    // Getters et Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public SpriteBatch getBatch() {
        return batch;
    }
    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }
    public Spacecraft getSpacecraft() {
        return spacecraft;
    }
    public void setSpacecraft(Spacecraft spacecraft) {
        this.spacecraft = spacecraft;
    }


    // Constructor
    public Weapon(SpriteBatch batch, Spacecraft spacecraft, AllAssets assets){
        this.munitions = new HashSet<>();
        setBatch(batch);
        setSpacecraft(spacecraft);
        this.assets = assets;

    }

    // Methodes abstraites
    abstract public void createAmmo();//creer un seul munition
    abstract public void create(); // Permet de savoir Quand creer les ammo


    // Methodes
    public void shoot(Spacecraft opponent) {
        /* Methode qui permet de verifier s'il y a des collisions entre l'ennemi et une munition */
        Iterator<Ammo> iterator = this.munitions.iterator();

        while (iterator.hasNext()) {
            Ammo ammo = iterator.next();
            if (new Collision().checkCollision(ammo.getxPosition(), ammo.getyPosition(), ammo.getImage().getWidth(), ammo.getImage().getHeight(), opponent.getPosX(),
                    opponent.getPosY(), opponent.getPicture().getWidth(), opponent.getPicture().getHeight())) {//si les tirs ont touche les ennemis !!
                Texture boom = getAssets().getBoom6();
                getBatch().begin();
                getBatch().draw(boom,ammo.getxPosition() - (float) boom.getWidth() /2,ammo.getyPosition()- (float) boom.getHeight() /2);
                getBatch().end();
                opponent.shotBy(ammo);//il a ete tire !!
                iterator.remove();

            }
        }
    }


    public void spawnAllAmmo() throws NoWeaponExeption {
        if(getSpacecraft().getWeapon() == null)
                throw new NoWeaponExeption();
        /* Methode qui permet de creer les munitions, de supprimer les munitions hors de l'ecran et d'afficher les munitions */
        create(); // Create les ammos
        Iterator<Ammo> iterator = this.munitions.iterator();

        while (iterator.hasNext()) {
            Ammo ammo = iterator.next();
            if(ammo instanceof Laser){
                Laser laser = (Laser) ammo;
                laser.move(getSpacecraft());
            }
            else
                ammo.move(); // gerer les deplacement des ammos

            if( (ammo.getyPosition() > Gdx.graphics.getHeight() + 2) || ( ammo.getyPosition() < 0)
                    || (ammo.getxPosition() < 0) || (ammo.getxPosition() > Gdx.graphics.getWidth()) ) // Supprimer les ammos
                iterator.remove();
        }
    }
}
