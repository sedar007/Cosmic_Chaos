package shoot_em_up;

import screen.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screen.TestButton;

public class ShootEmUP extends Game {
    public BitmapFont font;//pour les textes
    public SpriteBatch batch;//affichages
    @Override
    public void create () {//l'initialisation
        batch = new SpriteBatch();
        font = new BitmapFont(); // use libGDX's default Arial font
        this.setScreen(new TestButton(this));
    }
    @Override
    public void render () {// produit les affichages à chaque itération !
        super.render(); // important!

    }

    @Override
    public void dispose () { // pour detruire les instances qu on avait créée auparavant pour la memoire !
        batch.dispose();
        font.dispose();

    }
}
