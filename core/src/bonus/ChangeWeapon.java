package bonus;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import exceptions.NoWeaponExeption;
import screen.AllAssets;
import spacecraft.Skyblade;
import weapon.SkyBladeWeapons.LaserFury;
import weapon.SkyBladeWeapons.RocketCyclone;
import weapon.SkyBladeWeapons.RocketStorm;
import weapon.SkyBladeWeapons.RocketStorm3X;
import weapon.Weapon;

import java.util.HashSet;

public class ChangeWeapon extends Bonus {
    private static final String DEFAULT_NAME = " COLLECTE D'ARME " ;
   private HashSet<Weapon> weapons ;

    public ChangeWeapon(Skyblade skyblade, float x , float y, SpriteBatch batch, AllAssets assets) {
        super(DEFAULT_NAME,skyblade,x,y,batch);
        this.weapons = new HashSet<>();
        this.weapons.add(new RocketCyclone(batch,skyblade, assets));
        this.weapons.add(new RocketStorm(batch,skyblade, assets));
        this.weapons.add(new RocketStorm3X(batch,skyblade, assets));
        this.weapons.add(new LaserFury(batch,skyblade, assets));
        setPicture(assets.getBonusWeaponPicture());
    }
    public void collect() {
        try{
            for(Weapon weapon : this.weapons){
                if(this.skyblade.getWeapon() != weapon) {
                    this.skyblade.setWeapon(weapon);
                    return;
                }
            }

        }
        catch (NoWeaponExeption e){

        }

    }


}
