package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import shoot_em_up.ShootEmUP;

import javax.swing.*;

public class MainMenuScreen extends ScreenAdapter implements Screen {
    final ShootEmUP game;
    OrthographicCamera camera;
    Texture backgroundTexture;
    BitmapFont fontBoutton;
////////////////////////////////////////////

    private Stage stage;
    private Viewport viewport;



    public MainMenuScreen(final ShootEmUP game) {
       this.game = game;
    camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        fontBoutton = new BitmapFont();
        backgroundTexture = new Texture(Gdx.files.internal("pictures/logogame.jpeg"));
        ////////////////////////////////////////////


    }

    @Override
    public void show() {
        stage = new Stage();
        viewport = new ExtendViewport(1280,720);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
      camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        backgroundTexture.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        game.batch.draw(backgroundTexture,0,0,Gdx.graphics.getWidth()-200,Gdx.graphics.getHeight()-500);
        game.font.draw(game.batch, "Tap anywhere to begin!", 50, 40);
        game.batch.end();
       if (Gdx.input.isTouched()) {
            game.setScreen(new  GameScreen(game));
            dispose();
        }
        ////////////////////////////////////////////






    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
