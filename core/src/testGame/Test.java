package testGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import spacecraft.Skyblade;

public class Test extends Game {
    public SpriteBatch batch;

    @Override
    public void create () {
        //batch = new SpriteBatch();
        /*Boss monster = new Boss();
        System.out.println(monster);*/

        Skyblade captain = new Skyblade(batch);
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
