package Model.Player.characteres.ShotgunGuy;

import Model.Player.Player;
import Model.Player.ability;

public class Charge extends ability {

    Player player;

    public Charge(String chargeName, Player player){
        super(chargeName);
        this.player = player;
    }
}
