package bonus;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import exceptions.NoWeaponExeption;
import helpers.AllAssets;
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
        super(DEFAULT_NAME,skyblade,x,y,batch, assets);
        //Un hashset contenant tous les armes
        this.weapons = new HashSet<>();

        //Stockage de tous les types d'arme
        this.weapons.add(new RocketCyclone(batch,skyblade, assets));
        this.weapons.add(new RocketStorm(batch,skyblade, assets));
        this.weapons.add(new RocketStorm3X(batch,skyblade, assets));
        this.weapons.add(new LaserFury(batch,skyblade, assets));

        //image du bonus sur laquelle le vaiseau peut collecter et changer d arme
        setPicture(assets.getBonusWeaponPicture());
    }
    public void collect() {//une fois que le vaisseau collecte le bonus
        try{
            for(Weapon weapon : this.weapons){
                if(this.skyblade.getWeapon() != weapon) {//pour tester si le vaisseau n a pas deja le meme arme pour qu on puisse le changer avec un autre !
                    this.skyblade.setWeapon(weapon);//changement d arme
                    getAssets().getBonuCollect().play();//Collecte et lance un petit son
                    return;
                }
            }
        }
        catch (NoWeaponExeption e){

        }

    }


}
