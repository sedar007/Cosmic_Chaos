package screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import shoot_em_up.ShootEmUP;
import spacecraft.*;

import java.util.HashSet;

public class Level {

    HashSet<Alien> monsters = new HashSet<>();

    public Level(int level, ShootEmUP game, SpriteBatch batch){

        if(level == 1 ){
            for (int i = 0; i < 1; i++) {
               Alien monster = new TyrantOfDesolation(batch);
                setMonsters(monster);
            }
        }

        else if( level == 2 ){
            for (int i = 0; i < 5; i++) {
                Alien monster = new TyrantOfDesolation(batch);
                setMonsters(monster);
            }
            for(int i= 0 ; i < 10 ; i++){
                Alien monster = new DeathspikeMarauder(batch);
                setMonsters(monster);
            }

        }

        else if( level == 3){
            for (int i = 0; i < 5; i++) {
                Alien monster = new TyrantOfDesolation(batch);
                setMonsters(monster);
            }
            for(int i= 0 ; i < 5 ; i++){
                Alien monster = new DeathspikeMarauder(batch);
                setMonsters(monster);
            }
            for(int i=0 ; i < 10 ; i++){
                Alien monster = new RavagerScourge(batch);
                setMonsters(monster);
            }
        }

        else if( level == 4){
            for (int i = 0; i < 5; i++) {
                Alien monster = new TyrantOfDesolation(batch);
                setMonsters(monster);
            }
            for(int i= 0 ; i < 5 ; i++){
                Alien monster = new DeathspikeMarauder(batch);
                setMonsters(monster);
            }
            for(int i=0 ; i < 5 ; i++){
                Alien monster = new RavagerScourge(batch);
                setMonsters(monster);
            }
            for(int i=0 ; i < 10 ; i++){
                Alien monster = new InfernoReaper(batch);
                setMonsters(monster);
            }

        }
        else if( level == 5){
            for(int i=0 ; i < 10 ; i++){
                Alien monster = new VenomclawRavager(batch);
                setMonsters(monster);
            }
            setMonsters(new BossChaosbaneDestructor(batch));
        }

    }




    public HashSet<Alien> getMonsters() {
        return monsters;
    }

    public void setMonsters(Alien alien) {
        this.monsters.add(alien);
    }

}
