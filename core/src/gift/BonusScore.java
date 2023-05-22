package gift;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import spacecraft.Skyblade;

public class BonusScore extends Gift {
    private static final String DEFAULT_NAME = " BONUS DE SCORE " ;
    private static final String DEFAULT_IMAGE = "pictures/bonus/Score.png" ;
    private static final int BONUS = 250 ;
    public BonusScore(Skyblade skyblade, float x ,float y, SpriteBatch batch) {
        super(DEFAULT_NAME,DEFAULT_IMAGE,skyblade,x,y,batch);
        setBonus(BONUS);
    }
    public void collect(){
        this.skyblade.setScore(this.skyblade.getScore() + BONUS);
    }
    public int getBonus(){
        return BONUS;
    }

}
