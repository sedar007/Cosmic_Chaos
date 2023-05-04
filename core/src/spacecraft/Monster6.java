package spacecraft;

import com.badlogic.gdx.graphics.Texture;

public class Monster6 extends Alien{

    private static String name = "Monter_6";
    private static Texture picture = new Texture("");

    public Monster6(){
        super(name,picture);
        setLife(60);
        setMaxLife(60);
    }
}
