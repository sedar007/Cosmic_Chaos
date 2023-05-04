package spacecraft;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.Texture;
public class Boss extends Alien{

    private static final String DEFAULT_NAME = "Boss";
    private static final int DEFAULT_MAX_LIFE = 60;
    private int ratio = 2;

    public int getRatio() {
        return ratio;
    }

    private static  Texture DEFAULT_PICTURE = new Texture("pictures/ships/roundysh_large.png");
    public Boss(){
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_MAX_LIFE);
    }


}
