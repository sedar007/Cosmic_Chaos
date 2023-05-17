package testGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screen.MainMenuScreen;
import spacecraft.Boss;
import spacecraft.Captain;

public class Test extends Game {
    public SpriteBatch batch;

    @Override
    public void create () {
        //batch = new SpriteBatch();
        /*Boss monster = new Boss();
        System.out.println(monster);*/

        Captain captain = new Captain("Captain");
       System.out.println(captain.getPuissance());
        captain.equipShield();
       System.out.println(captain.getPuissance());


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
