package com.mygdx.game.commands;

import com.mygdx.game.spacecraft.Spacecraft;

public interface ICommand {
    void handleMovement(Spacecraft spacecraft, float deltaTime);
}
