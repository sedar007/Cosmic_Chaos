package screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
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
    public HighScore(final ShootEmUP game, Double lastScore, AllAssets assets ){
        this.game = game;
        this.assets = assets;
        skin = this.assets.getSkin();
       // jsonData = new HashMap<>();

       /* lastScoreGame = lastScore;
        jsonData.put(1, 0.0);
        jsonData.put(2, 0.0);
        jsonData.put(3, 0.0);
        jsonData.put(4, 0.0);
        jsonData.put(5, 0.0);
        jsonString = json.toJson(jsonData);
        file.writeString(jsonString, false); */


    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
       /* camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        changeScore(lastScoreGame);
        Label label = new Label(getJsonString(),skin);
        float x = (float) (Gdx.graphics.getWidth() /2 )- (label.getWidth() / 2) ;
        float y = (float)(Gdx.graphics.getHeight() /2) - (label.getHeight()/ 2) ;
        label.setPosition( x,y);
        label.setFontScale(1.0f, 1.0f);

        game.batch.begin();
        label.draw(game.batch,1f);
        game.batch.end();*/
    }


/*
    public String getJsonString(){
        String tmp =" ";
        for (Integer cle : jsonData.keySet()) {
            tmp = tmp + cle + " -> " + jsonData.get(cle) + "\n";
        }
        return tmp;
    }

    public void initHighScore(){//pour réinitialiser la liste des meilleurs scores
        jsonData.put(1, 0.0);
        jsonData.put(2, 0.0);
        jsonData.put(3, 0.0);
        jsonData.put(4, 0.0);
        jsonData.put(5, 0.0);
        jsonString = json.toJson(jsonData);
        file.writeString(jsonString, false);
    }


*/





}
