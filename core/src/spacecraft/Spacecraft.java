package spacecraft;


//La généralité
public class Spacecraft {

    protected String name;
    protected int life;
    protected int maxLife;
    protected int stamina;
    protected int maxStamina;

    public String getName(){ return this.name;}
    public void setName(String name){ this.name = name;}

    public int getLife() { return this.life;}
    public void setLife(int life){ this.life = life;}

    public int getStamina(){ return this.stamina;}
    public void setStamina(int stamina){ this.stamina = stamina;}

    public int getMaxLife(){ return this.maxLife;}
    public void setMaxLife(int maxLife) {this.maxLife = maxLife;}

    public int getMaxStamina(){ return this.maxStamina;}
    public void setMaxStamina(int maxStamina) {this.maxStamina = maxStamina;}

    public Spacecraft(String name){
        setName(name);
    }

    public Spacecraft(){
        this("Vessel");
    }



}
