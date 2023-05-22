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
import gift.BonusScore;
import helpers.Collision;
import shoot_em_up.ShootEmUP;
import spacecraft.Alien;
import spacecraft.BossChaosbaneDestructor;
import spacecraft.Skyblade;
import spacecraft.TyrantOfDesolation;
import weapon.InfernoOrbs;
import weapon.RocketCyclone;
import weapon.RocketStorm;

import java.util.HashSet;
import java.util.Iterator;

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

    public GameScreen(final ShootEmUP game) {

        //AFFFICHAGE DES STATISTIQUES
        this.shapestyle = new ShapeRenderer();


        this.game = game;
        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        background = new Background("pictures/stars_1.png", 50.1f, camera);
        captain = new Skyblade();
//        System.out.println(captain.getPicture().getWidth());
        System.out.println(Gdx.graphics.getWidth());
        batch = new SpriteBatch();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("song/06-Damiano-Baldoni-Charlotte.mp3"));
        backgroundMusic.setLooping(true);

        boss = new BossChaosbaneDestructor();


        for (int i = 0; i < 1; i++) {
            Alien monster = new TyrantOfDesolation();
            monster.setWeapon(new InfernoOrbs(batch,monster));
            monsters.add(monster);
        }
//        monsters.add(new Boss());

        captain.setWeapon(new RocketCyclone(batch,captain));
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

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);


        batch.begin();


        captain.getWeapon().spawnAllAmmo();


        Iterator<Alien> iterator = monsters.iterator();

        while (iterator.hasNext()) {
            Alien monster = iterator.next();
            monster.getWeapon().spawnAllAmmo();

            monster.move(batch,captain);
            captain.getWeapon().shoot(monster);
            monster.getWeapon().shoot(captain);
            batch.end();


            if(!monster.isNotDestroyed()){
                captain.setScore(captain.getScore() +  monster.getPoints());

                 bonusScore = new BonusScore(captain,50,50,batch);
               /* if ( new Collision().checkCollision(bonusScore.getPosX(), bonusScore.getPosY(), bonusScore.getPicture().getWidth(), bonusScore.getPicture().getHeight(),
                        captain.getPosX(),
                        captain.getPosY(),
                        captain.getPicture().getWidth(), captain.getPicture().getHeight())) {//si les tirs ont touche les ennemis !!
                    bonusScore.collect();

                }*/
                iterator.remove();
            }
            if(bonusScore != null)
                bonusScore.draw();
            batch.begin();

        }

        float sum = 0;
        for(Alien monster : monsters){
            float pctg = (float) (monster.getPuissance() * 100) / monster.getMaxPuissance();
            sum += pctg;
        }
        int means = (int) (sum / 10 );
        captain.move(batch,null);
        batch.end();
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
        float y = (Gdx.graphics.getHeight() + layout.height) / 2;
        batch.begin();
        ScoreStat.draw(batch, layout, x, Gdx.graphics.getHeight() - 10);
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
