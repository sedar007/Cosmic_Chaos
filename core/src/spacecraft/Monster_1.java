package spacecraft;

import com.badlogic.gdx.graphics.Texture;

public class Monster_1 extends Alien{
    private static final String DEFAULT_NAME = "monster 1";
    private static final Texture DEFAULT_PICTURE = new Texture("");
    private static final int DEFAULT_MAX_LIFE = 10;


    public Monster_1() {
        super(DEFAULT_NAME, DEFAULT_PICTURE,DEFAULT_MAX_LIFE);

    }
}
