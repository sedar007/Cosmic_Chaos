package spacecraft;

import com.badlogic.gdx.graphics.Texture;

public class Monster3 extends Alien {
    private static String DEFAULT_NAME = "Monster3";
    private static int DEFAULT_MAX_LIFE = 100;
    private static int DEFAULT_LIFE = 100;
    private static Texture DEFAULT_PICTURE = new Texture(" ");

    public Monster3() {
        super(DEFAULT_NAME, DEFAULT_PICTURE);
        setLife(DEFAULT_LIFE);
        setMaxLife(DEFAULT_MAX_LIFE);


    }
}