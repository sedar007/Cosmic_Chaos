package spacecraft;

public class Monster extends Spacecraft {

    public Monster(String name,int life,int maxLife,int stamina,int maxStamina){
        super(name);
        setLife(life);
        setMaxLife(maxLife);
        setStamina( stamina);
        setMaxStamina(maxStamina);
    }


    }

