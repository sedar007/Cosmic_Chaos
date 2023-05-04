package spacecraft;

import com.badlogic.gdx.graphics.Texture;

public class Alien extends Spacecraft {

    @Override
    public void move() {

    }

    public Alien(String name, Texture picture){
        super(name,picture);
        setLife(life);
        setMaxLife(maxLife);
        setStamina( stamina);
        setMaxStamina(maxStamina);
    }


    }

