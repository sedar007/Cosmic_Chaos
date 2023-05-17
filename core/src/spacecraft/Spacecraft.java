package spacecraft;


import ammo.Ammo;
import com.badlogic.gdx.graphics.Texture;

//La généralité
abstract public class Spacecraft {

    //Les attributs
    protected String name;
    protected int puissance ,maxPuissance;
    protected float posX,posY;
    protected Texture picture;
    private Ammo ammo;

    private boolean Protected ;

    //Les méthodes associées
    public String getName(){ return this.name;}
    public void setName(String name){ this.name = name;}
    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getMaxPuissance() {
        return maxPuissance;
    }

    public void setMaxPuissance(int maxPuissance) {
        this.maxPuissance = maxPuissance;
    }

    public void setProtected(boolean aProtected) {
        Protected = aProtected;
    }

    public float getPosX() { return posX;}
    public void setPosX(float posX) { this.posX = posX;}
    public float getPosY() { return posY;}
    public void setPosY(float posY) { this.posY = posY;}
    private Ammo getAmmo() {
        return ammo;
    }
    private void setAmmo(Ammo ammo) {
        this.ammo = ammo;
    }
    public boolean isNotDestroyed(){ return getPuissance()>0;}

    //Les méthodes abstraites
    public abstract void move();
    abstract public int shotBy(int shot);//car le vaisseau du héro peut avoir un bouclier

    //Le constructeur
    public Spacecraft(String name,String picture){
        setName(name);
        this.Protected = false;
        this.picture = new Texture(picture );
        this.picture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); // optionnel : améliore la qualité de l'image redimensionnée
    }

    public boolean isProtected(){ return this.Protected; }
    public int shotWith(Ammo ammo){//Le vaisseau tiré avec l ammo
        return ammo.getDegats();
    }

    //Affichage des statistiques
    @Override
    public String toString() {
        return String.format(" Name :%s Puissance :%d MaxPuissance :%d \n", getName(),getPuissance(),getMaxPuissance());
    }


}
