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
import helpers.AllAssets;
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
        // Enregistre tous les assets
        this.assets = assets;

        this.game = game;

       //instanciation et  configuration de la caméra avec une projection orthographique
        //Elle capture une vue plate de la scène, où les objets à l'écran apparaissent à la même échelle,
        // quelle que soit leur distance par rapport à la caméra
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000, 1000);

        fontBoutton = new BitmapFont();//pour le font par défaut !

        //pour l'image en background
        backgroundTexture = getAssets().getMainMenuScreenPicture();
        image = new Image(backgroundTexture);

        //instanciation du stage !
        stage = new Stage(new ScreenViewport());


        // mettre en plein ecran l image
        image.setWidth(stage.getWidth());
        image.setHeight(stage.getHeight());

        //le stage doit être le gestionnaire d'entrée principal de l' application
        //et recevra les événements d'entrée et les transmettra aux acteurs(boutons,...)
        Gdx.input.setInputProcessor(stage);

        //pour avoir le skin du jeu
        skin = getAssets().getSkin();


    }

    @Override
    public void show() {

        //Ajout des acteurs dans le stage
        stage.addActor(image);

        //Instanciation  de la table qui va contenir les boutons
        Table table = new Table();

        // Table occupe tout l'espace disponible dans son conteneur parent, en s'adaptant automatiquement à sa taille
        table.setFillParent(true);
        stage.addActor(table);

        //creation des boutons
        final TextButton start = new TextButton("START", skin);
        TextButton highscore = new TextButton("HIGH SCORE", skin);
        TextButton exit = new TextButton("Exit", skin);

        //pour la couleur du bouton (contour)
        start.setColor(Color.RED);
        exit.setColor(Color.RED);
        highscore.setColor(Color.RED);

        //ajout des boutons  dans la table
        table.add(start).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.row();
        table.add(highscore).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.row();
        table.add(exit).fillX().uniformX();

        // les actions qui vont se dérouler une fois que les boutons seront cliqués
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new Loading(game, getAssets()));

            }
        });

        highscore.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new HighScore(game, getAssets()));
            }
        });


    }

    @Override
    public void render(float delta) {
        // Nettoyer l'ecran
        ScreenUtils.clear(0, 0, 0, 0);

        //Pour ordonner le stage d'effectuer les actions demandées
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Pour changer le viewport du stage quand la taille de l ecran change aussi
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
        //pour le nettoyage une fois qu on aura plus besoin des elements
        stage.dispose();
    }

}