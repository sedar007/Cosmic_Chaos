package screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import shoot_em_up.ShootEmUP;
import spacecraft.Alien;
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

    }

    public void setMonsters(Alien alien) {
        this.monsters.add(alien);
    }

}
