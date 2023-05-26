package gift;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import spacecraft.Skyblade;
import weapon.SkyBladeWeapons.LaserFury;
import weapon.SkyBladeWeapons.RocketCyclone;
import weapon.SkyBladeWeapons.RocketStorm;
import weapon.SkyBladeWeapons.RocketStorm3X;
import weapon.Weapon;

import java.util.HashSet;

public class ChangeWeapon extends Gift{
    private static final String DEFAULT_NAME = " COLLECTE D'ARME " ;
    private static final String DEFAULT_IMAGE = "pictures/bonus/weapon.png" ;
   private HashSet<Weapon> weapons ;

    public ChangeWeapon(Skyblade skyblade, float x , float y, SpriteBatch batch) {
        super(DEFAULT_NAME,DEFAULT_IMAGE,skyblade,x,y,batch);
        this.weapons = new HashSet<>();
        this.weapons.add(new RocketCyclone(batch,skyblade));
        this.weapons.add(new RocketStorm(batch,skyblade));
        this.weapons.add(new RocketStorm3X(batch,skyblade));
        this.weapons.add(new LaserFury(batch,skyblade));
    }
    public void collect() {
      for(Weapon weapon : this.weapons){
          if(this.skyblade.getWeapon() != weapon) {
              this.skyblade.setWeapon(weapon);
              return;
          }
      }
    }


}
