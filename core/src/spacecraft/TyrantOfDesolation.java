package spacecraft;

public class TyrantOfDesolation extends Alien {
    private static final String DEFAULT_NAME = "Monster3";
    private static final int DEFAULT_MAX_LIFE = 60; // 100

    private static final String DEFAULT_PICTURE = "pictures/ships/enemy_1_1.png";
    private static final int DEFAULT_POINTS = 10;

    public TyrantOfDesolation() {
        super(DEFAULT_NAME, DEFAULT_PICTURE, DEFAULT_MAX_LIFE);
        setPoints(DEFAULT_POINTS);
//        setWeapon(new );

    }
}