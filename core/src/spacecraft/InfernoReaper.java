package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InfernoReaper extends Alien{
    private static final String DEFAULT_NAME = "monster 1";
    private static final String DEFAULT_PICTURE = " " ;

    private static final int DEFAULT_MAX_LIFE = 10;


    public InfernoReaper(SpriteBatch batch) {
        super(DEFAULT_NAME, DEFAULT_PICTURE,DEFAULT_MAX_LIFE, batch);

    }
}
