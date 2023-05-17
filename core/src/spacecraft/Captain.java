package spacecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Captain extends Spacecraft {

    private static int DEFAULT_MAX_LIFE = 100 ;
    private static int DEFAULT_LIFE = 100 ;
    private static int DEFAULT_MAX_STAMINA = 100 ;
    private static int DEFAULT_STAMINA = 100 ;
    private static Texture DEFAULT_PICTURE =new Texture("pictures/ships/blueships1_small.png" );
    private static String DEFAULT_NAME = "captain";
    public Captain(String name){
        super(name,DEFAULT_PICTURE);

        setLife(DEFAULT_LIFE);
        setMaxLife( DEFAULT_MAX_LIFE );
        setStamina( DEFAULT_STAMINA);
        setMaxStamina( DEFAULT_MAX_STAMINA);
        setPosX(0);
        setPosY(0);
    }

    public Captain(){
       this(DEFAULT_NAME);
    }


    @Override
    public void move() {
        float positionX = Gdx.input.getX() - ((float) getPicture().getWidth() /2);

        if(positionX < 0)
            setPosX(0);

        else if(positionX > Gdx.graphics.getWidth() - getPicture().getWidth())
            setPosX(Gdx.graphics.getWidth() - getPicture().getWidth());
        else
            setPosX(positionX);

        float positionY = Gdx.graphics.getHeight() - Gdx.input.getY() - ((float) getPicture().getHeight() /2); // Calculate paddle y position based on mouse position

        if(positionY < 0)
            setPosY(0);
        else if(positionY > Gdx.graphics.getHeight() - getPicture().getHeight())
            setPosY(Gdx.graphics.getHeight() - getPicture().getHeight());
        else
            setPosY(positionY);

    }
}
