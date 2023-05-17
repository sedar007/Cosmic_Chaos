package gift;

import com.badlogic.gdx.graphics.Texture;

public class Shield extends Gift {

    public int charge = 10;//le nombre de charge du bouclier !!
    public Shield(String name, Texture picture) {
        super(name,picture);
    }

    public void touched(){// impact des tirs
        this.charge--;
    }
    public boolean isBroken(){
      return this.charge == 0;
    }

}
