package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RavagerScourge extends Alien{

    private static final String DEFAULT_NAME = "Monster5";
    private static final int DEFAULT_MAX_LIFE = 5;
    private static final String DEFAULT_PICTURE = " " ;

    public RavagerScourge(SpriteBatch batch){
        super(DEFAULT_NAME,DEFAULT_PICTURE, DEFAULT_MAX_LIFE, batch);

    }
}
