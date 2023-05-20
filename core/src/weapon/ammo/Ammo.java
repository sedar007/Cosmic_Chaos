package weapon.ammo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

abstract public class Ammo {
    protected int degats;
    private int speed;
    protected Texture image;
    protected String name;
    private float xPosition, yPosition;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public static final String DEFAULT_PICTURE = "pictures/projectiles/rocket.png";
    public static final String DEFAULT_NAME = "ammo";

    private void setDegats(int degats) {
        this.degats = degats;
    }

    public int getDegats() {
        return this.degats;
    }

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

    public Ammo(String name, String image, int degats, int speed, float xPosition, float yPosition) {
        setName(name);
        setImage(new Texture(image));
        setDegats(degats);
        setxPosition(xPosition - (float) this.getImage().getWidth() / 2);
        setyPosition(yPosition);
        setSpeed(speed);
    }

    public Ammo() {
        setName(DEFAULT_NAME);
        setImage(new Texture(DEFAULT_PICTURE));
        setDegats(0);
        setxPosition(0);
        setyPosition(0);
    }

    abstract public void move();
}