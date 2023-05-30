package screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import shoot_em_up.ShootEmUP;
import spacecraft.*;

import java.util.HashSet;

public class Level {

    HashSet<Alien> aliens = new HashSet<>();
    AllAssets assets;

    public Level(int level, ShootEmUP game, SpriteBatch batch, AllAssets assets){
        this.assets = assets;

        if(level == 1 ){
            for (int i = 0; i < 10; i++) {
               Alien alien = new TyrantOfDesolation(batch,assets);
                setAliens(alien);
            }
        }

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
        else if( level == 5){
            for(int i=0 ; i < 10 ; i++){
                Alien alien = new VenomclawRavager(batch,assets);
                setAliens(alien);
            }
            setAliens(new BossChaosbaneDestructor(batch,assets));
        }

    }




    public HashSet<Alien> getAliens() {
        return aliens;
    }

    public void setAliens(Alien alien) {
        this.aliens.add(alien);
    }

}
