package gift;

import com.badlogic.gdx.graphics.Texture;

public class Gift {
    protected String name;
    protected Texture picture;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Texture getPicture() {
        return picture;
    }
    public void setPicture(Texture picture) {
        this.picture = picture;
    }

    public Gift(String name) {
       setName(name);
    }
    public Gift(String name, Texture picture) {
        setName(name);
        setPicture(picture);
    }
}
