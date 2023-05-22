package weapon.ammo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RocketEventail{
    // Static
    public final static String DEFAULT_PICTURE = "pictures/projectiles/rocket.png";
    public final static String DEFAULT_NAME = "Rocket Eventail";
    public final static int DEFAULT_DEGATS = 10;
    private final static int DEFAULT_SPEED = 5;
     public Ammo[] AmmosTab ;

    // Constructor
    public RocketEventail(float x1Position, float y1Position,float x2Position, float y2Position, float x3Position, float y3Position, SpriteBatch batch){
        this.AmmosTab = new Ammo[3];
        this.AmmosTab[0] = new Ammo("pai", DEFAULT_PICTURE, DEFAULT_DEGATS,DEFAULT_SPEED, x1Position, y1Position, batch);
        this.AmmosTab[1] = new Ammo("pai", DEFAULT_PICTURE, DEFAULT_DEGATS,DEFAULT_SPEED, x2Position, y2Position, batch);
        this.AmmosTab[2] = new Ammo("pai", DEFAULT_PICTURE, DEFAULT_DEGATS,DEFAULT_SPEED, x3Position, y3Position, batch);

    }

    // Methodes
    public void move() {

        if(this.AmmosTab[0] != null){
            this.AmmosTab[0].setyPosition(this.AmmosTab[0].getyPosition() + this.AmmosTab[0].getSpeed());
            this.AmmosTab[0].setxPosition(this.AmmosTab[0].getxPosition() - this.AmmosTab[0].getSpeed());
            this.AmmosTab[0].getBatch().draw(this.AmmosTab[0].getImage(),  this.AmmosTab[0].getxPosition(),this.AmmosTab[0].getyPosition(),
                    (float) this.AmmosTab[0].getImage().getWidth() / 2, (float) this.AmmosTab[0].getImage().getHeight() / 2, this.AmmosTab[0].getImage().getWidth(), this.AmmosTab[0].getImage().getHeight(), 1, 1, 90, 0, 0, this.AmmosTab[0].getImage().getWidth(), this.AmmosTab[0].getImage().getHeight(), false, false);
        }

        if(this.AmmosTab[1] != null) {
            this.AmmosTab[1].setyPosition(this.AmmosTab[1].getyPosition() + this.AmmosTab[1].getSpeed());
            this.AmmosTab[1].getBatch().draw(this.AmmosTab[1].getImage(), this.AmmosTab[1].getxPosition(), this.AmmosTab[1].getyPosition());
        }

        if(this.AmmosTab[2] != null){
            this.AmmosTab[2].setyPosition(this.AmmosTab[2].getyPosition() + this.AmmosTab[2].getSpeed());
            this.AmmosTab[2].setxPosition(this.AmmosTab[2].getxPosition() + this.AmmosTab[2].getSpeed());
            this.AmmosTab[2].getBatch().draw(this.AmmosTab[2].getImage(),  this.AmmosTab[2].getxPosition(),this.AmmosTab[2].getyPosition(),
                    (float) this.AmmosTab[2].getImage().getWidth() / 2, (float) this.AmmosTab[2].getImage().getHeight() / 2, this.AmmosTab[2].getImage().getWidth(), this.AmmosTab[2].getImage().getHeight(), 1, 1, 340, 0, 0, this.AmmosTab[2].getImage().getWidth(), this.AmmosTab[2].getImage().getHeight(), false, false);
        }
    }


}


