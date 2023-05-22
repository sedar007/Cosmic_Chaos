package weapon.ammo;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.MoveManager;

    public class Ammo implements MoveManager {
    protected int degats;
    private int speed;
    private Texture image;
    private String name;
    private float xPosition, yPosition;
    protected SpriteBatch batch;

    // Static
    public static final String DEFAULT_PICTURE = "pictures/projectiles/rocket.png";
    public static final String DEFAULT_NAME = "ammo";

    // Getters et Setters
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

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
    public SpriteBatch getBatch() {
        return batch;
    }
    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }


    // Constructors
    public Ammo(String name, String image, int degats, int speed, float xPosition, float yPosition, SpriteBatch batch) {
        setName(name);
        setImage(new Texture(image));
        setDegats(degats);
        setxPosition(xPosition - (float) this.getImage().getWidth() / 2);
        setyPosition(yPosition);
        setSpeed(speed);
        setBatch(batch);
    }

    public Ammo() {
        setName(DEFAULT_NAME);
        setImage(new Texture(DEFAULT_PICTURE));
        setDegats(0);
        setxPosition(0);
        setyPosition(0);
    }

     // Methodes Abstraites
     @Override
     public void move() {
        setyPosition(getyPosition() + getSpeed());
        getBatch().draw(this.getImage(), this.getxPosition(), this.getyPosition());
     }


}