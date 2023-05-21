package gift;

import com.badlogic.gdx.graphics.Texture;
import spacecraft.Skyblade;

import java.util.Random;

public class BonusPower extends Gift {
    private static final String DEFAULT_NAME = " BONUS DU PUISSANCE " ;
    private static final String DEFAULT_IMAGE = "" ;
    public BonusPower( Skyblade captain) {
        super(DEFAULT_NAME,DEFAULT_IMAGE);
        captain.setPuissance(captain.getPuissance() + 250);
    }

}
