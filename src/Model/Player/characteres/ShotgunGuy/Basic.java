package Model.Player.characteres.ShotgunGuy;

import Model.Player.Player;
import Model.Player.ability;

public class Basic extends ability {

    Player player;

    public Basic(String basicName, Player player){
        super(basicName);
        this.player = player;
    }
}
