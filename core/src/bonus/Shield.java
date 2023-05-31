package bonus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.AllAssets;
import spacecraft.Skyblade;

public class Shield extends Bonus {
    private int charge;//le nombre de charge du bouclier !!
    private static final String DEFAULT_NAME = " BOUCLIER " ;

    private  Texture ShieldPicture;

    public  Texture getShieldPicture() {
        return ShieldPicture;
    }

    public  void setShieldPicture(Texture shieldPicture) {
        ShieldPicture = shieldPicture;
    }

    public Shield(Skyblade skyblade, float x , float y, SpriteBatch batch, AllAssets assets) {
        super(DEFAULT_NAME,skyblade,x,y,batch, assets);
        setPicture(assets.getBonusShieldPicture());
        setShieldPicture(assets.getShieldPicture());
        this.charge =  250;
    }
    public void touched(){
        //Impact des tirs sur le bouclier
        if(charge > 0 )
            this.charge--;

        //Pour savoir si le bouclier est cassé ou pas
        if(isBroken())
            this.skyblade.protect(false);//il ne sera pas protégé logiquement
    }
    public boolean isBroken(){
       return this.charge <= 0;
    }//si la charge devient nulle donc le bouclier cassé
    @Override
    public void collect() {//collecte le bouclier et declenche un petit son
        getAssets().getBonuCollect().play();
        this.skyblade.protect(true);
        this.skyblade.shield = this;
    }
}
