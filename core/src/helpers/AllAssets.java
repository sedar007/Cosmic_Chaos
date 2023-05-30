package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AllAssets {

    // Projectiles
    private final Texture rocketPicture;
    private Texture missilesPicture;
    private final Texture predatorPicture;
    private final Texture laserPicture;
    private Texture rocketInversePicture;
    private final Texture shotbigPicture;
    private final Texture shotOvalPicture;
    private final Texture shotsmallPicture;


    // Images
    private final Texture mainMenuScreenPicture;
    private final Texture backGroundPicture;
    private final Texture endGamePicture;
    private final Texture etoilePicture;
    private final Texture logo;
    private final Texture highScore;

    // Images Bonus
    private final Texture bonusPowerPicture;
    private final Texture bonusScorePicture;
    private final Texture BonusWeaponPicture;
    private final Texture bonusShieldPicture;

    // Shield
    private final Texture shieldPicture;

    // Skin
    private final Skin skin;

    // Sound
    private final Music backgroundMusic;
    private final Music soundShoot;
    private final Music bonuCollect;

    //Spacecraft
    private final Texture skybladePicture;
    private final Texture bossSmallPicture;
    private final Texture bossLarge;
    private final Texture deathSpikeMarauder;
    private final Texture infernoReaper;
    private final Texture ravagerScourge;
    private final Texture tyrantOfDesolation;
    private final Texture venomClawRavager;

    // Explosion
    private final Texture boom1;
    private final Texture boom2;
    private final Texture boom3;
    private final Texture boom4;
    private final Texture boom5;
    private final Texture boom6;
    private final Texture boom7;
    private final Texture boom8;
    private final Texture boom9;
    private final Texture boom10;
    private final Texture boom11;

    private final Texture explosion5;




    public AllAssets(){
        // Projectiles
        this.laserPicture = new Texture("pictures/projectiles/laser.png");
        this.rocketPicture = new Texture("pictures/projectiles/rocket.png");
        this.predatorPicture = new Texture("pictures/projectiles/predator.png");
        this.rocketInversePicture = new Texture("pictures/projectiles/rocketInverse.png");
        this.shotsmallPicture = new Texture("pictures/projectiles/shotsmall.png");
        this.shotbigPicture = new Texture("pictures/projectiles/shotbig.png");
        this.shotOvalPicture = new Texture("pictures/projectiles/shotoval.png");
        System.out.println("Create Assets");

        // Images
        this.mainMenuScreenPicture = new Texture("pictures/fond.jpg");
        this.backGroundPicture =  new Texture("pictures/background.png");
        this.endGamePicture = new Texture("pictures/endgame.jpg");
        this.etoilePicture = new Texture("pictures/stars_1.png");
        this.logo = new Texture(Gdx.files.internal("pictures/logogame.jpeg"));
        this.highScore = new Texture("pictures/highScore.jpg");

        // Bonus
        this.bonusPowerPicture = new Texture("pictures/bonus/puissance.png");
        this.bonusScorePicture = new Texture("pictures/bonus/Score.png");
        this.BonusWeaponPicture = new Texture("pictures/bonus/weapon.png");
        this.bonusShieldPicture = new Texture("pictures/bonus/shield.png");


        // Shield
        this.shieldPicture = new Texture("pictures/shield/shield.png");

        // Skin
        this.skin = new Skin(Gdx.files.internal("skin2/star-soldier-ui.json"));

        // Sound
        this.backgroundMusic =  Gdx.audio.newMusic(Gdx.files.internal("song/06-Damiano-Baldoni-Charlotte.mp3"));
        this.soundShoot = Gdx.audio.newMusic(Gdx.files.internal("song/gunner-sound-43794.mp3"));
        this.bonuCollect = Gdx.audio.newMusic(Gdx.files.internal("song/bonus-collected.wav"));


        // Spacecraft
        this.skybladePicture = new Texture("pictures/ships/skyblade.png");
        this.bossSmallPicture = new Texture("pictures/ships/boss_small.png");
        this.bossLarge = new Texture("pictures/ships/boss_large.png");
        this.deathSpikeMarauder = new Texture("pictures/ships/DeathspikeMarauder.png");
        this.infernoReaper = new Texture("pictures/ships/InfernoReaper.png");
        this.ravagerScourge = new Texture("pictures/ships/RavagerScourge.png");
        this.tyrantOfDesolation = new Texture("pictures/ships/TyrantOfDesolation.png");
        this.venomClawRavager = new Texture("pictures/ships/VenomclawRavager.png");

        //Explosion
        this.boom1 = new Texture("pictures/explosion/boom01.png");
        this.boom2 = new Texture("pictures/explosion/boom02.png");
        this.boom3 = new Texture("pictures/explosion/boom03.png");
        this.boom4 = new Texture("pictures/explosion/boom04.png");
        this.boom5 = new Texture("pictures/explosion/boom05.png");
        this.boom6 = new Texture("pictures/explosion/boom06.png");
        this.boom7 = new Texture("pictures/explosion/boom07.png");
        this.boom8 = new Texture("pictures/explosion/boom08.png");
        this.boom9 = new Texture("pictures/explosion/boom09.png");
        this.boom10 = new Texture("pictures/explosion/boom10.png");
        this.boom11 = new Texture("pictures/explosion/boom11.png");

        this.explosion5 = new Texture("pictures/explosion/explosion-5.png");


//        private Texture Shield;


    }

    public Music getBonuCollect() {
        return bonuCollect;
    }

    public Music getSoundShoot() {
        return soundShoot;
    }

    public Texture getHighScore() {
        return highScore;
    }

    public Texture getExplosion5() {
        return explosion5;
    }

    public Texture getBoom1() {
        return boom1;
    }

    public Texture getBoom2() {
        return boom2;
    }

    public Texture getBoom3() {
        return boom3;
    }

    public Texture getBoom4() {
        return boom4;
    }

    public Texture getBoom5() {
        return boom5;
    }

    public Texture getBoom7() {
        return boom7;
    }

    public Texture getBoom8() {
        return boom8;
    }

    public Texture getBoom9() {
        return boom9;
    }

    public Texture getBoom10() {
        return boom10;
    }

    public Texture getBoom11() {
        return boom11;
    }

    public Texture getBoom6() {
        return boom6;
    }

    public Texture getBossLarge() {
        return bossLarge;
    }

    public Texture getDeathSpikeMarauder() {
        return deathSpikeMarauder;
    }

    public Texture getInfernoReaper() {
        return infernoReaper;
    }

    public Texture getRavagerScourge() {
        return ravagerScourge;
    }

    public Texture getTyrantOfDesolation() {
        return tyrantOfDesolation;
    }

    public Texture getVenomClawRavager() {
        return venomClawRavager;
    }

    public Texture getLogo() {
        return logo;
    }


    public Texture getBossSmallPicture() {
        return bossSmallPicture;
    }

    public Texture getRocketPicture() {
        return rocketPicture;
    }

    public Texture getMissilesPicture() {
        return missilesPicture;
    }

    public Texture getMainMenuScreenPicture() {
        return mainMenuScreenPicture;
    }

    public Texture getPredatorPicture() {
        return predatorPicture;
    }


    public Texture getLaserPicture() {
        return laserPicture;
    }


    public Texture getRocketInversePicture() {
        return rocketInversePicture;
    }

    public void setRocketInversePicture(Texture rocketInversePicture) {
        this.rocketInversePicture = rocketInversePicture;
    }

    public Texture getShotbigPicture() {
        return shotbigPicture;
    }

    public Texture getShotOvalPicture() {
        return shotOvalPicture;
    }


    public Texture getShotsmallPicture() {
        return shotsmallPicture;
    }

    public Texture getBonusPowerPicture() {
        return bonusPowerPicture;
    }

    public Texture getBonusScorePicture() {
        return bonusScorePicture;
    }


    public Texture getBonusWeaponPicture() {
        return BonusWeaponPicture;
    }

    public Texture getBonusShieldPicture() {
        return bonusShieldPicture;
    }

    public Texture getShieldPicture() {
        return shieldPicture;
    }

    public Texture getBackGroundPicture() {
        return backGroundPicture;
    }

    public Skin getSkin() {
        return skin;
    }

    public Texture getEndGamePicture() {
        return endGamePicture;
    }

    public Texture getEtoilePicture() {
        return etoilePicture;
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public Texture getSkybladePicture() {
        return skybladePicture;
    }
}
