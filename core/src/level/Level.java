package level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import helpers.AllAssets;
import shoot_em_up.ShootEmUP;
import spacecraft.*;

import java.util.HashSet;

public class Level {

    //Pour stocker tous les aliens dont le nombre et type en fonction du level
    HashSet<Alien> aliens = new HashSet<>();

    //Accès a tous les assets du jeu
    AllAssets assets;

    public Level(int level, ShootEmUP game, SpriteBatch batch, AllAssets assets){

        //pouvoir garder les assets mis en parametres
        this.assets = assets;

        //Correspond au nombre et type de monstres :
        //Pour le level 1
        if(level == 1 ){
            for (int i = 0; i < 10; i++) {
               Alien alien = new TyrantOfDesolation(batch,assets);
                setAliens(alien);
            }
        }

        //Pour le level 2
        else if( level == 2 ){
            for (int i = 0; i < 5; i++) {
                Alien alien = new TyrantOfDesolation(batch,assets);
                setAliens(alien);
            }
            for(int i= 0 ; i < 10 ; i++){
                Alien alien = new DeathspikeMarauder(batch,assets);
                setAliens(alien);
            }

        }

        //Pour le level 3
        else if( level == 3){
            for (int i = 0; i < 5; i++) {
                Alien alien = new TyrantOfDesolation(batch,assets);
                setAliens(alien);
            }
            for(int i= 0 ; i < 5 ; i++){
                Alien alien = new DeathspikeMarauder(batch,assets);
                setAliens(alien);
            }
            for(int i=0 ; i < 10 ; i++){
                Alien alien = new RavagerScourge(batch,assets);
                setAliens(alien);
            }
        }

        //Pour le level 4
        else if( level == 4){
            for (int i = 0; i < 5; i++) {
                Alien alien = new TyrantOfDesolation(batch,assets);
                setAliens(alien);
            }
            for(int i= 0 ; i < 5 ; i++){
                Alien alien = new DeathspikeMarauder(batch,assets);
                setAliens(alien);
            }
            for(int i=0 ; i < 5 ; i++){
                Alien alien = new RavagerScourge(batch,assets);
                setAliens(alien);
            }
            for(int i=0 ; i < 10 ; i++){
                Alien alien = new InfernoReaper(batch,assets);
                setAliens(alien);
            }

        }

        // //Pour le level 5
        else if( level == 5){
            for(int i=0 ; i < 3 ; i++){
                Alien alien = new VenomclawRavager(batch,assets);
                setAliens(alien);
            }
            setAliens(new BossChaosbaneDestructor(batch,assets));
        }

    }




    public HashSet<Alien> getAliens() {
        return aliens;
    }//Accès a tous les monstres a l'exterieur de la classe

    public void setAliens(Alien alien) {
        this.aliens.add(alien);
    }

}
