package gift;

import spacecraft.Skyblade;

public class BonusScore extends Gift {
    private static final String DEFAULT_NAME = " BONUS DE SCORE " ;
    private static final String DEFAULT_IMAGE = "" ;
    public BonusScore( Skyblade skyblade, int Points){
        super(DEFAULT_NAME,DEFAULT_IMAGE);
        skyblade.setScore(skyblade.getScore() + Points);
    }

}
