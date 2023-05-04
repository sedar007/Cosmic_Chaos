package spacecraft;

import com.badlogic.gdx.graphics.Texture;

public class Monster4 extends Alien {
    private static int DEFAULT_MAX_LIFE = 100 ;
    private static int DEFAULT_LIFE = 100 ;
    private static String DEFAULT_NAME = "Monster4" ;
    private static Texture DEFAULT_PICTURE =new Texture(" " );
    public Monster4() {
        super(DEFAULT_NAME,DEFAULT_PICTURE);
        setLife(DEFAULT_LIFE);
        setMaxLife( DEFAULT_MAX_LIFE );

    }
}
