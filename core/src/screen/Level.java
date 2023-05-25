package screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import shoot_em_up.ShootEmUP;
import spacecraft.Alien;
import spacecraft.BossChaosbaneDestructor;
import spacecraft.DeathspikeMarauder;
import spacecraft.TyrantOfDesolation;

import java.util.HashSet;

public class Level {

    HashSet<Alien> monsters = new HashSet<>();

    public Level(int level, ShootEmUP game, SpriteBatch batch){

        if(level == 1 ){
            for (int i = 0; i < 10; i++) {
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

        }
        else if( level == 4){

        }
        else if( level == 5){
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
