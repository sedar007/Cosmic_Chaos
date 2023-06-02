package spacecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.AllAssets;
import weapon.AlienWeapons.MegaInferno;

import java.util.Random;

public class BossChaosbaneDestructor extends Alien{

    private static final String DEFAULT_NAME = "Boss Chaos bane Destructor";
    private static final int DEFAULT_MAX_LIFE = 2500;


    public BossChaosbaneDestructor(SpriteBatch batch, AllAssets assets){
        super(DEFAULT_NAME,DEFAULT_MAX_LIFE, batch,assets.getBossLarge());
       setWeapon(new MegaInferno(batch, this, assets));
    }
    @Override
    public void move(Spacecraft spacecraft) {

        getBatch().begin();
        getBatch().draw(getPicture(), getPosX(), getPosY());
        getBatch().end();

        setPosX(getPosX() + getxSpeed());
        setxAlea(getxAlea() - 1);

        if(getPosX()<=0 || getPosX() >= Gdx.graphics.getWidth() - getPicture().getWidth())
            setxSpeed(- getxSpeed());

        if(getPosY()<= (float) Gdx.graphics.getHeight() /2 || getPosY() >= Gdx.graphics.getHeight() - getPicture().getHeight())
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
