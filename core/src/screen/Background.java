package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.AllAssets;

public class Background {
    private final Texture texture;
    private final float scrollSpeed;
    private float layer1, layer2;
    private final Texture backgroundP;

    public Background(Texture texturePath, float scrollSpeed, OrthographicCamera camera, AllAssets assets) {

        // recupere les assets et le jeu

        //Le path de l'image
        texture = texturePath;

        //pour l'image en background
        backgroundP = assets.getBackGroundPicture();

        //Vitesse du scroll
        this.scrollSpeed = scrollSpeed;


        layer1 = 0;
        layer2 = texture.getWidth();
    }

    public void update(SpriteBatch batch, float delta) {

        //delta : Le temps generé par l'affichage du screen
        layer1 -= scrollSpeed * delta;
        layer2 -= scrollSpeed * delta;

        //si l'image arrive en bas donc on remonte en haut
        if (layer1 + texture.getHeight() < 0) {
            layer1 = layer2 + texture.getHeight();
        }
        if (layer2 + texture.getHeight() < 0) {
            layer2 = layer1 + texture.getHeight();
        }

        //Dessin
        batch.begin();

        //dessin du background
        batch.draw(backgroundP,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        //dessin des textures  avec une vitesse layer1 et layer2
        batch.draw(texture, 0, layer1,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(texture, 0, layer2,Gdx.graphics.getWidth(), texture.getHeight());

        batch.end();

    }

}
