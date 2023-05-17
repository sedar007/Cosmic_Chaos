package ammo;

import com.badlogic.gdx.graphics.Texture;

public class Ammo {
    private int degats;
    private Texture image;
    private String name;
    private int xPosition,yPosition;
    public Ammo(String name, Texture image, int degats){
        this.name=name;
        this.image=image;
        this.degats=degats;
        this.xPosition=xPosition;
       this.yPosition=yPosition;
    }
}
