package spacecraft;

public class Knight extends Spacecraft {
    public Knight (String name,int life,int maxLife,int stamina,int maxStamina){
        super(name);
        setLife(life);
        setMaxLife( maxLife);
        setStamina( stamina);
        setMaxStamina( maxStamina);
    }
}
