package screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import shoot_em_up.ShootEmUP;

public class TestButton implements Screen{

    private Stage stage;
    final ShootEmUP game;
    OrthographicCamera camera;
    //Texture backgroundTexture;
    BitmapFont fontBoutton;
    Image image ;

    public TestButton(final ShootEmUP game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        fontBoutton = new BitmapFont();
        /*backgroundTexture = new Texture(Gdx.files.internal("pictures/logogame.jpeg"));
        image = new Image(backgroundTexture);*/
        stage = new Stage(new ScreenViewport());

        /*image.setWidth(stage.getWidth());
        image.setHeight(stage.getHeight());*/

        /// create stage and set it as input processor

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
       // stage.addActor(image);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("skin/skin-composer-ui.json"));
         skin = new Skin(Gdx.files.internal("skin2/star-soldier-ui.json"));


        //create buttons
        TextButton newGame = new TextButton("New Game", skin);
        //TextButton preferences = new TextButton("Preferences", skin);
        TextButton exit = new TextButton("Exit", skin);
//newGame.setColor(Color.RED);
        //add buttons to table
        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
       // table.add(preferences).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();

        // create button listeners
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });


    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn


        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when teh screen size is changed
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // dispose of assets when not needed anymore
        stage.dispose();
    }

}