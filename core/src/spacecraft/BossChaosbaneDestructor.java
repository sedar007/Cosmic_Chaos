package spacecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.Collision;
import weapon.AlienWeapons.InfernoOrbs;
import weapon.AlienWeapons.MegaInferno;
import weapon.Weapon;

import java.util.Random;

public class BossChaosbaneDestructor extends Alien{

    private static final String DEFAULT_NAME = "Boss";
    private static final int DEFAULT_MAX_LIFE = 2500;
    private int ratio = 2;

    public int getRatio() {
        return ratio;
    }


    private static final String DEFAULT_PICTURE = "pictures/ships/roundysh_large.png" ;

    public BossChaosbaneDestructor(SpriteBatch batch){
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_MAX_LIFE, batch);
       setWeapon(new MegaInferno(batch, this));
    }
    @Override
    public void move(Spacecraft spacecraft) {

        getBatch().begin();
        getBatch().draw(getPicture(), getPosX(), getPosY());
        getBatch().end();

        setPosX(getPosX() + getxSpeed());
//        setPosY(getPosY() + ySpeed);

//        xAlea -= 1;
        setxAlea(getyAlea() - 1);
//        yAlea--;

        if(getPosX()<0 || getPosX() > Gdx.graphics.getWidth() - getPicture().getWidth())
            setxSpeed(- getxSpeed());

        if(getPosY()< (float) Gdx.graphics.getHeight() /2 || getPosY() > Gdx.graphics.getHeight() - getPicture().getHeight())
            setySpeed(- getySpeed());


        if(getxAlea() == 0) {
            setxAlea( new Random().nextInt(2)) ;
            setxSpeed(- getxSpeed());

        }

        if(getyAlea() == 0){
             setyAlea(new Random().nextInt(2)) ;
            setySpeed(- getySpeed());

        }

        if(getPosY() < 0)
            setPosY(0);
        if(getPosX() < 0)
            setPosX(0);

        if(getPosY() > Gdx.graphics.getHeight() - getPicture().getHeight())
            setPosY(Gdx.graphics.getHeight() - getPicture().getHeight());

        if(getPosX() > Gdx.graphics.getWidth() - getPicture().getWidth())
            setPosX(Gdx.graphics.getWidth() - getPicture().getWidth());



    }


}
