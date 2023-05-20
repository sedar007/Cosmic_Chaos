package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import helpers.Collision;
import shoot_em_up.ShootEmUP;
import spacecraft.Boss;
import spacecraft.Captain;
import spacecraft.Monster3;
import weapon.Weapon;
import weapon.ammo.Ammo;

import java.util.HashSet;
import java.util.Iterator;

public class GameScreen implements Screen {
    final ShootEmUP game;

    OrthographicCamera camera;

    Background background;
    Captain captain;
    SpriteBatch batch;
    Music backgroundMusic;

    HashSet<Monster3> monsters = new HashSet<>();
    Boss boss;

    Texture imageCaptain = new Texture("pictures/ships/blueships1_small.png");
    Texture imageAlien = new Texture("pictures/ships/roundysh_small.png");




    public GameScreen(final ShootEmUP game) {
        this.game = game;

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        background = new Background("pictures/stars_1.png", 50.1f, camera);
        captain = new Captain();
//        System.out.println(captain.getPicture().getWidth());
        System.out.println(Gdx.graphics.getWidth());
        batch = new SpriteBatch();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("song/06-Damiano-Baldoni-Charlotte.mp3"));
        backgroundMusic.setLooping(true);

        boss = new Boss();


        for (int i = 0; i < 10; i++) {
            monsters.add(new Monster3());
        }

        captain.setWeapon(new Weapon(batch,captain));
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


        Iterator<Monster3> iterator = monsters.iterator();

        while (iterator.hasNext()) {
            Monster3 monster = iterator.next();
            monster.move(batch,captain);
            captain.getWeapon().shoot(monster);
            if(!monster.isNotDestroyed()){
                captain.setScore(captain.getScore() + monster.getPoints());
                iterator.remove();
            }
        }

        float sum = 0;
        for(Monster3 monster : monsters){
            float pctg = (float) (monster.getPuissance() * 100) / monster.getMaxPuissance();
            sum += pctg;
        }
        int means = (int) (sum / 10 );

        stats( means,  2);

        captain.move(batch,null);
        batch.end();
    }


    public void powerStats(Texture captainPicture, int captainStat,  Texture alienPicture, int alienStat){
        BitmapFont captainLife = new BitmapFont();
        BitmapFont alienLife = new BitmapFont();

        batch.draw( captainPicture,0,15, 40,40);
        captainLife.draw(batch, String.format(" %d %% ",captainStat), 50, 40);

        batch.draw( alienPicture,Gdx.graphics.getWidth() - 40,15, 40,40);
        alienLife.draw(batch, String.format("%d %%",alienStat),  Gdx.graphics.getWidth() - 2*40, 40);
    }

    public void scoreStat(Double score){
        BitmapFont ScoreStat = new BitmapFont();

        String text =" SCORE: " + score;
        GlyphLayout layout = new GlyphLayout();
        layout.setText(ScoreStat, text);
        float x = (Gdx.graphics.getWidth() - layout.width) / 2;
        float y = (Gdx.graphics.getHeight() + layout.height) / 2;
        ScoreStat.draw(batch, layout, x, Gdx.graphics.getHeight() - 10);
    }

    public void level(int level){
        BitmapFont levelFont = new BitmapFont();
        levelFont.draw(batch, String.format("Level: %d",level),  1, Gdx.graphics.getHeight() - 10);
    }

    public void stats( int alienLife, int levelStat){
        powerStats(imageCaptain,captain.getPuissance(),imageAlien,alienLife);
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
