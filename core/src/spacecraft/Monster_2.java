package spacecraft;

import com.badlogic.gdx.graphics.Texture;

public class Monster_2 extends Alien{

    private static String DEFAULT_NAME = "monster 2";
    private static Texture picture = new Texture("");

    public Monster_2() {
        super(DEFAULT_NAME, picture);
        setLife(20);
        setMaxLife(20);
    }
}
