package com.mygdx.game.commands;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.spacecraft.Spacecraft;

public class MouseCommand implements ICommand{

    @Override
    public void handleMovement(Spacecraft spacecraft, float deltaTime) {
        float positionX = Gdx.input.getX() - ((float) spacecraft.getPicture().getWidth() / 2);
        float positionY = Gdx.graphics.getHeight() - Gdx.input.getY() - ((float) spacecraft.getPicture().getHeight() / 2);

        spacecraft.setPosX(positionX);
        spacecraft.setPosY(positionY);
    }
}
