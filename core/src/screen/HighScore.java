package screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import shoot_em_up.ShootEmUP;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class HighScore extends ScreenAdapter implements Screen{
    Json json;
   // HashMap<Integer, Double> jsonData;
    HashMap<Integer, Double> backup;
    HashMap data ;
    FileHandle file;
    String jsonString;
    final ShootEmUP game;
    Skin skin;
    Double lastScoreGame;
    private AllAssets assets;
    FilesJson filesJson;
    private final Stage stage;
    Label title,label;
    OrthographicCamera camera;
    Texture backgroundTexture;
    Image image;
    public HighScore(final ShootEmUP game, AllAssets assets ){

        this.game = game;

        //pouvoir garder les assets mis en parametres
        this.assets = assets;

        //pour avoir le skin du jeu
        skin = this.assets.getSkin();

        //instanciation et  configuration de la caméra avec une projection orthographique
        //Elle capture une vue plate de la scène, où les objets à l'écran apparaissent à la même échelle,
        // quelle que soit leur distance par rapport à la caméra
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        //instanciation du stage !
        stage = new Stage(new ScreenViewport());

        //pour l'image en background
        backgroundTexture = getAssets().getHighScore();
        image = new Image(backgroundTexture);

        //Modification de la taille de l image pour bien adapter au stage
        image.setWidth(stage.getWidth());
        image.setHeight(stage.getHeight());

        //instanciation du fichiers
        this.filesJson = new FilesJson();

        //Pour le titre
        title = new Label("HIGH SCORE :", skin);

        //Stockage des donnees dans le fichier json
        String tmp = " ";
        for (Object cle : filesJson.readJson().keySet()) {
            tmp = tmp + cle + " - " + filesJson.readJson().get(cle) + "\n\n";
        }

        //On recupere (string) puis on le met dans un label pour pouvoir l'integrer au stage !
        label = new Label(tmp, skin);

        //modification de la taille du texte
        title.setFontScale(2.0f, 2.0f);
        label.setFontScale(1.5f, 1.5f);

        //Pour les mesures x,y par rapport au screen
        float x = (float) (Gdx.graphics.getWidth() /2 )- (label.getWidth() / 2) ;
        float y = (float)(Gdx.graphics.getHeight() /2) - (label.getHeight()/ 2) ;

        //On place le title et label dans les positions adequates
        title.setPosition(x - label.getWidth() ,y + label.getHeight() + 100);
        label.setPosition(x, y);

        //le stage doit être le gestionnaire d'entrée principal de l' application
        //et recevra les événements d'entrée et les transmettra aux acteurs(boutons,...)
        Gdx.input.setInputProcessor(stage);

    }

    public AllAssets getAssets() {
        return assets;
    }

    public void render(float delta) {
        // Nettoyer l'ecran
        ScreenUtils.clear(0, 0, 0, 0);

        //Pour ordonner le stage d'effectuer les actions demandées
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    public void show(){

        //Ajout des acteurs dans le stage
        stage.addActor(image);
        stage.addActor(title);
        stage.addActor(label);

        //Instanciation et positionnement de la table qui va contenier le bouton
        Table table = new Table();
        table.setPosition(100,100);

        //Ajout du table comme acteur dans le stage
        stage.addActor(table);

        //pour avoir le skin du jeu
        skin = getAssets().getSkin();

        //creer le bouton et donner la couleur
        TextButton back = new TextButton("BACK", skin);
        back.setColor(Color.RED);

        //ajout du bouton  dans la table
        table.add(back).fillX().uniformX();

        // l' actions qui va se dérouler une fois que le bouton sera cliqué
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game, getAssets()));
            }
        });
    }

    public void resize(int width, int height) {
        // Pour changer le viewport du stage quand la taille de l ecran change aussi
        stage.getViewport().update(width, height, true);
    }
    public void dispose() {
        //pour le nettoyage une fois qu on aura plus besoin des elements
        stage.dispose();

        // Libère la ressource du skin
        skin.dispose();

    }

}
