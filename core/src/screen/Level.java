package screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import shoot_em_up.ShootEmUP;
import spacecraft.*;

import java.util.HashSet;

public class Level {

    HashSet<Alien> monsters = new HashSet<>();
    AllAssets assets;

    public Level(int level, ShootEmUP game, SpriteBatch batch, AllAssets assets){
        this.assets = assets;

        if(level == 1 ){
            for (int i = 0; i < 10; i++) {
               Alien monster = new TyrantOfDesolation(batch,assets);
                setMonsters(monster);
            }
        }

        else if( level == 2 ){
            for (int i = 0; i < 5; i++) {
                Alien monster = new TyrantOfDesolation(batch,assets);
                setMonsters(monster);
            }
            for(int i= 0 ; i < 10 ; i++){
                Alien monster = new DeathspikeMarauder(batch,assets);
                setMonsters(monster);
            }

        }

        else if( level == 3){
            for (int i = 0; i < 5; i++) {
                Alien monster = new TyrantOfDesolation(batch,assets);
                setMonsters(monster);
            }
            for(int i= 0 ; i < 5 ; i++){
                Alien monster = new DeathspikeMarauder(batch,assets);
                setMonsters(monster);
            }
            for(int i=0 ; i < 10 ; i++){
                Alien monster = new RavagerScourge(batch,assets);
                setMonsters(monster);
            }
        }

        else if( level == 4){
            for (int i = 0; i < 5; i++) {
                Alien monster = new TyrantOfDesolation(batch,assets);
                setMonsters(monster);
            }
            for(int i= 0 ; i < 5 ; i++){
                Alien monster = new DeathspikeMarauder(batch,assets);
                setMonsters(monster);
            }
            for(int i=0 ; i < 5 ; i++){
                Alien monster = new RavagerScourge(batch,assets);
                setMonsters(monster);
            }
            for(int i=0 ; i < 10 ; i++){
                Alien monster = new InfernoReaper(batch,assets);
                setMonsters(monster);
            }

        }
        else if( level == 5){
            for(int i=0 ; i < 10 ; i++){
                Alien monster = new VenomclawRavager(batch,assets);
                setMonsters(monster);
            }
            setMonsters(new BossChaosbaneDestructor(batch,assets));
        }

    }




    public HashSet<Alien> getMonsters() {
        return monsters;
    }

    public void setMonsters(Alien alien) {
        this.monsters.add(alien);
    }

}
