package weapon;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import spacecraft.Spacecraft;
import weapon.ammo.EnergyOrbs;

public class InfernoOrbs extends Weapon{
    // Static
    private static final String DEFAULT_NAME = "InfernoOrbs";

    // Constructor
    public InfernoOrbs(SpriteBatch batch, Spacecraft spacecraft){
        super(batch,spacecraft);
        setName(DEFAULT_NAME);
    }

    // Methodes
    public void createAmmo(){//creer un seul pair de munition
       // munition 1
        EnergyOrbs ammo = new EnergyOrbs(getSpacecraft().getPosX() + 15,getSpacecraft().getPosY(),getBatch());
        Texture boom = new Texture("pictures/explosion/explosion-5.png");
        getBatch().begin();
        getBatch().draw(boom,ammo.getxPosition()-ammo.getImage().getWidth()-8,ammo.getyPosition()-ammo.getImage().getHeight());
        getBatch().end();
        munitions.add(ammo);

        // munition 2
        EnergyOrbs ammo2 = new EnergyOrbs(getSpacecraft().getPosX() + getSpacecraft().getPicture().getWidth() - 15,getSpacecraft().getPosY(), getBatch());
        Texture boom2 = new Texture("pictures/explosion/explosion-5.png");
        getBatch().begin();

        getBatch().draw(boom2,ammo2.getxPosition()-ammo2.getImage().getWidth()-8,ammo2.getyPosition()-ammo2.getImage().getHeight());
        getBatch().end();

        munitions.add(ammo2);

        lastAmmoTime = TimeUtils.nanoTime();
//        Music soundShoot;
//        soundShoot = Gdx.audio.newMusic(Gdx.files.internal("song/gunner-sound-43794.mp3"));
//        soundShoot.play();
    }

    @Override
    public void create() {
        if (TimeUtils.nanoTime() - lastAmmoTime > 100005500)
            createAmmo();
    }


}
