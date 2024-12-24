package Model.Player.characteres.ShotgunGuy;

import Model.Player.Character;
import Model.Model;
import Model.FileManager;
import Model.Player.Player;
import Model.Player.characteres.ShotgunGuy.*;

public class ShotgunGuy extends Character {

    boolean stepDone = false;
    Basic basic;
    Charge charge;
    Skill skill;

    public ShotgunGuy (Player player) {
        super(player);
        this.basic = new Basic("basic", this.player);
        this.charge = new Charge("charge", this.player);
        this.skill = new Skill("skill", this.player);
    }
}
