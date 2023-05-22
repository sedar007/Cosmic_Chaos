package testGame;

public class lib {
}
    Texture buttonTexture; // Texture du bouton
    Rectangle buttonBounds; // Zone de collision du bouton
    boolean isButtonPressed; // Indicateur pour savoir si le bouton est enfoncé ou non



buttonTexture = new Texture("button.png"); // Remplacez "button.png" par le chemin d'accès à votre texture de bouton
        buttonBounds = new Rectangle(x, y, buttonTexture.getWidth(), buttonTexture.getHeight()); // Définissez les coordonnées x et y du bouton

        Gdx.input.setInputProcessor(new InputAdapter() {
@Override
public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (buttonBounds.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
        isButtonPressed = true;
        return true; // Capture l'événement de clic sur le bouton
        }
        return false;
        }

@Override
public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (isButtonPressed && buttonBounds.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
        // Actions à effectuer lorsque le bouton est relâché
        // Ajoutez votre code ici
        }
        isButtonPressed = false;
        return super.touchUp(screenX, screenY, pointer, button);
        }
        });






        SpriteBatch spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        if (isButtonPressed) {
        spriteBatch.draw(buttonTexture, buttonBounds.x, buttonBounds.y - 5); // Dessine le bouton enfoncé avec un décalage vertical de 5 pixels
        } else {
        spriteBatch.draw(buttonTexture, buttonBounds.x, buttonBounds.y); // Dessine le bouton normal
        }
        spriteBatch.end();
