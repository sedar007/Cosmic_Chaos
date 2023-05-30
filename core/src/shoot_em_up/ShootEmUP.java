package shoot_em_up;

import screen.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShootEmUP extends Game {
    public BitmapFont font;//pour les textes
    public SpriteBatch batch;//affichages
    private  AllAssets assets; // Toutes les images
    @Override
    public void create () {//l'initialisation

        //pour l initialisation et obtenir toutes les images
        this.assets = new AllAssets();

        batch = new SpriteBatch();
        font = new BitmapFont(); // use libGDX's default Arial font
        this.setScreen(new MainMenuScreen(this,assets));
    }
    @Override

    public void render () {// produit les affichages à chaque itération !
        super.render(); // important! pour affichage du screen

    }
    public AllAssets getAssets() {
        return assets;
    }

    @Override
    public void dispose () { // pour detruire les instances qu on avait créée auparavant pour la memoire !
        batch.dispose();
        font.dispose();

    }
}
