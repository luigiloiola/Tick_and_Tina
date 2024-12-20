package Model;

import Controller.KeyHandler;
import view.GamePannel;

public abstract class Character extends Entity {

    public String imageFolderPath;
    public GamePannel gamePannel;
    public KeyHandler keyH;
    public int animationStep;
    public int animationFrame;
    public double skillanimationDuration;
    public int skillAnimamationFramesPerStep;
    public int animationSpeed;
    public int animationSteps;
    public int imageWidth;

    public Character(GamePannel gamePannel, KeyHandler keyH) {
        this.gamePannel = gamePannel;
        this.keyH = keyH;
        this.animationStep = 0;
        this.animationFrame = 1;
        this.skillanimationDuration = 1;

    }

    public abstract void basic();

    public abstract void skill();

    public abstract void charge();

    public abstract void draw();

    public int getAnimationFramesPerStep(String pathName, double animationDuration){
        System.out.println((int)((Model.getInstance().tickRate * animationDuration / FileManager.getFolderLength(pathName))));
        System.out.println(((Model.getInstance().tickRate * animationDuration / FileManager.getFolderLength(pathName))));
        return (int)((Model.getInstance().tickRate * animationDuration / FileManager.getFolderLength(pathName)));
    }

}
