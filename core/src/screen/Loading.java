package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import shoot_em_up.ShootEmUP;


public class Loading extends ScreenAdapter implements Screen {
    final ShootEmUP game;
    OrthographicCamera camera;
    Texture backgroundTexture;
    BitmapFont fontBoutton;
////////////////////////////////////////////
   ShapeRenderer shape;

    private Viewport viewport;

    int x ;
    int y ;

    public Loading(final ShootEmUP game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        fontBoutton = new BitmapFont();
        backgroundTexture = new Texture(Gdx.files.internal("pictures/logogame.jpeg"));
        ////////////////////////////////////////////
        shape = new ShapeRenderer();
        x = 0;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        backgroundTexture.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        game.batch.draw(backgroundTexture,0,0,Gdx.graphics.getWidth()-200,Gdx.graphics.getHeight()-500);

        game.batch.end();

        x += 1;
        int width = 500;
        int pourcentage = (x * 100) / width;
        float posX = (float) (Gdx.graphics.getWidth()) / 4;
        float height = 40;
        float posY = height + 10;

        game.batch.begin();
        game.font.draw(game.batch, "L O A D I N G  "+ pourcentage +" % ", posX + 100, posY + 10);
        game.batch.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.WHITE);
        shape.rect(posX, posY, width,height);
        if(x == width ) {
            game.setScreen(new  GameScreen(game));
            dispose();
        }
        else shape.setColor(Color.RED);
        shape.rect(posX + 10,posY + 10 ,x - 20,height - 20);
        shape.end();

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