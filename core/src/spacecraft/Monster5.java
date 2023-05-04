package spacecraft;

import com.badlogic.gdx.graphics.Texture;

public class Monster5 extends Alien{

    private static String DEFAULT_NAME = "Monster5";
    private static int DEFAULT_MAX_LIFE = 5;
    private static int DEFAULT_LIFE = 5;
    private static Texture DEFAULT_PICTURE = new Texture(" ");
    public Monster5(){
        super(DEFAULT_NAME,DEFAULT_PICTURE);
        setLife(DEFAULT_LIFE);
        setMaxLife(DEFAULT_MAX_LIFE);
    }
}
