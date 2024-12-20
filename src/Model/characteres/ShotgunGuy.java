package Model.characteres;

import Controller.KeyHandler;
import Model.Character;
import Model.Model;
import view.GamePannel;
import Model.FileManager;

public class ShotgunGuy extends Character {




    public ShotgunGuy(GamePannel gamePannel, KeyHandler keyH) {
        super(gamePannel, keyH);
        this.imageFolderPath = "shotgunGuy";
        this.skillanimationDuration = 0.5;
        skillAnimamationFramesPerStep = getAnimationFramesPerStep("res/" + imageFolderPath+"/skill", skillanimationDuration);
    }

    @Override
    public void basic() {

    }

    @Override
    public void skill() {
        animationSteps = FileManager.getFolderLength( "res/" +imageFolderPath+"/skill");
        int imageWidth = FileManager.getImageWidth("res/" +imageFolderPath+"/skill");
        animationStep = (int)(animationFrame/ skillAnimamationFramesPerStep);
        switch (animationStep) {
            case 0:
                break;
            case 1:
                System.out.println("aaaa");
                this.posX = tempPosX + imageWidth - this.width;
                break;
            case 6:
                break;
            default:
                break;
        }
        if(animationFrame == skillAnimamationFramesPerStep * animationSteps) animationFrame = 0;
        else animationFrame++;

    }

    @Override
    public void charge() {

    }

    @Override
    public void draw() {

    }
}
