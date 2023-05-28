
package screen;


        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Screen;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.graphics.g2d.GlyphLayout;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.scenes.scene2d.Actor;
        import com.badlogic.gdx.scenes.scene2d.Stage;
        import com.badlogic.gdx.scenes.scene2d.ui.*;
        import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
        import com.badlogic.gdx.utils.viewport.ScreenViewport;
        import shoot_em_up.ShootEmUP;
        import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Endgame implements Screen {

    private final Stage stage;
    final ShootEmUP game;
    OrthographicCamera camera;
    Texture backgroundTexture;
    BitmapFont fontBoutton;
    BitmapFont statistics;

    LabelStyle style;
    Label label;
    Image image ;
    Skin skin;
    private final AllAssets assets;

    public AllAssets getAssets() {
        return assets;
    }

    public Endgame(final ShootEmUP game, String comment, AllAssets assets ){
        this.assets = assets;
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        fontBoutton = new BitmapFont();
        // temporary until we have asset manager in
        skin = getAssets().getSkin();

        //ajout de texte dans le stage
       /* statistics =  new BitmapFont();
        statistics.getData().setScale(1.5f); // Définissez l'échelle de la police pour augmenter ou diminuer la taille
        style = new LabelStyle();
        style.font = statistics;*/

        float x = ((float) Gdx.graphics.getWidth()  / 4); // Coordonnée x
        float y =  ((float) Gdx.graphics.getHeight()  / 2) + 400; // Coordonnée y

        label = new Label(comment,skin);
        label.setPosition(x, y);


        ////
        backgroundTexture = getAssets().getEndGamePicture();
        image = new Image(backgroundTexture);
        stage = new Stage(new ScreenViewport());

        image.setWidth(stage.getWidth());
        image.setHeight(stage.getHeight());

        /// create stage and set it as input processor

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        stage.addActor(image);
        stage.addActor(label);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);



        //create buttons
        TextButton restart = new TextButton("RESTART", skin);
       TextButton menu = new TextButton("MENU", skin);
        TextButton exit = new TextButton("Exit", skin);


        restart.setColor(Color.RED);
        exit.setColor(Color.RED);
        menu.setColor(Color.RED);
        //add buttons to table
        table.add(restart).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.row();
        table.add(menu).fillX().uniformX();
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

        restart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Loading(game, getAssets()));
            }
        });
        menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });


    }

    @Override
    public void render(float delta) {
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

        stage.dispose();
        fontBoutton.dispose(); // Libère la ressource de la police de caractères
        statistics.dispose(); // Libère la ressource de la police de caractères
        skin.dispose(); // Libère la ressource du skin
        backgroundTexture.dispose(); // Libère la ressource de la texture
    }

}