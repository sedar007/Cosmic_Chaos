package screen;

import bonus.*;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import exceptions.NoWeaponExeption;
import helpers.Collision;
import shoot_em_up.ShootEmUP;
import spacecraft.*;
import weapon.SkyBladeWeapons.RocketStorm3X;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class GameScreen implements Screen {
    final ShootEmUP game;
    private final AllAssets assets;

    final  Texture imageCaptain ;
    final  Texture imageAlien;

    OrthographicCamera camera;
    Background background;
    Skyblade captain;
    SpriteBatch batch;
    Music backgroundMusic;
    HashSet<Alien> monsters = new HashSet<>();
    ShapeRenderer shapestyle;
    BonusScore bonusScore = null;
    HashSet<Bonus> bonus ;
    int nbMonstres = 0;

    BitmapFont fontScore;

    private Double score;
    private Alien targetPredator;

    int numberALienKilled = 0 ;
    boolean play;

    Level level;
    int numLevel;
    private float elapsedTimeLevelText;
    private float times, times1;
    private final float durationTimeLevelText;

    Skin skin;

    FilesJson filesJson;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setMonsters(Alien alien) {
            monsters.add(alien);
    }

    public AllAssets getAssets() {
        return assets;
    }

    public GameScreen(final ShootEmUP game, AllAssets assets) {
        this.assets = assets;

        skin = getAssets().getSkin();

        //AFFFICHAGE DES STATISTIQUES
        this.shapestyle = new ShapeRenderer();
        this.game = game;
        play = true;

        imageCaptain = getAssets().getSkybladePicture();
        imageAlien = getAssets().getBossSmallPicture();

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        background = new Background(getAssets().getEtoilePicture(), 50.1f, camera, this.assets);
        batch = new SpriteBatch();
        captain = new Skyblade(batch, getAssets());

        backgroundMusic = getAssets().getBackgroundMusic();
        backgroundMusic.setLooping(true);

       // POUR LE LEVEL !!
        numLevel = 0 ;
        level = new Level(numLevel,this.game,batch, getAssets());//Au debut !!
        this.monsters = level.getMonsters();

      //  monsters.add(new BossChaosbaneDestructor(batch));

        captain.setWeapon(new RocketStorm3X(batch,captain, getAssets()));
        bonus = new HashSet<>();
        fontScore =  new BitmapFont();
        setScore(0.0);

        elapsedTimeLevelText = 0f;
        durationTimeLevelText = 5f; // Durée d'affichage en secondes
        targetPredator = null;

        times = 0f;
        times1 = 5f; // Durée d'affichage en secondes

        this.filesJson = new FilesJson();

    }

    @Override
    public void show() {
        backgroundMusic.play();
    }

    @Override
    public void render(float delta) {

        if( !captain.isNotDestroyed() ){//qd il est mort !
            //finalStat(numLevel,getScore(),Lost());
            //return;

            this.filesJson.writeJson(getScore());
            game.setScreen(new Endgame(game,finalStat(numLevel,getScore(),Lost()), getAssets()));
            dispose();
        }
        if( numLevel > 2){//il a fini la partie
            //finalStat(numLevel-1,getScore(),Win());
           // return;

            this.filesJson.writeJson(getScore());
            game.setScreen(new Endgame(game,finalStat(numLevel-1,getScore(),Win()),getAssets()));
            dispose();
        }

        if(captain.isNotDestroyed()){

            elapsedTimeLevelText += Gdx.graphics.getDeltaTime();

            if(monsters.size() == 0 && bonus.size() == 0){
                elapsedTimeLevelText = 0f;
                numLevel += 1;
                showLevelText(numLevel);
                level = new Level(numLevel,this.game,batch, getAssets());
                this.monsters = level.getMonsters();
                nbMonstres = level.getMonsters().size();
            }

            if (elapsedTimeLevelText >= durationTimeLevelText ) {
                play();
            }

        /*if(numLevel > 5 ){//fin du jeu
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }*/
        }

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
        try {
            captain.getWeapon().spawnAllAmmo();

        }
        catch (NoWeaponExeption e){
            e.printStackTrace();
        }




        generateBonus();

        //Pour l affichage des bonus et aussi le collecte de ces bonus pour le vaisseau
        collectible();

        //Pour l affichage des aliens et aussi leur disparition une fois qu'ils sont touchés par les tirs !
        Iterator<Alien> iterator = monsters.iterator();
        while (iterator.hasNext()) {
            Alien monster = iterator.next();
            try {
                monster.getWeapon().spawnAllAmmo();

            }
            catch (Exception e){
                e.printStackTrace();
            }
            if(targetPredator == null)
                targetPredator = monster;
            monster.move(captain);
            try {
                captain.getWeapon().shoot(monster);
            }
            catch (NoWeaponExeption e){

            }
            try {
                monster.getWeapon().shoot(captain);

            }
            catch (NoWeaponExeption e){

            }




            if (!monster.isNotDestroyed()) {
                numberALienKilled += 1;
                //captain.setScore(captain.getScore() + monster.getPoints());
                bonus.add(new BonusScore(captain, monster.getPosX(), monster.getPosY(), batch, this, getAssets()));

               for(int i = 10 ; i >= 1 ; i--){
//                   String boom =String.format("boom%02d.png",i) ;
//                   Texture texture = new Texture("pictures/explosion/" + boom);
                   batch.begin();
                   Texture texture = null;
                   switch (i) {
                       case 1:
                           texture = getAssets().getBoom1();
                           break;
                       case 2:
                           texture = getAssets().getBoom2();
                           break;
                       case 3:
                           texture = getAssets().getBoom3();
                           break;
                       default:
                           texture = getAssets().getBoom1();
                           break;
                   }
                   batch.draw(texture,monster.getPosX() , monster.getPosY());
                   batch.end();
               }

                iterator.remove();
            }
        }
        if(targetPredator != null  ){
            try{
                captain.getPredatorFury().spawnAllAmmo(targetPredator);

            }
            catch (Exception e){

            }
        }
        if(targetPredator != null){
            captain.getPredatorFury().shoot(targetPredator);
            targetPredator = null;


        }

        captain.move(null);
        stats(numLevel);

    }


    public void powerStats( float captainStat, float alienStat){
        //AFFICHAGES DES STATISTIQUES

        batch.begin();
        batch.draw( imageCaptain,0,15, 40,40);
        batch.end();

        float posX = 50;
        float height = 20;
        float posY = 20;

        //  shape.rect(posX + 10,posY + 10 ,x - 20,height - 20);

        shapestyle.begin(ShapeRenderer.ShapeType.Filled);

        shapestyle.setColor(Color.WHITE);
        shapestyle.rect(posX, posY, 100,height);

        if(captainStat <= 30 ) shapestyle.setColor(Color.RED);
        else shapestyle.setColor(Color.GREEN);

        shapestyle.rect(50, 20, captainStat,20);
        shapestyle.end();
        for(int i=0; i<captain.getPredatorFury().getNbAmmo(); i++) {
            Texture predatorPic =getAssets().getPredatorPicture();
            batch.begin();
            batch.draw(predatorPic, 60 + 100 + i*10, 20);
            batch.end();
        }
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

      /*  Image image1 = new Image(skin, "progress-bar-back");
        image1.setPosition(55,60);

        batch.begin();
        image1.draw(batch,0.8f);
        for(int i = 0 ; i < 5 ; i++ ){
            Image image = new Image(skin, "progress-bar");
            image.setPosition(70+(i*10),70);
            image.draw(batch,1f);
        }
        batch.end();*/

    }

    public void scoreStat(){
        Label label = new Label(String.format("SCORE : %.2f",getScore()),skin);
        float x = (Gdx.graphics.getWidth() - label.getWidth()) / 2;
        float y = Gdx.graphics.getHeight() - label.getHeight() - 5;
        label.setPosition( x, y);

        batch.begin();
        label.draw(batch,1f);
        batch.end();

        /*BitmapFont ScoreStat = new BitmapFont();
        String text =" SCORE: " + score;
        GlyphLayout layout = new GlyphLayout();
        layout.setText(ScoreStat, text);
        float x = (Gdx.graphics.getWidth() - layout.width) / 2;
        float y = Gdx.graphics.getHeight() - 10;
        batch.begin();
        ScoreStat.draw(batch, layout, x,y );
        batch.end();*/
    }

    public void level(){
        Label label = new Label(String.format("LEVEL : %d",numLevel),skin);
        label.setPosition( 10, Gdx.graphics.getHeight() - label.getHeight() - 5);
        batch.begin();
        label.draw(batch,1f);
        batch.end();
    }

    public void stats( int levelStat){
        //Calcul du nombre moyenne de vaisseau en pourcentage !
        float sum = 0;
        for (Alien monster : monsters) {
            float pctg = (float) (monster.getPuissance() * 100) / monster.getMaxPuissance();
            sum += pctg;
        }
        int alienLife = (int) (sum / nbMonstres);

        float pourcentagePuissance = (captain.getPuissance() * 100)/ captain.getMaxPuissance();
        powerStats(pourcentagePuissance,alienLife);
        scoreStat();
        level();
    }

    public void generateBonus(){
        times += Gdx.graphics.getDeltaTime();
        int choice = new Random().nextInt(50);
        if (numberALienKilled >= 10 && choice % 2 == 0 && times >= times1 ){
            times = 0;
            numberALienKilled = 0;//pour chaque 10
            int choice2 =  new Random().nextInt(3);

            if(choice2 == 0)
                bonus.add(new ChangeWeapon(captain, new Random().nextInt(Gdx.graphics.getWidth()),new Random().nextInt(Gdx.graphics.getHeight() / 2), batch,getAssets()));
            else if(choice2 == 1)
                bonus.add(new BonusPower(captain, new Random().nextInt(Gdx.graphics.getWidth()),new Random().nextInt(Gdx.graphics.getHeight() / 2), batch,getAssets()));
            else
                bonus.add(new Shield(captain, new Random().nextInt(Gdx.graphics.getWidth()),new Random().nextInt(Gdx.graphics.getHeight() / 2), batch,getAssets()));




        }


    }

    public void collectible(){
        //Pour l affichage des bonus et aussi le collecte de ces bonus pour le vaisseau
        Iterator<Bonus> iteratorGift = bonus.iterator();
        while (iteratorGift.hasNext()) {
            Bonus collectible = iteratorGift.next();
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
            else if (collectible.timeOut())
                iteratorGift.remove();

        }
    }

    public void showLevelText(int level){

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Label label = new Label(String.format("LEVEL %d",numLevel),skin);
        float x = (float) (Gdx.graphics.getWidth() /2 )- (label.getWidth() / 2) ;
        float y = (float)(Gdx.graphics.getHeight() /2) - (label.getHeight()/ 2) ;
        label.setPosition( x,y);
        label.setFontScale(1.5f, 1.5f);

        batch.begin();
        label.draw(batch,1f);
        batch.end();


        /*BitmapFont levelText = new BitmapFont();
        String text =" LEVEL " + level ;
        GlyphLayout layout = new GlyphLayout();
        layout.setText(levelText, text);
        float x = (Gdx.graphics.getWidth() - layout.width) / 2;
        float y = (Gdx.graphics.getHeight() - layout.height) / 2;
        batch.begin();
        levelText.draw(batch, layout, x,y );
        batch.end();*/


    }

    public String Lost(){
       return " GAME OVER \n YOU LOSE  ! ";
    }
    public String Win(){
        return " CONGRATULATIONS  ! " ;
    }

    public String finalStat(int level,Double score,String comment ){
       return String.format(" %s \n STATISTICS  :\n  LEVEL -> %d \n SCORE -> %.2f ", comment,level,score);

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
