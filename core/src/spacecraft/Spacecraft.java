package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import weapon.Weapon;
import weapon.ammo.Ammo;
import com.badlogic.gdx.graphics.Texture;

//La généralité
abstract public class Spacecraft {

    //Les attributs
    protected String name;
    protected int puissance ,maxPuissance;
    protected float posX,posY;
    protected Texture picture;
    private Weapon weapon;


    //Les méthodes associées
    public String getName(){ return this.name;}
    public void setName(String name){ this.name = name;}
    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = (puissance < 0) ? 0 : puissance;
    }

    public int getMaxPuissance() {
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

    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public void setPicture(Texture picture) { this.picture = picture;}

    public boolean isNotDestroyed(){ return getPuissance()>0;} //si le vaisseau est detruit ou pas !

    //Les méthodes abstraites
    public abstract void move(SpriteBatch spriteBatch,Spacecraft spacecraft);
    abstract public void shotBy(Ammo ammo);//car le vaisseau du héro peut avoir un bouclier

    //Le constructeur
    public Spacecraft(String name,String picture){
        setName(name);
        this.picture = new Texture(picture);
        this.picture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); // optionnel : améliore la qualité de l'image redimensionnée
    }


    //Affichage des statistiques
    @Override
    public String toString() {
        return String.format(" Name :%s Puissance :%d MaxPuissance :%d \n", getName(),getPuissance(),getMaxPuissance());
    }


}
