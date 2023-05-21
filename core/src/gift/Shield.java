package gift;

import com.badlogic.gdx.graphics.Texture;
import spacecraft.Skyblade;

public class Shield extends Gift {
    public int charge = 10;//le nombre de charge du bouclier !!
    private static final String DEFAULT_NAME = " BOUCLIER " ;
    private static final String DEFAULT_IMAGE = "" ;
    private Skyblade skyblade;

    public Shield(Skyblade captain) {
        super(DEFAULT_NAME,DEFAULT_IMAGE);
        this.skyblade = captain;
        this.skyblade.protect(true);
    }
    public void touched(){// impact des tirs
        if(charge > 0 ){
            this.charge--;
        }
        else{
            this.charge = 0;
            this.skyblade.protect(false);
        }
    }
    public boolean isBroken(){
       return this.charge == 0;
    }
}
