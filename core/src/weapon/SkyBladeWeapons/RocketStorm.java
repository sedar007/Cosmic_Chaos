package weapon.SkyBladeWeapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import spacecraft.Spacecraft;
import weapon.Weapon;
import weapon.ammo.Rocket;

public class RocketStorm extends Weapon {
    // Static
    private static final String DEFAULT_NAME = "Rocket Storm";

    // Constructor
    public RocketStorm(SpriteBatch batch, Spacecraft spacecraft) {
        super(batch, spacecraft);
        setName(DEFAULT_NAME);
    }

    // Methodes
    public void createAmmo(){//creer un seul munition
        float xPosition = getSpacecraft().getPosX() + ((float) getSpacecraft().getPicture().getWidth() /2 ) ;
        float yPosition = getSpacecraft().getPosY() + getSpacecraft().getPicture().getHeight();
        Rocket ammo = new Rocket(xPosition,yPosition, getBatch());
        munitions.add(ammo);

        // Explosions a la creation du munition
        Texture boom = new Texture("pictures/explosion/explosion-5.png");
        getBatch().begin();

        getBatch().draw(boom,ammo.getxPosition()-ammo.getImage().getWidth()-8,ammo.getyPosition()-ammo.getImage().getHeight());
        getBatch().end();


        // sound du shoots
        Music soundShoot;
        soundShoot = Gdx.audio.newMusic(Gdx.files.internal("song/gunner-sound-43794.mp3"));
        soundShoot.play();

        lastAmmoTime = TimeUtils.nanoTime(); // dernier ammo creer
    }

    @Override
    public void create() {
        /* Creer les ammos quand on appuie sur la souris */
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            if (TimeUtils.nanoTime() - lastAmmoTime > 100000000 )
                createAmmo();
    }

}
