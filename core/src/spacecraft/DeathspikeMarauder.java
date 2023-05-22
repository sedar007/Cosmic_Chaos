package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DeathspikeMarauder extends Alien {
    private static final int DEFAULT_MAX_LIFE = 100 ;

    private static final String DEFAULT_NAME = "Monster4" ;
    private static final String DEFAULT_PICTURE = " " ;
    public DeathspikeMarauder(SpriteBatch batch) {
        super(DEFAULT_NAME,DEFAULT_PICTURE, DEFAULT_MAX_LIFE, batch);

    }
}
