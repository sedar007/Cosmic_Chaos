package weapon.ammo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.AllAssets;

public class RocketEventail{
    // Static
    public final static String DEFAULT_NAME = "Rocket Eventail";
    public final static float DEFAULT_DEGATS = 0.9f;
    private final static int DEFAULT_SPEED = 6;
     public Ammo[] AmmosTab ;

    // Constructor
    public RocketEventail(float x1Position, float y1Position, float x2Position, float y2Position, float x3Position, float y3Position, float x4Position,
                          float y4Position, float x5Position, float y5Position,SpriteBatch batch, AllAssets assets){
        this.AmmosTab = new Ammo[5];
        this.AmmosTab[0] = new Ammo(DEFAULT_NAME,DEFAULT_DEGATS, DEFAULT_SPEED, x1Position, y1Position, batch, assets, assets.getRocketPicture());
        this.AmmosTab[1] = new Ammo(DEFAULT_NAME,DEFAULT_DEGATS, DEFAULT_SPEED, x2Position, y2Position, batch, assets, assets.getRocketPicture());
        this.AmmosTab[2] = new Ammo(DEFAULT_NAME,DEFAULT_DEGATS, DEFAULT_SPEED, x3Position, y3Position, batch, assets, assets.getRocketPicture());
        this.AmmosTab[3] = new Ammo(DEFAULT_NAME,DEFAULT_DEGATS, DEFAULT_SPEED, x4Position, y4Position, batch, assets, assets.getRocketPicture());
        this.AmmosTab[4] = new Ammo(DEFAULT_NAME,DEFAULT_DEGATS, DEFAULT_SPEED, x5Position, y5Position, batch, assets, assets.getRocketPicture());


    }

    // Methodes
    public void move() {

        if(this.AmmosTab[0] != null){//la gauche
            this.AmmosTab[0].setyPosition(this.AmmosTab[0].getyPosition() + this.AmmosTab[0].getSpeed());
            this.AmmosTab[0].setxPosition(this.AmmosTab[0].getxPosition() - this.AmmosTab[0].getSpeed());
            this.AmmosTab[0].getBatch().begin();
            this.AmmosTab[0].getBatch().draw(this.AmmosTab[0].getImage(),  this.AmmosTab[0].getxPosition(),this.AmmosTab[0].getyPosition(),
                    (float) this.AmmosTab[0].getImage().getWidth() / 2, (float) this.AmmosTab[0].getImage().getHeight() / 2, this.AmmosTab[0].getImage().getWidth(), this.AmmosTab[0].getImage().getHeight(), 1, 1, 45, 0, 0, this.AmmosTab[0].getImage().getWidth(), this.AmmosTab[0].getImage().getHeight(), false, false);
            this.AmmosTab[0].getBatch().end();

        }

        if(this.AmmosTab[1] != null) {//milieu
            this.AmmosTab[1].setyPosition(this.AmmosTab[1].getyPosition() + this.AmmosTab[1].getSpeed());
            this.AmmosTab[1].getBatch().begin();
            this.AmmosTab[1].getBatch().draw(this.AmmosTab[1].getImage(), this.AmmosTab[1].getxPosition(), this.AmmosTab[1].getyPosition());
            this.AmmosTab[1].getBatch().end();

        }

        if(this.AmmosTab[2] != null){//droite
            this.AmmosTab[2].setyPosition(this.AmmosTab[2].getyPosition() + this.AmmosTab[2].getSpeed());
            this.AmmosTab[2].setxPosition(this.AmmosTab[2].getxPosition() + this.AmmosTab[2].getSpeed());
            this.AmmosTab[2].getBatch().begin();
            this.AmmosTab[2].getBatch().draw(this.AmmosTab[2].getImage(),  this.AmmosTab[2].getxPosition(),this.AmmosTab[2].getyPosition(),
                    (float) this.AmmosTab[2].getImage().getWidth() / 2, (float) this.AmmosTab[2].getImage().getHeight() / 2, this.AmmosTab[2].getImage().getWidth(),
                    this.AmmosTab[2].getImage().getHeight(), 1, 1, 340, 0, 0, this.AmmosTab[2].getImage().getWidth(), this.AmmosTab[2].getImage().getHeight(), false, false);
            this.AmmosTab[2].getBatch().end();

        }

        if(this.AmmosTab[3] != null){
            this.AmmosTab[3].setyPosition(this.AmmosTab[3].getyPosition() + this.AmmosTab[3].getSpeed());
            this.AmmosTab[3].setxPosition(this.AmmosTab[3].getxPosition() - (float) this.AmmosTab[3].getSpeed() /2);
            this.AmmosTab[3].getBatch().begin();
            this.AmmosTab[3].getBatch().draw(this.AmmosTab[3].getImage(),  this.AmmosTab[3].getxPosition(),this.AmmosTab[3].getyPosition(),
                    (float) this.AmmosTab[3].getImage().getWidth() / 2, (float) this.AmmosTab[3].getImage().getHeight() / 2, this.AmmosTab[3].getImage().getWidth(),
                    this.AmmosTab[3].getImage().getHeight(), 1, 1, 30, 0, 0, this.AmmosTab[3].getImage().getWidth(), this.AmmosTab[3].getImage().getHeight(),
                    false, false);
            this.AmmosTab[3].getBatch().end();

        }

        if(this.AmmosTab[4] != null){//droite
            this.AmmosTab[4].setyPosition(this.AmmosTab[4].getyPosition() + this.AmmosTab[4].getSpeed());
            this.AmmosTab[4].setxPosition(this.AmmosTab[4].getxPosition() + (float) this.AmmosTab[4].getSpeed() /2);
            this.AmmosTab[4].getBatch().begin();
            this.AmmosTab[4].getBatch().draw(this.AmmosTab[4].getImage(),  this.AmmosTab[4].getxPosition(),this.AmmosTab[4].getyPosition(),
                    (float) this.AmmosTab[4].getImage().getWidth() / 2, (float) this.AmmosTab[4].getImage().getHeight() / 2,
                    this.AmmosTab[4].getImage().getWidth(), this.AmmosTab[4].getImage().getHeight(), 1, 1, 355, 0, 0,
                    this.AmmosTab[4].getImage().getWidth(), this.AmmosTab[4].getImage().getHeight(), false, false);
            this.AmmosTab[4].getBatch().end();

        }
    }

}


