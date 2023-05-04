package spacecraft;

import com.badlogic.gdx.graphics.Texture;

public class Monster5 extends Alien{

    private static String name = "Monter_5";
    private static Texture picture = new Texture("");

    public Monster5(){
        super(name,picture);
        setLife(5);
        setMaxLife(5);
    }
}
