package spacecraft;


//La généralité
public class Spacecraft {

    protected String name;
    protected int life;
    protected int maxLife;
    protected int stamina;
    protected int maxStamina;


    public Spacecraft(String name){
        this.name = name;
        this.life = 100;
        this.maxLife = 100;
    }
    public Spacecraft(){

    }



}
