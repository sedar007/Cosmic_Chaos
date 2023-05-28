package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private final Texture texture;
    private float scrollSpeed;
    private float layer1, layer2;
    private OrthographicCamera camera;
    private Texture backgroundP;
    private AllAssets assets;

    public Background(Texture texturePath, float scrollSpeed, OrthographicCamera camera, AllAssets assets) {
        this.assets = assets;
        texture = texturePath;
        backgroundP = assets.getBackGroundPicture();

        this.scrollSpeed = scrollSpeed;
        this.camera = camera;
        layer1 = 0;
        layer2 = texture.getWidth();
    }

    public void update(SpriteBatch batch, float delta) {
        layer1 -= scrollSpeed * delta;
        layer2 -= scrollSpeed * delta;
        if (layer1 + texture.getHeight() < 0) {
            layer1 = layer2 + texture.getHeight();
        }
        if (layer2 + texture.getHeight() < 0) {
            layer2 = layer1 + texture.getHeight();
        }

        batch.begin();
        batch.draw(backgroundP,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(texture, 0, layer1,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(texture, 0, layer2,Gdx.graphics.getWidth(), texture.getHeight());
        batch.end();
    }

}
