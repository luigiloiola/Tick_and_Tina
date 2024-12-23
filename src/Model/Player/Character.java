package Model.Player;

import Controller.KeyHandler;
import Model.Model;
import view.GamePannel;
import Model.FileManager;

public abstract class Character {

    public String imageFolderPath;
    public GamePannel gamePannel;
    public KeyHandler keyH;
    public int animationStep;
    public int animationFrame;
    public double skillanimationDuration;
    public double skillAnimamationFramesPerStep;
    public int animationSpeed;
    public int animationSteps;
    public int imageWidth;
    public Player player;

    public Character(Player player) {
        this.animationStep = 0;
        this.animationFrame = 1;
        this.skillanimationDuration = 1;
        this.player = player;
    }

    public abstract void basic();

    public abstract void skill();

    public abstract void charge();

    public double getAnimationFramesPerStep(String pathName, double animationDuration){
        return ((Model.getInstance().tickRate * animationDuration / FileManager.getFolderLength(pathName)));
    }

    public void checkForAnimationEnd(){

    }

}
