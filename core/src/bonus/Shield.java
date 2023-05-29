package bonus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screen.AllAssets;
import spacecraft.Skyblade;

public class Shield extends Bonus {
    public int charge = 250;//le nombre de charge du bouclier !!
    private static final String DEFAULT_NAME = " BOUCLIER " ;

    private  Texture ShieldPicture;

    public  Texture getShieldPicture() {
        return ShieldPicture;
    }

    public  void setShieldPicture(Texture shieldPicture) {
        ShieldPicture = shieldPicture;
    }

    public Shield(Skyblade skyblade, float x , float y, SpriteBatch batch, AllAssets assets) {
        super(DEFAULT_NAME,skyblade,x,y,batch);
        setPicture(assets.getBonusShieldPicture());
        setShieldPicture(assets.getShieldPicture());

    }
    public void touched(){// impact des tirs
        if(charge > 0 ){
            this.charge--;
        }
        else{
            this.charge = 0;
        }
        if(isBroken())
            this.skyblade.protect(false);


    }
    public boolean isBroken(){
       return this.charge == 0;
    }
    @Override
    public void collect() {
        this.skyblade.protect(true);
    }
}
