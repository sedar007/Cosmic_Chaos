package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VenomclawRavager extends Alien{

    private static final String DEFAULT_PICTURE = "";
    private static final int DEFAULT_MAX_LIFE = 20;
    private static final String DEFAULT_NAME = "monster 1";


    public VenomclawRavager(SpriteBatch batch) {
        super(DEFAULT_NAME,DEFAULT_PICTURE, DEFAULT_MAX_LIFE, batch);
    }
}
