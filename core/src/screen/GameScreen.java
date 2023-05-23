package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import gift.BonusPower;
import gift.BonusScore;
import gift.ChangeWeapon;
import gift.Gift;
import helpers.Collision;
import shoot_em_up.ShootEmUP;
import spacecraft.Alien;
import spacecraft.BossChaosbaneDestructor;
import spacecraft.Skyblade;
import spacecraft.TyrantOfDesolation;
import weapon.AlienWeapons.InfernoOrbs;
import weapon.AlienWeapons.SingleRocket;
import weapon.SkyBladeWeapons.RocketStorm3X;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class GameScreen implements Screen {
    final ShootEmUP game;
    OrthographicCamera camera;
    Background background;
    Skyblade captain;
    SpriteBatch batch;
    Music backgroundMusic;
    HashSet<Alien> monsters = new HashSet<>();
    BossChaosbaneDestructor boss;
    Texture imageCaptain = new Texture("pictures/ships/blueships1_small.png");
    Texture imageAlien = new Texture("pictures/ships/roundysh_small.png");
    ShapeRenderer shapestyle;
    BonusScore bonusScore = null;
    HashSet<Gift> bonus ;

    BitmapFont fontScore;
    int numberALienKilled = 0 ;



    public GameScreen(final ShootEmUP game) {

        //AFFFICHAGE DES STATISTIQUES
        this.shapestyle = new ShapeRenderer();
        this.game = game;

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        background = new Background("pictures/stars_1.png", 50.1f, camera);
        batch = new SpriteBatch();

        captain = new Skyblade(batch);

//        System.out.println(captain.getPicture().getWidth());
        System.out.println(Gdx.graphics.getWidth());
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("song/06-Damiano-Baldoni-Charlotte.mp3"));
        backgroundMusic.setLooping(true);
        boss = new BossChaosbaneDestructor(batch);
       // monsters.add(boss);

        for (int i = 0; i < 10; i++) {
            Alien monster = new TyrantOfDesolation(batch);
            monster.setWeapon(new SingleRocket(batch,monster));
            monsters.add(monster);
        }

        captain.setWeapon(new RocketStorm3X(batch,captain));
        bonus = new HashSet<>();
        fontScore =  new BitmapFont();

    }

    @Override
    public void show() {
        backgroundMusic.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        // tell the camera to update its matrices.
        background.update(game.batch,Gdx.graphics.getDeltaTime());
        camera.update();

        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        captain.getWeapon().spawnAllAmmo();

        //Pour l affichage des bonus et aussi le collecte de ces bonus pour le vaisseau
        Iterator<Gift> iteratorGift = bonus.iterator();
        while (iteratorGift.hasNext()) {
            Gift collectible = iteratorGift.next();
            collectible.draw();
            if (new Collision().checkCollision(collectible.getPosX(), collectible.getPosY(), collectible.getPicture().getWidth(), collectible.getPicture().getHeight(),
                    captain.getPosX(),
                    captain.getPosY(),
                    captain.getPicture().getWidth(), captain.getPicture().getHeight())) {//si les tirs ont touche les ennemis !!
                collectible.collect();
                String text =" + " + collectible.getBonus() + " POINTS ";
                GlyphLayout layout = new GlyphLayout();
                fontScore.setColor(Color.GREEN);
                layout.setText(fontScore, text);
                if(collectible instanceof BonusScore){
                    batch.begin();
                    fontScore.draw(batch, layout, ((Gdx.graphics.getWidth() - layout.width) / 2)  + 180 ,  Gdx.graphics.getHeight() - 10 );
                    batch.end();
                }
                else if(collectible instanceof BonusPower){
                    batch.begin();
                    fontScore.draw(batch, layout, 200 , 50);
                    batch.end();
                }
                iteratorGift.remove();
            }
        }

        bonus.add(new ChangeWeapon(captain,50,50,batch));
        //Pour l affichage des aliens et aussi leur disparition une fois qu'ils sont touchés par les tirs !
        Iterator<Alien> iterator = monsters.iterator();
        while (iterator.hasNext()) {
            Alien monster = iterator.next();
            monster.getWeapon().spawnAllAmmo();

            monster.move(captain);
            captain.getWeapon().shoot(monster);
            monster.getWeapon().shoot(captain);

            if(!monster.isNotDestroyed()){
                numberALienKilled += 1;
                captain.setScore(captain.getScore() +  monster.getPoints());
                bonus.add(new BonusScore(captain, monster.getPosX(), monster.getPosY(), batch));
                if( numberALienKilled == 10 ){
                    float x  = new Random().nextInt(Gdx.graphics.getWidth()) ;
                    float y = new Random().nextInt(Gdx.graphics.getHeight() / 2);
                    bonus.add(new BonusPower(captain, x, y, batch));
                    numberALienKilled = 0 ;//pour chaque 10
                }

                iterator.remove();
            }
        }


        //Calcul du nombre moyenne de vaisseau en pourcentage !
        float sum = 0;
        for(Alien monster : monsters){
            float pctg = (float) (monster.getPuissance() * 100) / monster.getMaxPuissance();
            sum += pctg;
        }
        int means = (int) (sum / 10 );
        captain.move(null);
        stats( means,  1);


    }


    public void powerStats(Texture captainPicture, int captainStat,  Texture alienPicture, int alienStat){
        //AFFICHAGES DES STATISTIQUES

        batch.begin();
        batch.draw( captainPicture,0,15, 40,40);
        batch.end();

        shapestyle.begin(ShapeRenderer.ShapeType.Filled);
        shapestyle.setColor(Color.WHITE);
        shapestyle.rect(50, 20, 100,20);
        if(captainStat <= 30 ) shapestyle.setColor(Color.RED);
        else shapestyle.setColor(Color.GREEN);
        shapestyle.rect(50, 20, captainStat,20);
        shapestyle.end();

        batch.begin();
        batch.draw( alienPicture,Gdx.graphics.getWidth() - 40,15, 40,40);
        batch.end();


        shapestyle.begin(ShapeRenderer.ShapeType.Filled);
        shapestyle.setColor(Color.WHITE);
        shapestyle.rect(Gdx.graphics.getWidth() - 150, 20, 100,20);

        if(alienStat <= 30 ) shapestyle.setColor(Color.RED);
        else shapestyle.setColor(Color.GREEN);
        shapestyle.rect(Gdx.graphics.getWidth() - 150, 20, alienStat,20);
        shapestyle.end();

    }

    public void scoreStat(Double score){
        BitmapFont ScoreStat = new BitmapFont();
        String text =" SCORE: " + score;
        GlyphLayout layout = new GlyphLayout();
        layout.setText(ScoreStat, text);
        float x = (Gdx.graphics.getWidth() - layout.width) / 2;
        float y = Gdx.graphics.getHeight() - 10;
        batch.begin();
        ScoreStat.draw(batch, layout, x,y );
        batch.end();
    }

    public void level(int level){
        BitmapFont levelFont = new BitmapFont();
        batch.begin();
        levelFont.draw(batch, String.format("Level: %d",level),  1, Gdx.graphics.getHeight() - 10);
        batch.end();
    }

    public void stats( int alienLife, int levelStat){
        int pourcentagePuissance = (captain.getPuissance() * 100)/ captain.getMaxPuissance();
        powerStats(imageCaptain,pourcentagePuissance,imageAlien,alienLife);
        scoreStat(captain.getScore());
        level(levelStat);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
