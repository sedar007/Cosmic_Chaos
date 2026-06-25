package com.mygdx.game.commands;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.spacecraft.Spacecraft;

public class KeyboardCommand implements ICommand{
    private final float speed = 600f;

    @Override
    public void handleMovement(Spacecraft spacecraft, float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.Q)) {
            spacecraft.setPosX(spacecraft.getPosX() - (speed * deltaTime));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            spacecraft.setPosX(spacecraft.getPosX() + (speed * deltaTime));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.Z)) {
            spacecraft.setPosY(spacecraft.getPosY() + (speed * deltaTime));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            spacecraft.setPosY(spacecraft.getPosY() - (speed * deltaTime));
        }
    }
}
