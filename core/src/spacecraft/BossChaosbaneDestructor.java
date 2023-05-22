package spacecraft;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BossChaosbaneDestructor extends Alien{

    private static final String DEFAULT_NAME = "Boss";
    private static final int DEFAULT_MAX_LIFE = 150;
    private int ratio = 2;

    public int getRatio() {
        return ratio;
    }

    private static final String DEFAULT_PICTURE = "pictures/ships/roundysh_large.png" ;

    public BossChaosbaneDestructor(SpriteBatch batch){
        super(DEFAULT_NAME,DEFAULT_PICTURE,DEFAULT_MAX_LIFE, batch);
    }


}
