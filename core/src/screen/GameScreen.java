package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import shoot_em_up.ShootEmUP;
import spacecraft.Boss;
import spacecraft.Captain;
import spacecraft.Monster3;
import weapon.ammo.Ammo1;

import java.util.ArrayList;

public class GameScreen implements Screen {
    final ShootEmUP game;

    OrthographicCamera camera;

    Background background;
    Captain captain;
    SpriteBatch batch;
    Music backgroundMusic;
    Monster3 monster3;

    ArrayList<Monster3> monsters = new ArrayList<>();
    Boss boss;

    Texture shield = new Texture("pictures/shield/shield.png");
    Texture imageCaptain = new Texture("pictures/ships/blueships1_small.png");
    Texture imageAlien = new Texture("pictures/ships/roundysh_small.png");

    Array<Ammo1> ammos;







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
        monster3 = new Monster3();
        boss = new Boss();
        ammos = new Array<Ammo1>();


        for (int i = 0; i < 10; i++) {
            monsters.add(new Monster3());
        }

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



//        batch.draw(monster3.getPicture(), monster3.getPosX(),monster3.getPosY());
//
//
//        batch.draw(boss.getPicture(), boss.getPosX(),boss.getPosY());



//        for(Monster3 monster: monsters) {
//            batch.draw(monster.getPicture(), monster.getPosX(), monster.getPosY());
//            monster.move();
//        }
        batch.draw(captain.getPicture(),captain.getPosX(),captain.getPosY());


//        ammoTest.setxPosition(captain.getPosX());
//        ammoTest.setxPosition(captain.getPosY());

//        batch.draw(ammoTest.getImage(), ammoTest.getxPosition(),ammoTest.getyPosition());

//
//        stats(captain.getPuissance(), 90, 122323.4, 2);
//
//
//
//        monster3.move();
        captain.move();

            batch.end();

    }

    private void spawnAmmo(){
        Ammo1 ammo = new Ammo1();
        ammo.setxPosition(15);
        ammo.setyPosition(15);

    }

    public void lifeStats(Texture captainPicture, int captainStat,  Texture alienPicture, int alienStat){
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

    public void stats(int captainLife, int alienLife, Double stat, int levelStat){
        lifeStats(imageCaptain,alienLife,imageAlien,alienLife);
        scoreStat(stat);
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
