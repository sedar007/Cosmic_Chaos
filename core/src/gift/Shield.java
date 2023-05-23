package gift;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import spacecraft.Skyblade;

public class Shield extends Gift {
    public int charge = 10;//le nombre de charge du bouclier !!
    private static final String DEFAULT_NAME = " BOUCLIER " ;
    private static final String DEFAULT_IMAGE = "pictures/shield/shield.png" ;

    public Shield(Skyblade skyblade, float x ,float y, SpriteBatch batch) {
        super(DEFAULT_NAME,DEFAULT_IMAGE,skyblade,x,y,batch);
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
