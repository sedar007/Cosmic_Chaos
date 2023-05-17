package weapon.ammo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Ammo {
    protected int degats;
    protected Texture image;
    protected String name;
    private float xPosition,yPosition;
    private int speed = 5;
    Music soung;


    private void setDegats(int degats) {
        this.degats = degats;
    }
    public int getDegats(){ return this.degats;}

    private void setImage(Texture image) {
        this.image = image;
    }

    public Texture getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public float getxPosition() {
        return xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public Ammo(String name, String image, int degats, float xPosition, float yPosition){
        setName(name);
        setImage(new Texture(image));
        setDegats(degats);
        setxPosition(xPosition - this.getImage().getWidth() / 2);
        setyPosition(yPosition);
    }

    public void move(){
        setxPosition(getxPosition());
        setyPosition(getyPosition() + speed);

    }
}
