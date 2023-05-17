package testGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screen.MainMenuScreen;
import spacecraft.Boss;

public class Test extends Game {
    public SpriteBatch batch;

    @Override
    public void create () {
//        batch = new SpriteBatch();
        Boss monster = new Boss();
        System.out.println(monster);

    }
    @Override
    public void render () {
//        super.render();

    }

    @Override
    public void dispose () {
//        batch.dispose();

    }

}
