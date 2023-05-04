package spacecraft;

import com.badlogic.gdx.graphics.Texture;

public class Monster4 extends Alien {
    private static final int DEFAULT_MAX_LIFE = 100 ;

    private static final String DEFAULT_NAME = "Monster4" ;
    private static final Texture DEFAULT_PICTURE = new Texture(" " );
    public Monster4() {
        super(DEFAULT_NAME,DEFAULT_PICTURE, DEFAULT_MAX_LIFE);

    }
}
