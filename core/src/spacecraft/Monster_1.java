package spacecraft;

import com.badlogic.gdx.graphics.Texture;

public class Monster_1 extends Alien{
    private static String DEFAULT_NAME = "monster 1";
    private static Texture picture = new Texture("");

    public Monster_1() {
        super(DEFAULT_NAME, picture);
        setLife(10);
        setMaxLife(10);
    }
}
