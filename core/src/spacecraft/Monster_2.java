package spacecraft;

import com.badlogic.gdx.graphics.Texture;

public class Monster_2 extends Alien{

    private static final Texture DEFAULT_PICTURE = new Texture("");
    private static final int DEFAULT_MAX_LIFE = 20;
    private static final String DEFAULT_NAME = "monster 1";


    public Monster_2() {
        super(DEFAULT_NAME,DEFAULT_PICTURE, DEFAULT_MAX_LIFE);
    }
}
