package gift;

import spacecraft.Captain;

import java.util.Random;

public class BonusPower extends Gift {
    public Captain captain;

    public BonusPower(String name, Captain captain) {
        super(name);
        this.captain = captain;
    }

    public void EnhanceVessel(){Random random = new Random();
         Random nb_aleatoire = new Random();
       // this.captain.setStamina( this.captain.getStamina() + 5 + random.nextInt(5) );//nombre entre 5 et 10
    }
}
