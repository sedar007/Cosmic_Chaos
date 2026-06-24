package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.bonus.*;
import com.mygdx.game.exceptions.NoWeaponExeption;
import com.mygdx.game.helpers.AllAssets;
import com.mygdx.game.helpers.Collision;
import com.mygdx.game.helpers.FilesJson;
import com.mygdx.game.level.Level;
import com.mygdx.game.shoot_em_up.ShootEmUP;
import com.mygdx.game.spacecraft.*;
import com.mygdx.game.weapon.SkyBladeWeapons.RocketStorm3X;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class GameScreen implements Screen {
    final ShootEmUP game;
    final AllAssets assets; // Les assets
    final int MaxLevel; // Nombre de level Max

    OrthographicCamera camera;
    Background background;
    ShapeRenderer shapestyle;

    // Les characteres
    Skyblade captain; // Le captain
    HashSet<Alien> aliens;
    HashSet<Bonus> bonus ;
    int nbAliens; // Le nombre de aliens qu'il y a

    Double score; // Pour le score total
    Alien targetPredator; // Pour suivre l'alien avec le predator ( missile Tete Chercheuse)
    Alien boss; // Pour garder le boss

    int numberALienKilled; // Pour connaitre le nombre d'alien creer
    boolean play; // Savoir Si la partie s'execute ou pas
    Level level; // La classe de tous les levels
    int numLevel; // Le level actuel

    FilesJson highScore; // Permet de modifier le high Score
    float elapsedTimeLevelText,timesBonus,bossTime,afficheNbBonus;//Le temps d affichage du texte Level et du bonus
    final float durationTimeLevelText,MaxTimeBonus;

    Label afficheBonusLabel;
    Label scoreLabel;
    Label levelLabel;
    Label transitionLevelLabel;
    Label pauseLabel;
    Label pauseMessageLabel;


    public GameScreen(final ShootEmUP game, AllAssets assets) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000, 1000);
        this.assets = assets;
        this.game = game;
        shapestyle = new ShapeRenderer();
        background = new Background(getAssets().getEtoilePicture(), 50.1f, camera, this.assets);
        highScore = new FilesJson();
        play = true;

        captain = new Skyblade(game.batch, getAssets());
        captain.setWeapon(new RocketStorm3X(game.batch,captain, getAssets()));

        numLevel = 0 ;
        level = new Level(numLevel,this.game,game.batch, getAssets());
        aliens = level.getAliens();
        boss = null;
        targetPredator = null;
        bossTime = 0f;

        bonus = new HashSet<>();
        nbAliens = 0;
        MaxLevel = 5;
        numberALienKilled = 0 ;
        setScore(0.0);

        elapsedTimeLevelText = 0f;
        durationTimeLevelText = 5f;
        timesBonus = 0f;
        MaxTimeBonus = 30f;
        afficheNbBonus = 0f;

        afficheBonusLabel = new Label("", getAssets().getSkin());
        scoreLabel = new Label("SCORE : 0", getAssets().getSkin());
        levelLabel = new Label("LEVEL : 0", getAssets().getSkin());
        transitionLevelLabel = new Label("", getAssets().getSkin());
        transitionLevelLabel.setFontScale(1.5f, 1.5f);
        pauseLabel = new Label("PAUSED ", getAssets().getSkin());
        pauseMessageLabel = new Label(" Press P or R to continue ", getAssets().getSkin());

        getAssets().getBackgroundMusic().setLooping(true);
    }

    @Override
    public void render(float delta) {
        if( !captain.isNotDestroyed() ){
            highScore.writeJson(getScore());
            game.setScreen(new Endgame(game,finalStat(numLevel,getScore(),Lost()), getAssets()));
            dispose();
        }
        if( numLevel > MaxLevel ){
            highScore.writeJson(getScore());
            game.setScreen(new Endgame(game,finalStat(numLevel-1,getScore(),Win()),getAssets()));
            dispose();
        }

        if(captain.isNotDestroyed()){
            elapsedTimeLevelText += Gdx.graphics.getDeltaTime();

            if(aliens.size() == 0 && bonus.size() == 0){
                elapsedTimeLevelText = 0f;
                numLevel += 1;
                showLevelText();

                level = new Level(numLevel,this.game,game.batch, getAssets());
                this.aliens = level.getAliens();
                nbAliens = level.getAliens().size();
            }

            if (elapsedTimeLevelText >= durationTimeLevelText )
                play();
        }
    }

    public void play(){

        //Le temps de vie du boss
        bossTime += Gdx.graphics.getDeltaTime();

        afficheNbBonus += Gdx.graphics.getDeltaTime();

        // Permet de mettre en pause le jeu
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) { // Si on appuie sur la touche P
            if( !(play = !play) )  // On change la valeur de play et on verifie si elle est a false
                pause(); // on fait appel a la methode pause
            else
                resume(); // On fait appel a la methode resume
        }

        // Permet de continuer le jeu avec la touche R
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            play = true;
            resume();
        }

        //// Permet de mettre en pause a chaque render si play est false
        if(!play){
            pause();
            return;
        }

        ScreenUtils.clear(0, 0, 0, 0); // Nettoyer l'ecran

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        // Permet de faire la mise a jour et le dessin du fond
        background.update(game.batch, Gdx.graphics.getDeltaTime());

        /* --- Game Play --- */
        try {
            captain.getWeapon().spawnAllAmmo();
        }
        catch (NoWeaponExeption e){
            e.printStackTrace();
        }

        generateBonus(); // La generation des bonus
        collectible(); // Pour l affichage des bonus et la collecte


        if(bossTime >= 30f && aliens.contains(boss)) {
            // Generations de aliens chaque 10 secondes, Si le boss est la, on ajoute 5 aliens
            aliens.add(new DeathspikeMarauder(game.batch,getAssets()));
            aliens.add(new InfernoReaper(game.batch,getAssets()));
            aliens.add(new RavagerScourge(game.batch,getAssets()));
            aliens.add(new TyrantOfDesolation(game.batch,getAssets()));
            aliens.add(new VenomclawRavager(game.batch,getAssets()));
            bossTime = 0f;
            nbAliens += 5;
            boss = null;
        }

        // Pour l affichage des aliens et leur disparition
        Iterator<Alien> iterator = aliens.iterator();
        while (iterator.hasNext()) {
            Alien alien = iterator.next();

            if(alien instanceof BossChaosbaneDestructor)
                boss = alien; // Pour garder la valeur du boss
            try {
                alien.getWeapon().spawnAllAmmo();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            if(targetPredator == null) // recupere un Alien pour la tete chercheuse
                targetPredator = alien;

            alien.move(captain); // Deplacement des Alien

            try {
                captain.getWeapon().shoot(alien);
            }
            catch (NoWeaponExeption e){ }

            try {
                alien.getWeapon().shoot(captain);
            }
            catch (NoWeaponExeption e){ }

            if (!alien.isNotDestroyed()) { // Destruction du vaisseau de l'alien
                numberALienKilled += 1;


                bonus.add(new BonusScore(captain, alien.getPosX(), alien.getPosY(), game.batch, this, getAssets()));


                game.batch.draw(getAssets().getBoom1(), alien.getPosX() , alien.getPosY());

                iterator.remove();
            }
        }

        if(targetPredator != null){
            try{
                captain.getPredatorFury().spawnAllAmmo(targetPredator);
            }
            catch (Exception e){ }
            captain.getPredatorFury().shoot(targetPredator);
            targetPredator = null;
        }

        captain.move(null);


        game.batch.end();

        stats();
    }


    public void powerStats( float captainStat, float alienStat){

        shapestyle.begin(ShapeRenderer.ShapeType.Filled);

        // Vaisseau du capitaine
        shapestyle.setColor(Color.WHITE);
        shapestyle.rect(50, 20, 100, 20);
        if(captainStat <= 30 ) shapestyle.setColor(Color.RED); else shapestyle.setColor(Color.GREEN);
        shapestyle.rect(50, 20, captainStat, 20);

        // Vaisseau du monstre
        shapestyle.setColor(Color.WHITE);
        shapestyle.rect(Gdx.graphics.getWidth() - 150, 20, 100,20);
        if(alienStat <= 30 ) shapestyle.setColor(Color.RED); else shapestyle.setColor(Color.GREEN);
        shapestyle.rect(Gdx.graphics.getWidth() - 150, 20, alienStat,20);

        shapestyle.end();

        game.batch.begin();
        game.batch.draw(getAssets().getSkybladePicture(), 0, 15, 40, 40);
        game.batch.draw(getAssets().getBossSmallPicture(), Gdx.graphics.getWidth() - 40, 15, 40, 40);

        Texture predatorPic = getAssets().getPredatorPicture();
        for(int i=0; i<captain.getPredatorFury().getNbAmmo(); i++) {
            game.batch.draw(predatorPic, 60 + 100 + i*10, 20);
        }
        game.batch.end();
    }

    public void scoreStat(){
        scoreLabel.setText("SCORE : " + (Math.round(getScore() * 100.0) / 100.0));
        float x = (Gdx.graphics.getWidth() - scoreLabel.getWidth()) / 2;
        float y = Gdx.graphics.getHeight() - scoreLabel.getHeight() - 5;
        scoreLabel.setPosition( x, y);

        game.batch.begin();
        scoreLabel.draw(game.batch,1f);
        game.batch.end();
    }

    public void level(){
        // --- OPTIMISATION : On met à jour le texte ---
        levelLabel.setText("LEVEL : " + numLevel);
        levelLabel.setPosition( 10, Gdx.graphics.getHeight() - levelLabel.getHeight() - 5);

        game.batch.begin();
        levelLabel.draw(game.batch,1f);
        game.batch.end();
    }

    public void stats(){
        float sum = 0;
        for (Alien alien : aliens) {
            float pctg = (float) (alien.getPuissance() * 100) / alien.getMaxPuissance();
            sum += pctg;
        }

        int alienLife = nbAliens > 0 ? (int) (sum / nbAliens) : 0; // Protection contre division par zéro
        float pourcentagePuissance = (captain.getPuissance() * 100)/ captain.getMaxPuissance();

        powerStats(pourcentagePuissance,alienLife);
        scoreStat();
        level();
    }

    public void generateBonus(){
        timesBonus += Gdx.graphics.getDeltaTime();
        int choice = new Random().nextInt(50);

        if (numberALienKilled >= 10 || (choice % 2 == 0 && timesBonus >= MaxTimeBonus) ){
            timesBonus = 0;
            numberALienKilled = 0;
            int choice2 =  new Random().nextInt(3);

            if(choice2 == 0) bonus.add(new ChangeWeapon(captain, new Random().nextInt(Gdx.graphics.getWidth()),new Random().nextInt(Gdx.graphics.getHeight() / 2), game.batch,getAssets()));
            else if(choice2 == 1) bonus.add(new BonusPower(captain, new Random().nextInt(Gdx.graphics.getWidth()),new Random().nextInt(Gdx.graphics.getHeight() / 2), game.batch,getAssets()));
            else bonus.add(new Shield(captain, new Random().nextInt(Gdx.graphics.getWidth()),new Random().nextInt(Gdx.graphics.getHeight() / 2), game.batch,getAssets()));
        }
    }

    public void collectible(){
        Iterator<Bonus> iteratorGift = bonus.iterator();

        while (iteratorGift.hasNext()) {
            Bonus collectible = iteratorGift.next();
            collectible.draw();

            if (new Collision().checkCollision(collectible.getPosX(), collectible.getPosY(), collectible.getPicture().getWidth(), collectible.getPicture().getHeight(),
                    captain.getPosX(), captain.getPosY(), captain.getPicture().getWidth(), captain.getPicture().getHeight())) {

                afficheNbBonus = 0f;
                collectible.collect();

                afficheBonusLabel.setText(" + " + (Math.round(collectible.getBonus() * 100.0) / 100.0) + " POINTS");
                afficheBonusLabel.setColor(Color.GREEN);

                if (collectible instanceof BonusScore)
                    afficheBonusLabel.setPosition(((Gdx.graphics.getWidth() - afficheBonusLabel.getWidth()) / 2) + 250, Gdx.graphics.getHeight() - afficheBonusLabel.getHeight() - 5);
                else if (collectible instanceof BonusPower)
                    afficheBonusLabel.setPosition(200, 50);

                iteratorGift.remove();
            }
            else if (collectible.timeOut())
                iteratorGift.remove();
        }

        if(afficheNbBonus < 0.7f){
            afficheBonusLabel.draw(game.batch,1f);
        }
    }

    public void showLevelText(){
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        transitionLevelLabel.setText("LEVEL " + numLevel);
        float x = (float) (Gdx.graphics.getWidth() /2 )- (transitionLevelLabel.getWidth() / 2) ;
        float y = (float)(Gdx.graphics.getHeight() /2) - (transitionLevelLabel.getHeight()/ 2) ;
        transitionLevelLabel.setPosition(x,y);

        game.batch.begin();
        transitionLevelLabel.draw(game.batch,1f);
        game.batch.end();
    }

    public String Lost(){ return " GAME OVER \n YOU LOSE  ! "; }
    public String Win(){ return " CONGRATULATIONS  ! " ; }
    public String finalStat(int level,Double score,String comment ){ return " " + comment + " \n STATISTICS  :\n  LEVEL -> " + level + " \n SCORE -> " + (Math.round(score * 100.0) / 100.0) + " "; }

    @Override
    public void show() { getAssets().getBackgroundMusic().play(); }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() {
        getAssets().getBackgroundMusic().pause();

        pauseLabel.setPosition((Gdx.graphics.getWidth() - pauseLabel.getWidth()) / 2, (Gdx.graphics.getHeight() - pauseLabel.getHeight()) / 2);
        pauseMessageLabel.setPosition( (Gdx.graphics.getWidth() - pauseMessageLabel.getWidth()) / 2,  ( (Gdx.graphics.getHeight() - pauseMessageLabel.getHeight()) / 2) - 20);

        game.batch.begin();
        pauseLabel.draw(game.batch,1f);
        pauseMessageLabel.draw(game.batch,1f);
        game.batch.end();
    }

    @Override
    public void resume() { getAssets().getBackgroundMusic().play(); }

    @Override
    public void hide() { }

    @Override
    public void dispose() { }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    public AllAssets getAssets() { return assets; }
}