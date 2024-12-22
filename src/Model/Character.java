package Model;

import Controller.KeyHandler;
import view.GamePannel;

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
        System.out.println((int)((Model.getInstance().tickRate * animationDuration / FileManager.getFolderLength(pathName))));
        System.out.println(((Model.getInstance().tickRate * animationDuration / FileManager.getFolderLength(pathName))));
        return ((Model.getInstance().tickRate * animationDuration / FileManager.getFolderLength(pathName)));
    }

}
