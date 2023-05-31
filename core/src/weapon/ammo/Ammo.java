package weapon.ammo;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.AllAssets;

public class Ammo {
    protected float degats;
    private int speed;
    private Texture image;
    private String name;
    private float xPosition, yPosition;
    protected SpriteBatch batch;
    private AllAssets assets;

    // Static
    public static final String DEFAULT_NAME = "ammo";

    // Getters et Setters
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private void setDegats(float degats) {
        this.degats = degats;
    }
    public float getDegats() {
        return this.degats;
    }
    public void setImage(Texture image) {
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
    public SpriteBatch getBatch() {
        return batch;
    }
    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public AllAssets getAssets() {
        return assets;
    }

    // Constructors
    public Ammo(String name, float degats, int speed, float xPosition, float yPosition, SpriteBatch batch, AllAssets assets, Texture picture) {
        this.assets = assets;
        setName(name);
        setDegats(degats);
        setImage(picture);

        setxPosition(xPosition - (float) this.getImage().getWidth() / 2);
        setyPosition(yPosition);
        setSpeed(speed);
        setBatch(batch);
    }

    public Ammo() {
        setName(DEFAULT_NAME);
        setImage(getAssets().getRocketPicture());
        setDegats(0);
        setxPosition(0);
        setyPosition(0);
    }

     // Methodes Abstraites

     public void move() {
        setyPosition(getyPosition() + getSpeed());
        getBatch().begin();
        getBatch().draw(this.getImage(), this.getxPosition(), this.getyPosition());
        getBatch().end();

     }


}