package Model.Player;

import Controller.KeyHandler;
import Model.Model;
import view.GamePannel;
import Model.FileManager;

public abstract class Character {

    public Player player;

    public Character(Player player) {
        this.player = player;
    }



}
