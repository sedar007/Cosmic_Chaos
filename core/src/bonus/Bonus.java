package bonus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.AllAssets;
import spacecraft.Skyblade;

abstract public class Bonus {
    protected String name;
    private Texture picture;
    protected Skyblade skyblade;

    protected SpriteBatch batch;

    private float posX,posY;
    private float timesOut;
    private float maxTimeOut;
    private AllAssets assets;

    private float bonus;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Skyblade getSkyblade() {
        return skyblade;
    }
    public void setSkyblade(Skyblade skyblade) {
        this.skyblade = skyblade;
    }

    public Texture getPicture() {
        return picture;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void setPicture(Texture picture) {
        this.picture = picture;
    }

    public AllAssets getAssets() {
        return assets;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Bonus(String name) {
       setName(name);
    }
    public Bonus(String name, Skyblade skyblade, float x, float y, SpriteBatch batch, AllAssets assets) {
        this.assets = assets;
        setName(name);
        setSkyblade(skyblade);
        setPosX(x);
        setPosY(y);
        this.batch = batch;
        this.maxTimeOut = 7f;
    }
    //methodes abstraites
    abstract public void collect();


    public void draw(){
        this.batch.begin();
        this.batch.draw(getPicture(),getPosX(),getPosY());
        this.timesOut = this.timesOut + Gdx.graphics.getDeltaTime();
        this.batch.end();
    }

    public boolean timeOut(){
        return timesOut >= maxTimeOut;
    }//pour svoir si le temps est superieur ou bien égale au temps max

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public float getBonus() { return bonus;}

}
