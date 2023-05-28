package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import exceptions.NoWeaponExeption;
import weapon.Weapon;
import weapon.ammo.Ammo;
import com.badlogic.gdx.graphics.Texture;

//La généralité
abstract public class Spacecraft {

    //Les attributs
    protected String name;
    protected float puissance ,maxPuissance;
    protected float posX,posY;
    protected Texture picture;
    private Weapon weapon;
    private SpriteBatch batch;



    //Les méthodes associées
    public String getName(){ return this.name;}
    public void setName(String name){ this.name = name;}
    public float getPuissance() {
        return puissance;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public void setPuissance(float puissance) {
        this.puissance = (puissance < 0) ? 0 : puissance;
    }

    public float getMaxPuissance() {
        return maxPuissance;
    }

    public void setMaxPuissance(int maxPuissance) {
        this.maxPuissance = maxPuissance;
    }

    public float getPosX() { return posX;}
    public void setPosX(float posX) { this.posX = posX;}
    public float getPosY() { return posY;}
    public void setPosY(float posY) { this.posY = posY;}
    public Texture getPicture() { return picture;}

    public Weapon getWeapon() throws NoWeaponExeption {
        if(weapon == null)
            throw new NoWeaponExeption();
        return weapon;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public void setPicture(Texture picture) { this.picture = picture;}

    public boolean isNotDestroyed(){ return getPuissance()>0;} //si le vaisseau est detruit ou pas !

    //Les méthodes abstraites
    public abstract void move(Spacecraft spacecraft);
    abstract public void shotBy(Ammo ammo);//car le vaisseau du héro peut avoir un bouclier

    //Le constructeur
    public Spacecraft(String name, SpriteBatch batch){
        setName(name);

        setBatch(batch);
    }


    //Affichage des statistiques
    @Override
    public String toString() {
        return String.format(" Name :%s Puissance :%d MaxPuissance :%d \n", getName(),getPuissance(),getMaxPuissance());
    }


}
