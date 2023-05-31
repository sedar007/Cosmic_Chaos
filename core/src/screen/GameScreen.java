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
import com.badlogic.gdx.utils.ScreenUtils;
import exceptions.NoWeaponExeption;
import helpers.AllAssets;
import helpers.Collision;
import shoot_em_up.ShootEmUP;
import spacecraft.*;
import weapon.SkyBladeWeapons.RocketStorm3X;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class GameScreen implements Screen {
    final ShootEmUP game;
    final AllAssets assets; // Les assets
    final int MaxLevel; // Nombre de level Max

    OrthographicCamera camera;
    Background background;
    SpriteBatch batch;
    ShapeRenderer shapestyle;


    // Les characteres
    Skyblade captain; // Le captain
    HashSet<Alien> aliens = new HashSet<>();
    BonusScore bonusScore = null;
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
    float elapsedTimeLevelText;//Le temps d affichage du texte Level
    final float durationTimeLevelText;

    float timesBonus;//Le temps d affichage du bonus
    final float MaxTimeBonus;
    float bossTime;



    public GameScreen(final ShootEmUP game, AllAssets assets) {

        //instanciation et  configuration de la caméra avec une projection orthographique
        //Elle capture une vue plate de la scène, où les objets à l'écran apparaissent à la même échelle,
        // quelle que soit leur distance par rapport à la caméra
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // recupere les assets
        this.assets = assets;

        this.game = game;

        //Instanciation du nouveau shape
        shapestyle = new ShapeRenderer();

        //Pour l'image de fond
        background = new Background(getAssets().getEtoilePicture(), 50.1f, camera, this.assets);

        batch = new SpriteBatch();

        //Fichier
        highScore = new FilesJson();

        //En train de jouer
        play = true;

        //Instanciation et arme du vaisseau du capitaine
        captain = new Skyblade(batch, getAssets());
        captain.setWeapon(new RocketStorm3X(batch,captain, getAssets()));

       // POUR LE LEVEL !!
        numLevel = 0 ;
        level = new Level(numLevel,this.game,batch, getAssets());//Au debut !!
        aliens = level.getAliens(); // Recupere le monstre du level
        boss = null; // Pour garder le boss
        targetPredator = null;//Qui va contenir l alien qui est touché par le predator
        bossTime = 0f;//Pour garder le temps de vie du Boss pour qu on puisse ajouter des autres aliens dans le level 5


        bonus = new HashSet<>(); // Initialise le bonus

        nbAliens = 0; // Le nombre de aliens qu'il y a
        MaxLevel = 5; // Nombre de level Max
        numberALienKilled = 0 ; // Pour connaitre le nombre d'alien creer
        setScore(0.0);//Intialisation du score

        elapsedTimeLevelText = 0f;//Temps passés
        durationTimeLevelText = 5f; // Durée d'affichage en secondes

        timesBonus = 0f;
        MaxTimeBonus = 30f; // Durée d'affichage en secondes

        getAssets().getBackgroundMusic().setLooping(true); // debut la music du fond
    }


    @Override
    public void render(float delta) {

        if( !captain.isNotDestroyed() ){//qd il est mort !
            highScore.writeJson(getScore());
            game.setScreen(new Endgame(game,finalStat(numLevel,getScore(),Lost()), getAssets()));
            dispose();
        }
        if( numLevel > MaxLevel ){//il a fini la partie
            highScore.writeJson(getScore());
            game.setScreen(new Endgame(game,finalStat(numLevel-1,getScore(),Win()),getAssets()));
            dispose();
        }

        if(captain.isNotDestroyed()){//Qd le vaisseau n est pas encore detruit

            elapsedTimeLevelText += Gdx.graphics.getDeltaTime();

            if(aliens.size() == 0 && bonus.size() == 0){//S il n y a plus d aliens et il n y a plus de bonus sur le screen
                elapsedTimeLevelText = 0f;//On reinitialise
                numLevel += 1;//On avance le level
                showLevelText(numLevel);//Pour afficher la transition affichant le level suivant
                level = new Level(numLevel,this.game,batch, getAssets());
                this.aliens = level.getAliens();
                nbAliens = level.getAliens().size();
            }

            if (elapsedTimeLevelText >= durationTimeLevelText ) {//Pour afficher le texte de transition avant de continuer
                play();
            }

        }

    }


    public void play(){

        //Le temps de vie du boss
        bossTime += Gdx.graphics.getDeltaTime();

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

        //// Permet de mettre en pause a chaque render si play est false
        if(!play){
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

        generateBonus(); // La generation des bonus
        collectible(); //Pour l affichage des bonus et aussi le collecte de ces bonus pour le vaisseau


        if(bossTime >= 10f && aliens.contains(boss)) {
            // Generations de aliens chaque 10 secondes, Si le boss est la,donc on ajoute 5 aliens differents au
            // dernier level
            aliens.add(new DeathspikeMarauder(batch,getAssets()));
            aliens.add(new InfernoReaper(batch,getAssets()));
            aliens.add(new RavagerScourge(batch,getAssets()));
            aliens.add(new TyrantOfDesolation(batch,getAssets()));
            aliens.add(new VenomclawRavager(batch,getAssets()));
            bossTime = 0f;
            nbAliens += 5;
            boss = null;
        }

        //Pour l affichage des aliens et aussi leur disparition une fois qu'ils sont touchés par les tirs !
        Iterator<Alien> iterator = aliens.iterator();
        while (iterator.hasNext()) {
            Alien alien = iterator.next();

            if(alien instanceof BossChaosbaneDestructor)
                boss = alien;//Pour garder la valeur du boss dans le jeu
            try {
                alien.getWeapon().spawnAllAmmo();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            if(targetPredator == null) //recupere un Alien pour la tete chercheuse
                targetPredator = alien;

            alien.move(captain); // Deplacement des Alien
            try {
                captain.getWeapon().shoot(alien);
            }
            catch (NoWeaponExeption e){

            }
            try {
                alien.getWeapon().shoot(captain);

            }
            catch (NoWeaponExeption e){

            }

            if (!alien.isNotDestroyed()) {//Destruction du vaisseau de l alien
                numberALienKilled += 1;

                //gain de bonus pour le vaisseau du capitaine
                bonus.add(new BonusScore(captain, alien.getPosX(), alien.getPosY(), batch, this, getAssets()));

                Music soundShoot;
                soundShoot = Gdx.audio.newMusic(Gdx.files.internal("song/mixkit-multiple-fireworks-explosions-1689.wav")); // song explosion quand un alien est mort
                soundShoot.play();

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
                   batch.draw(texture,alien.getPosX() , alien.getPosY());
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
        stats();

    }


    public void powerStats( float captainStat, float alienStat){
        //AFFICHAGES DES STATISTIQUES:


        //Pour le vaisseau du  capitaine

        //petite image du vaisseau du capitaine
        batch.begin();
        batch.draw( getAssets().getSkybladePicture(),0,15, 40,40);
        batch.end();

        float posX = 50;
        float height = 20;
        float posY = 20;

        shapestyle.begin(ShapeRenderer.ShapeType.Filled);

        shapestyle.setColor(Color.WHITE);

        //Pour avoir un rectangle qui contient la progression
        shapestyle.rect(posX, posY, 100,height);

        if(captainStat <= 30 ) shapestyle.setColor(Color.RED);
        else shapestyle.setColor(Color.GREEN);

        //Pour avoir un rectangle pour la progression
        shapestyle.rect(50, 20, captainStat,20);

        shapestyle.end();

        //Affichages des tetes chercheuses pres du statistique
        for(int i=0; i<captain.getPredatorFury().getNbAmmo(); i++) {
            Texture predatorPic =getAssets().getPredatorPicture();
            batch.begin();
            batch.draw(predatorPic, 60 + 100 + i*10, 20);
            batch.end();
        }


        //Pour le vaisseau du monstre

        //petite image du vaisseau du monstre
        batch.begin();
        batch.draw( getAssets().getBossSmallPicture(),Gdx.graphics.getWidth() - 40,15, 40,40);
        batch.end();

        shapestyle.begin(ShapeRenderer.ShapeType.Filled);

        shapestyle.setColor(Color.WHITE);

        //Pour avoir un rectangle qui contient la progression
        shapestyle.rect(Gdx.graphics.getWidth() - 150, 20, 100,20);

        if(alienStat <= 30 ) shapestyle.setColor(Color.RED);
        else shapestyle.setColor(Color.GREEN);

        //Pour avoir un rectangle pour la progression
        shapestyle.rect(Gdx.graphics.getWidth() - 150, 20, alienStat,20);

        shapestyle.end();
    }

    public void scoreStat(){
        //AFFICHAGE DU SCORE SUR L'EN TETE DE L'ECRAN

        //le texte contenu dans un label avec le skin du jeu
        Label label = new Label(String.format("SCORE : %.2f",getScore()),getAssets().getSkin());

        //positionnement de ce label sur l'ecran
        float x = (Gdx.graphics.getWidth() - label.getWidth()) / 2;
        float y = Gdx.graphics.getHeight() - label.getHeight() - 5;
        label.setPosition( x, y);

        //Pouvoir dessinner ce label
        batch.begin();
        label.draw(batch,1f);
        batch.end();

    }

    public void level(){
        //AFFICHAGE DU LEVEL SUR L'EN TETE DE L'ECRAN

        //le texte contenu dans un label avec le skin du jeu
        Label label = new Label(String.format("LEVEL : %d",numLevel),getAssets().getSkin());

        //positionnement de ce label sur l'ecran
        label.setPosition( 10, Gdx.graphics.getHeight() - label.getHeight() - 5);

        //Pouvoir dessinner ce label
        batch.begin();
        label.draw(batch,1f);
        batch.end();
    }

    public void stats(){
       //AFFICHAGE ET CALCUL DU STATISTIQUES,SCORE,LEVEL

        //Pour convertir  la puissance totale des monstres en %
        float sum = 0;
        for (Alien alien : aliens) {
            float pctg = (float) (alien.getPuissance() * 100) / alien.getMaxPuissance();
            sum += pctg;
        }

        //Pour avoir la moyenne
        int alienLife = (int) (sum / nbAliens);

        //Pour convertir la puissance du capitaine en %
        float pourcentagePuissance = (captain.getPuissance() * 100)/ captain.getMaxPuissance();

        //Affichage et application des calculs
        powerStats(pourcentagePuissance,alienLife);
        scoreStat();
        level();

    }

    public void generateBonus(){

        //Pour pourvoir compter le nombre de temps que le bonus s affiche
        timesBonus += Gdx.graphics.getDeltaTime();

        int choice = new Random().nextInt(50);

        //generation de bonus une fois que le nombre d'aliens morts atteints le nombre 10 ou plus (pour chaque 10)
        //ou bien si on obtient une valeur aleatoire paire et que le temps maximal d'affichage du bouton est atteint
        if (numberALienKilled >= 10 || (choice % 2 == 0 && timesBonus >= MaxTimeBonus) ){

            timesBonus = 0;

            numberALienKilled = 0;

            //Pour donner des bonus aleatoires au capitaine
            int choice2 =  new Random().nextInt(3);

            //Soit changement d arme
            if(choice2 == 0)
                bonus.add(new ChangeWeapon(captain, new Random().nextInt(Gdx.graphics.getWidth()),new Random().nextInt(Gdx.graphics.getHeight() / 2), batch,getAssets()));

            //Soit bonus sur la puissance
            else if(choice2 == 1)
                bonus.add(new BonusPower(captain, new Random().nextInt(Gdx.graphics.getWidth()),new Random().nextInt(Gdx.graphics.getHeight() / 2), batch,getAssets()));

            //Soit un gain de bouclier
            else
                bonus.add(new Shield(captain, new Random().nextInt(Gdx.graphics.getWidth()),new Random().nextInt(Gdx.graphics.getHeight() / 2), batch,getAssets()));
        }

    }

    public void collectible(){
        BitmapFont fontScore = new BitmapFont();

        //Pour l affichage des bonus et aussi le collecte de ces bonus pour le vaisseau
        Iterator<Bonus> iteratorGift = bonus.iterator();
        while (iteratorGift.hasNext()) {
            Bonus collectible = iteratorGift.next();
            collectible.draw(); // dessine les bonus

            // Permet de recuperer les bonus
            if (new Collision().checkCollision(collectible.getPosX(), collectible.getPosY(), collectible.getPicture().getWidth(), collectible.getPicture().getHeight(),
                    captain.getPosX(),
                    captain.getPosY(),
                    captain.getPicture().getWidth(), captain.getPicture().getHeight())) {//si les tirs ont touche les ennemis !!
                collectible.collect();
                String text = " + " + collectible.getBonus() + " POINTS ";
                GlyphLayout layout = new GlyphLayout();

                fontScore.setColor(Color.GREEN);
                layout.setText(fontScore, text);

                batch.begin();

                if (collectible instanceof BonusScore)
                    fontScore.draw(batch, layout, ((Gdx.graphics.getWidth() - layout.width) / 2) + 180, Gdx.graphics.getHeight() - 10);

                 else if (collectible instanceof BonusPower)
                    fontScore.draw(batch, layout, 200, 50);

                batch.end();

                iteratorGift.remove();
            }
            else if (collectible.timeOut()) // Permet de retirer les bonus apres un certains temps sans etre recuperer
                iteratorGift.remove();
        }
    }

    public void showLevelText(int level){

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Label label = new Label(String.format("LEVEL %d",numLevel),getAssets().getSkin());
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
    public void show() {
        getAssets().getBackgroundMusic().play();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        getAssets().getBackgroundMusic().pause();

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
        getAssets().getBackgroundMusic().play();


    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setAliens(Alien alien) {
        aliens.add(alien);
    }

    public AllAssets getAssets() {
        return assets;
    }
}
