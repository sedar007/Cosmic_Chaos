package ammo;

import com.badlogic.gdx.graphics.Texture;

public class Ammo {
    protected int degats;
    protected Texture image;
    protected String name;
    protected int xPosition,yPosition;

    private void setDegats(int degats) {
        this.degats = degats;
    }
    public int getDegats(){ return this.degats;}

    private void setImage(Texture image) {
        this.image = image;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    private void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public Ammo(String name, Texture image, int degats, int xPosition, int yPosition){
        setName(name);
        setImage(image);
        setDegats(degats);
        setxPosition(xPosition);
        setyPosition(yPosition);
    }
}
