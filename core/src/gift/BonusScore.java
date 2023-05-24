package gift;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screen.GameScreen;
import spacecraft.Skyblade;

public class BonusScore extends Gift {
    private static final String DEFAULT_NAME = " BONUS DE SCORE " ;
    private static final String DEFAULT_IMAGE = "pictures/bonus/Score.png" ;
    private static final int BONUS = 250 ;
    GameScreen gameScreen;
    public BonusScore(Skyblade skyblade, float x , float y, SpriteBatch batch, GameScreen gameScreen) {
        super(DEFAULT_NAME,DEFAULT_IMAGE,skyblade,x,y,batch);
        setBonus(BONUS);
        this.gameScreen = gameScreen;
    }
    public void collect(){
        //this.skyblade.setScore(this.skyblade.getScore() + BONUS);
        this.gameScreen.setScore(this.gameScreen.getScore() + BONUS);
    }
    public int getBonus(){
        return BONUS;
    }

}
