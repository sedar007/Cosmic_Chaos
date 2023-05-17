package spacecraft;


import com.badlogic.gdx.graphics.Texture;

//La généralité
abstract public class Spacecraft {

    //Les attributs
    protected String name;
    protected int life,maxLife,stamina,maxStamina;
    protected float posX,posY;
    protected Texture picture;


    //Les méthodes associées
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
    public float getPosX() { return posX;}
    public void setPosX(float posX) { this.posX = posX;}
    public float getPosY() { return posY;}
    public void setPosY(float posY) { this.posY = posY;}
    public Texture getPicture() { return picture;}
    public void setPicture(Texture picture) { this.picture = picture;}
    public boolean isNotDestroyed(){ return getLife()>0;}

    //Les méthodes abstraites
    public abstract void move();

    //Le constructeur
    public Spacecraft(String name,Texture picture){
        picture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); // optionnel : améliore la qualité de l'image redimensionnée
        setName(name);
        setPicture(picture);
    }

    //Affichage des statistiques
    @Override
    public String toString() {
        return String.format(" Name :%s Life :%d MaxLife :%d Stamina :%d MaxStamina :%d \n", getName(),getLife(),getMaxLife(),getStamina(),getMaxStamina());
    }


}
