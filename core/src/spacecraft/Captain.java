package spacecraft;

import com.badlogic.gdx.graphics.Texture;

public class Captain extends Spacecraft {

    private static int DEFAULT_MAX_LIFE = 100 ;
    private static int DEFAULT_LIFE = 100 ;
    private static int DEFAULT_MAX_STAMINA = 100 ;
    private static int DEFAULT_STAMINA = 100 ;
    private static Texture DEFAULT_PICTURE =new Texture(" " ); ;
    public Captain(String name){
        super(name,DEFAULT_PICTURE);

        setLife(DEFAULT_LIFE);
        setMaxLife( DEFAULT_MAX_LIFE );
        setStamina( DEFAULT_STAMINA);
        setMaxStamina( DEFAULT_MAX_STAMINA);
    }

    @Override
    public void move() {

    }
}
