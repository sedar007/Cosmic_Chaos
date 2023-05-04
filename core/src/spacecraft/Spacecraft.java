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

    public Spacecraft(String name, int life, int maxLife, int stamina,int maxStamina){
        setName(name);
        setLife(life);
        setMaxLife(maxLife);
        setStamina(stamina);
        setMaxStamina(maxStamina);
    }

    public Spacecraft(String name){
        this("Vessel",100,100,50,50);
    }

    @Override
    public String toString() {
        return String.format(" Name :%s Life :%d MaxLife :%d Stamina :%d MaxStamina :%d \n", getName(),getLife(),getMaxLife(),getStamina(),getMaxStamina());
    }
}
