package spacecraft;

import com.badlogic.gdx.graphics.Texture;

public class Monster3 extends Alien {
    private static final String DEFAULT_NAME = "Monster3";
    private static final int DEFAULT_MAX_LIFE = 100;

    private static final Texture DEFAULT_PICTURE = new Texture("pictures/ships/enemy_1_1.png");

    public Monster3() {
        super(DEFAULT_NAME, DEFAULT_PICTURE, DEFAULT_MAX_LIFE);

    }
}