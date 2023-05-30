package screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import shoot_em_up.ShootEmUP;

public class MainMenuScreen implements Screen{

    private final AllAssets assets; // Toutes les images


    private final Stage stage;
    final ShootEmUP game;
    OrthographicCamera camera;
    Texture backgroundTexture;
    BitmapFont fontBoutton;
    Image image ;
    Skin skin;

    public AllAssets getAssets() {
        return assets;
    }//pour recuperer les assets mis en param


    public MainMenuScreen(final ShootEmUP game, AllAssets assets){
        // Telecharge toutes les assets
        this.assets = assets;

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000, 1000);

        fontBoutton = new BitmapFont();

        backgroundTexture = getAssets().getMainMenuScreenPicture();
        image = new Image(backgroundTexture);
        stage = new Stage(new ScreenViewport());


        // mettre en plein ecran l image
        image.setWidth(stage.getWidth());
        image.setHeight(stage.getHeight());

        /// create stage and set it as input processor
        Gdx.input.setInputProcessor(stage);
        skin = getAssets().getSkin();


    }

    @Override
    public void show() {

        stage.addActor(image);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //creer les boutons
        TextButton start = new TextButton("START", skin);
        TextButton highscore = new TextButton("HIGH SCORE", skin);
        TextButton exit = new TextButton("Exit", skin);

        start.setColor(Color.RED);
        exit.setColor(Color.RED);
        highscore.setColor(Color.RED);

        //ajout dans la table
        table.add(start).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.row();
        table.add(highscore).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.row();
        table.add(exit).fillX().uniformX();
        // create button listeners
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Loading(game, getAssets()));
                dispose();
            }
        });

        highscore.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new HighScore(game, getAssets()));
                dispose();
            }
        });


    }

    @Override
    public void render(float delta) {
        // tell our stage to do actions and draw itself
        ScreenUtils.clear(0, 0, 0, 0); // Nettoyer l'ecran

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


    }

    @Override
    public void resume() {


    }

    @Override
    public void hide() {


    }

    @Override
    public void dispose() {
        // dispose of assets when not needed anymore
        backgroundTexture.dispose(); // Libérer la texture du fond
        stage.dispose();
    }

}