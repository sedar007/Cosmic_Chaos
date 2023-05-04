package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import helpers.Collision;
import shoo_em_up.ShootEmUP;
import spacecraft.Boss;
import spacecraft.Captain;
import spacecraft.Monster3;

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
//        batch.draw(shield,(captain.getPosX() - shield.getWidth()/2 ) ,captain.getPosY() , captain.getPicture().getWidth() + 20, captain.getPicture().getWidth() + 20);

        batch.draw(captain.getPicture(),captain.getPosX(),captain.getPosY());
        batch.draw(monster3.getPicture(), monster3.getPosX(),monster3.getPosY());


        batch.draw(boss.getPicture(), boss.getPosX(),boss.getPosY());
//        batch.draw(boss.getPicture(), boss.getPosX(),boss.getPosY(), boss.getPicture().getWidth()/2, boss.getPicture().getHeight()/2, boss.getPicture().getWidth() , boss.getPicture().getHeight(), 1, 1, 180, 0, 0, boss.getPicture().getWidth(), boss.getPicture().getHeight(), false, false);



        for(Monster3 monster: monsters) {
            batch.draw(monster.getPicture(), monster.getPosX(), monster.getPosY());
            monster.move();
        }

        monster3.move();
        captain.move();
//        boss.move();


//        batch.draw(shield,(captain.getPosX() + captain.getPicture().getWidth()/2) - shield.getWidth()/2,(captain.getPosY() - captain.getPicture().getHeight()/2) - shield.getHeight()/2, captain.getPicture().getWidth(), captain.getPicture().getWidth());

//    if(new Collision().checkCollision(captain.getPosX(), captain.getPosY(), captain.getPicture().getWidth() , captain.getPicture().getHeight(), boss.getPosX(), boss.getPosY(), boss.getPicture().getWidth() , boss.getPicture().getHeight()))
//    {
//        Texture flamme = new Texture("pictures/explosion-8.png");
//        batch.draw(flamme, captain.getPosX(), captain.getPosY(), 250,250);
//    }


            batch.end();

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
