package weapon.SkyBladeWeapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import helpers.Collision;
import spacecraft.Spacecraft;
import weapon.Weapon;
import weapon.ammo.Predator;
import weapon.ammo.Rocket;
import weapon.ammo.RocketEventail;

import java.util.HashSet;
import java.util.Iterator;

public class PredatorFury extends Weapon {
    // Static
    private static final String DEFAULT_NAME = "Predator Fury";
    protected HashSet<Predator> munitionsTab; // Hashset de munitions
    private Spacecraft cible;
    private boolean fire;



    // Methode
    public PredatorFury(SpriteBatch batch, Spacecraft spacecraft, Spacecraft cible) {
        super(batch, spacecraft);
        setName(DEFAULT_NAME);
        this.munitionsTab = new HashSet<>();
        this.cible = cible;
        this.fire = true;
    }

    // Methodes
    public void createAmmo(){//creer un seul munition

        float xPosition = getSpacecraft().getPosX() + ((float) getSpacecraft().getPicture().getWidth() /2 ) ;
        float yPosition = getSpacecraft().getPosY() + getSpacecraft().getPicture().getHeight();
        Predator predator = new Predator(xPosition,yPosition, getBatch(), cible);
        munitions.add(predator);

        // Explosions a la creation du munition
        Texture boom = new Texture("pictures/explosion/explosion-5.png");
        getBatch().begin();
        getBatch().draw(boom,predator.getxPosition()-predator.getImage().getWidth()-8,predator.getyPosition()-predator.getImage().getHeight());
        getBatch().end();


        munitionsTab.add(predator);
        lastAmmoTime = TimeUtils.nanoTime(); // dernier ammo creer

//        Music soundShoot;
//        soundShoot = Gdx.audio.newMusic(Gdx.files.internal("song/gunner-sound-43794.mp3"));
//        soundShoot.play();
    }

    @Override
    public void create() {
//        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && this.fire){
//            createAmmo();
//            this.fire = ! this.fire;
//        }
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) )
            if (TimeUtils.nanoTime() - lastAmmoTime > 100000000 )
                createAmmo();

    }

//    declaration de ressource
    // declaration
    // scolarite





}
