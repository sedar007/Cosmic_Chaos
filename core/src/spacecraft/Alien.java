package spacecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

public class Alien extends Spacecraft {

    private int xSpeed = 3;
    private int ySpeed = 2;
    private long xAlea;
    private long yAlea;


    public Alien(String name, String picture, int maxPuissance){
        super(name,picture);
       setMaxPuissance(maxPuissance);

        int xPos = 0;
        xPos = (new Random().nextInt(2) == 0 )? xPos : Gdx.graphics.getWidth() - getPicture().getWidth();
//        int yPos = Gdx.graphics.getHeight()-getPicture().getHeight() - 2;
        int yPos = new Random().nextInt(Gdx.graphics.getHeight() - getPicture().getHeight());
        setPosX(xPos);
        setPosY(yPos);
        xAlea = new Random().nextInt(5)  ;
        yAlea = new Random().nextInt(4)  ;
    }

    @Override
    public void move() {
        setPosX(getPosX() + xSpeed);
        setPosY(getPosY() + ySpeed);

        xAlea -= 1;
        yAlea--;

        if(getPosX()<0 || getPosX() > Gdx.graphics.getWidth() - getPicture().getWidth())
            xSpeed = - xSpeed;

        if(getPosY()<0 || getPosY() > Gdx.graphics.getHeight() - getPicture().getHeight())
            ySpeed = - ySpeed;

        if(xAlea == 0) {
            xAlea =  new Random().nextInt(2) ;
            xSpeed = - xSpeed;
        }

        if(yAlea == 0){
            yAlea =  new Random().nextInt(2) ;
            ySpeed = - ySpeed;
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

    public float shotBy(float shot) {
        shot = (this.getPuissance() - shot) >= 0 ? shot : this.getPuissance();
        setPuissance(this.getPuissance() - 1);
        return shot;
    }


}

