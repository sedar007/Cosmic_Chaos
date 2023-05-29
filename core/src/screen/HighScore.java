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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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

    Label label;
    OrthographicCamera camera;
    public HighScore(final ShootEmUP game, AllAssets assets ){
        this.game = game;
        this.assets = assets;
        skin = this.assets.getSkin();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        this.filesJson = new FilesJson();

        stage = new Stage(new ScreenViewport());

        String tmp = "";
        for (Object cle : filesJson.readJson().keySet()) {
            tmp = tmp + cle + " -> " + filesJson.readJson().get(cle) + "\n";
        }
        label = new Label(tmp, skin);
        label.setPosition(100, 100);

        Gdx.input.setInputProcessor(stage);
    }
    public AllAssets getAssets() {
        return assets;
    }

    public void render(float delta) {
       /* ScreenUtils.clear(0, 0, 0.2f, 1);
       camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        String tmp = "";
        for (Object cle : filesJson.readJson().keySet()) {
            tmp = tmp + cle + " -> " + filesJson.readJson().get(cle) + "\n";
        }
        Label label = new Label(tmp, skin);
      /*  float x = (float) (Gdx.graphics.getWidth() /2 )- (label.getWidth() / 2) ;
        float y = (float)(Gdx.graphics.getHeight() /2) - (label.getHeight()/ 2) ;
        label.setPosition( 100,100);
        label.setFontScale(1.0f, 1.0f);

        game.batch.begin();
        label.draw(game.batch,1f);
        game.batch.end();*/
        ScreenUtils.clear(0, 0, 0, 0);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    public void show(){
        stage.addActor(label);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);


        skin = getAssets().getSkin();
        //creer le bouton
        TextButton back = new TextButton("BACK", skin);

        back.setColor(Color.RED);

        table.add(back).fillX().uniformX();

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game, getAssets()));
            }
        });
    }

    public void resize(int width, int height) {
        // change the stage's viewport when teh screen size is changed
        stage.getViewport().update(width, height, true);
    }
    public void dispose() {
        // dispose of assets when not needed anymore
        stage.dispose();
        skin.dispose(); // Libère la ressource du skin

    }

}
