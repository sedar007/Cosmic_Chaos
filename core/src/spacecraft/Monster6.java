package spacecraft;

import com.badlogic.gdx.graphics.Texture;

public class Monster6 extends Alien{

    private static String DEFAULT_NAME = "Monster6";
    private static int DEFAULT_MAX_LIFE = 60;
    private static int DEFAULT_LIFE = 60;
    private static Texture DEFAULT_PICTURE = new Texture(" ");
    public Monster6(){
        super(DEFAULT_NAME,DEFAULT_PICTURE);
        setLife(DEFAULT_LIFE);
        setMaxLife(DEFAULT_MAX_LIFE);
    }
}
