package shoot_em_up;

import screen.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import spacecraft.Boss;

public class ShootEmUP extends Game {
    public BitmapFont font;
    public SpriteBatch batch;
    @Override
    public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont(); // use libGDX's default Arial font
        this.setScreen(new MainMenuScreen(this));

    }
    @Override
    public void render () {
        super.render(); // important!

    }

    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();

    }
}
