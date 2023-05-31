package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import helpers.AllAssets;
import shoot_em_up.ShootEmUP;


public class Loading extends ScreenAdapter implements Screen {
    final ShootEmUP game;
    OrthographicCamera camera;
    Texture backgroundTexture;
    BitmapFont fontBoutton;
   ShapeRenderer shape;

    private Viewport viewport;

    int x,y ;

    Skin skin;
    Label label;
    private final AllAssets assets;

    //Pour pouvoir, obtenir tous les assets mis en parametres dans le constructeur
    public AllAssets getAssets() {
        return assets;
    }

    public Loading(final ShootEmUP game, AllAssets assets) {

        //pouvoir garder les assets mis en parametres
        this.assets = assets;

        this.game = game;

        //instanciation et  configuration de la caméra avec une projection orthographique
        //Elle capture une vue plate de la scène, où les objets à l'écran apparaissent à la même échelle,
        // quelle que soit leur distance par rapport à la caméra
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        //pour avoir le font par defaut
        fontBoutton = new BitmapFont();

        //pour l'image en background
        backgroundTexture = getAssets().getLogo();

        //Pour la forme (rectangle)
        shape = new ShapeRenderer();

        //Pour l'iteration(ici en pourcentage)
        x = 0;

        //pour avoir le skin du jeu
        skin = getAssets().getSkin();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 0);

        //Mise a jour du camera et prise en compte de toutes nouvelles modifications
        camera.update();

        //configuration de la matrice de projection (batch) avec la matrice de projection combinée de la caméra
        game.batch.setProjectionMatrix(camera.combined);

        //le batch peut commencer à dessiner sur l'ecran
        game.batch.begin();

        //Pour avoir une bonne qualité de la texture
        backgroundTexture.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);

        //Dessin
        game.batch.draw(backgroundTexture,0,0,Gdx.graphics.getWidth()-200,Gdx.graphics.getHeight()-500);

        //Fin
        game.batch.end();

        //Pour la vitesse du loading
        x += 3;


        int width = 500;
        int pourcentage = (x * 100) / width;
        float posX = (float) (Gdx.graphics.getWidth()) / 4;
        float height = 40;
        float posY = height + 10;

        label = new Label("LOADING "+ pourcentage +" % ",skin);
        label.setPosition(posX + 85, posY );

        //Pour pouvoir dessiner le label
        game.batch.begin();
        label.draw(game.batch,1f);
        game.batch.end();

        //commencement du dessin des shapes
        shape.begin(ShapeRenderer.ShapeType.Filled);

        //pour la couleur
        shape.setColor(Color.WHITE);

        //Pour avoir un rectangle
        shape.rect(posX, posY, width,height);

        //pour pouvoir s arreter et aussi passer a l'ecran suivante
        if(x > width)
            x = width;
        if(x == width ) {
            game.setScreen(new  GameScreen(game, getAssets()));
            dispose();
        }

        //Creation d un nouveau rectangle de couleur rouge pendant le chargement
        else shape.setColor(Color.RED);
        shape.rect(posX + 10,posY + 10 ,x - 20,height - 20);
        shape.end();

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