package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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
import spacecraft.*;
import weapon.AlienWeapons.InfernoOrbs;
import weapon.AlienWeapons.SingleRocket;
import weapon.SkyBladeWeapons.RocketStorm3X;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class GameScreen implements Screen {
    final ShootEmUP game;
    final static Texture imageCaptain = new Texture("pictures/ships/skyblade.png");
    final static Texture imageAlien = new Texture("pictures/ships/boss_small.png");

    OrthographicCamera camera;
    Background background;
    Skyblade captain;
    SpriteBatch batch;
    Music backgroundMusic;
    HashSet<Alien> monsters = new HashSet<>();
    ShapeRenderer shapestyle;
    BonusScore bonusScore = null;
    HashSet<Gift> bonus ;

    BitmapFont fontScore;

    private Double score;
    private Alien targetPredator;

    int numberALienKilled = 0 ;
    boolean play;

    Level level;
    int numLevel;
    private float elapsedTimeLevelText;
    private final float durationTimeLevelText;



    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setMonsters(Alien alien) {
            monsters.add(alien);
    }

    public GameScreen(final ShootEmUP game) {

        //AFFFICHAGE DES STATISTIQUES
        this.shapestyle = new ShapeRenderer();
        this.game = game;
        play = true;

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        background = new Background("pictures/stars_1.png", 50.1f, camera);
        batch = new SpriteBatch();
        captain = new Skyblade(batch);

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("song/06-Damiano-Baldoni-Charlotte.mp3"));
        backgroundMusic.setLooping(true);

       // POUR LE LEVEL !!
        numLevel = 0 ;
        level = new Level(numLevel,this.game,batch);//Au debut !!
        this.monsters = level.getMonsters();

      //  monsters.add(new BossChaosbaneDestructor(batch));

        captain.setWeapon(new RocketStorm3X(batch,captain));
        bonus = new HashSet<>();
        fontScore =  new BitmapFont();
        setScore(0.0);

        elapsedTimeLevelText = 0f;
        durationTimeLevelText = 5f; // Durée d'affichage en secondes


        targetPredator = null;

    }

    @Override
    public void show() {
        backgroundMusic.play();
    }

    @Override
    public void render(float delta) {

        elapsedTimeLevelText += Gdx.graphics.getDeltaTime();

        if(monsters.size() == 0 && bonus.size() == 0){
            elapsedTimeLevelText = 0f;
            numLevel += 1;
            showLevelText(numLevel);
            level = new Level(numLevel,this.game,batch);
            this.monsters = level.getMonsters();
        }

        if (elapsedTimeLevelText >= durationTimeLevelText ) {
            play();
        }

        /*if(numLevel > 5 ){//fin du jeu
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

        if( !captain.isNotDestroyed()){
            Lost(numLevel);
        }*/





    }


    public void play(){
        // Permet de mettre en pause le jeu
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) { // Si on appuie sur la touche P
            if( !(play = !play) )  // On change la valeur de play et on verifie si elle est a false
                pause(); // on fait appel a la methode pause ( Mettre en pause la musique, affiche un message ... )
            else
                resume(); // On fait appel a la methode resume pour mettre en marche la musique ...
        }

        // Permet de continuer le jeu avec la touche R
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            play = true;
            resume();
        }

        if(!play){ // Permet de mettre en pause a chaque render si play est false
            pause();
            return;
        }

        ScreenUtils.clear(0, 0, 0, 0); // Nettoyer l'ecran

        // Permet de faire la mise a jour
        background.update(game.batch,Gdx.graphics.getDeltaTime());
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        /* --- Game Play --- */

        captain.getWeapon().spawnAllAmmo();

        //Pour l affichage des bonus et aussi le collecte de ces bonus pour le vaisseau
        collectible();

        //Pour l affichage des aliens et aussi leur disparition une fois qu'ils sont touchés par les tirs !
        Iterator<Alien> iterator = monsters.iterator();
        while (iterator.hasNext()) {
            Alien monster = iterator.next();
            monster.getWeapon().spawnAllAmmo();
            if(targetPredator == null)
                targetPredator = monster;
            monster.move(captain);
            captain.getWeapon().shoot(monster);
            monster.getWeapon().shoot(captain);

            if (!monster.isNotDestroyed()) {
                numberALienKilled += 1;
                //captain.setScore(captain.getScore() + monster.getPoints());
                bonus.add(new BonusScore(captain, monster.getPosX(), monster.getPosY(), batch, this));
                if (numberALienKilled == 10) {
                    float x = new Random().nextInt(Gdx.graphics.getWidth());
                    float y = new Random().nextInt(Gdx.graphics.getHeight() / 2);
                    bonus.add(new BonusPower(captain, x, y, batch));
                    numberALienKilled = 0;//pour chaque 10
                }
               for(int i = 10 ; i >= 1 ; i--){
                   String boom =String.format("boom%02d.png",i) ;
                   Texture texture = new Texture("pictures/explosion/" + boom);
                   batch.begin();
                   batch.draw(texture,monster.getPosX() , monster.getPosY());
                   batch.end();
               }

                iterator.remove();
            }
        }
        if(targetPredator != null  ){
            captain.getPredatorFury().spawnAllAmmo(targetPredator);
        }
        if(targetPredator != null){
            captain.getPredatorFury().shoot(targetPredator);
            targetPredator = null;


        }

        captain.move(null);
        stats(1);

    }


    public void powerStats( float captainStat, float alienStat){
        //AFFICHAGES DES STATISTIQUES

        batch.begin();
        batch.draw( imageCaptain,0,15, 40,40);
        batch.end();

        shapestyle.begin(ShapeRenderer.ShapeType.Filled);
        shapestyle.setColor(Color.WHITE);
        shapestyle.rect(50, 20, 100,20);
        if(captainStat <= 30 ) shapestyle.setColor(Color.RED);
        else shapestyle.setColor(Color.GREEN);
        shapestyle.rect(50, 20, captainStat,20);
        shapestyle.end();

        batch.begin();
        batch.draw( imageAlien,Gdx.graphics.getWidth() - 40,15, 40,40);
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

    public void stats( int levelStat){
        //Calcul du nombre moyenne de vaisseau en pourcentage !
        float sum = 0;
        for (Alien monster : monsters) {
            float pctg = (float) (monster.getPuissance() * 100) / monster.getMaxPuissance();
            sum += pctg;
        }
        int alienLife = (int) (sum / 10);

        float pourcentagePuissance = (captain.getPuissance() * 100)/ captain.getMaxPuissance();
        powerStats(pourcentagePuissance,alienLife);
        scoreStat(getScore());
        level(levelStat);
    }

    public void collectible(){
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
                String text = " + " + collectible.getBonus() + " POINTS ";
                GlyphLayout layout = new GlyphLayout();
                fontScore.setColor(Color.GREEN);
                layout.setText(fontScore, text);
                if (collectible instanceof BonusScore) {
                    batch.begin();
                    fontScore.draw(batch, layout, ((Gdx.graphics.getWidth() - layout.width) / 2) + 180, Gdx.graphics.getHeight() - 10);
                    batch.end();
                } else if (collectible instanceof BonusPower) {
                    batch.begin();
                    fontScore.draw(batch, layout, 200, 50);
                    batch.end();
                }
                iteratorGift.remove();
            }
        }
    }

    public void showLevelText(int level){

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        BitmapFont levelText = new BitmapFont();
        String text =" LEVEL " + level ;
        GlyphLayout layout = new GlyphLayout();
        layout.setText(levelText, text);
        float x = (Gdx.graphics.getWidth() - layout.width) / 2;
        float y = (Gdx.graphics.getHeight() - layout.height) / 2;
        batch.begin();
        levelText.draw(batch, layout, x,y );
        batch.end();
    }

    public void Lost(int level){
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        BitmapFont levelText = new BitmapFont();
        String text =" GAME OVER \n YOU LOSE :( ! " + level ;
        GlyphLayout layout = new GlyphLayout();
        layout.setText(levelText, text);
        float x = (Gdx.graphics.getWidth() - layout.width) / 2;
        float y = (Gdx.graphics.getHeight() - layout.height) / 2;
        batch.begin();
        levelText.draw(batch, layout, x,y );
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        backgroundMusic.pause();

        BitmapFont pauseFont = new BitmapFont();
        String textPause =" PAUSED " ;
        GlyphLayout layoutPause = new GlyphLayout();
        layoutPause.setText(pauseFont, textPause);

        BitmapFont pauseFontMessage = new BitmapFont();
        String textPauseMessage =" Press P or R to continue " ;
        GlyphLayout layoutPauseMessage = new GlyphLayout();
        layoutPauseMessage.setText(pauseFontMessage, textPauseMessage);

        batch.begin();
        pauseFont.draw(batch, layoutPause, (Gdx.graphics.getWidth() - layoutPause.width) / 2, (Gdx.graphics.getHeight() - layoutPause.height) / 2);
        pauseFontMessage.draw(batch, layoutPauseMessage, (Gdx.graphics.getWidth() - layoutPauseMessage.width) / 2,  ( (Gdx.graphics.getHeight() - layoutPauseMessage.height) / 2) - 20);

        batch.end();


    }

    @Override
    public void resume() {
        backgroundMusic.play();


    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
