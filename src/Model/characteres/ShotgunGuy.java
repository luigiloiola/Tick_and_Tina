package Model.characteres;

import Controller.KeyHandler;
import Model.Character;
import Model.Model;
import view.GamePannel;
import Model.FileManager;
import Model.Player;

public class ShotgunGuy extends Character {

    boolean stepDone = false;

    public ShotgunGuy (Player player) {
        super(player);
        this.imageFolderPath = "shotgunGuy";
        this.skillanimationDuration = 0.5;
        skillAnimamationFramesPerStep = getAnimationFramesPerStep("res/" + imageFolderPath+"/skill", skillanimationDuration);
    }

    @Override
    public void basic() {

    }

    @Override
    public void skill() {
        int imageWidth = FileManager.getImageWidth("res/" +imageFolderPath+"/skill");
        int distance = imageWidth*Model.getInstance().scale - imageWidth;
        if (player.direction == Player.directions.LEFT) distance = distance*-1;
        animationSteps = FileManager.getFolderLength( "res/" +imageFolderPath+"/skill");
        animationStep = (int)(animationFrame/ skillAnimamationFramesPerStep);

        switch (animationStep) {
            case 0:
                break;
            case 1:
                if (!stepDone) {
                    this.player.posX += distance;
                    stepDone = true;
                }
                break;
            default:
                stepDone = false;
                break;
        }
        if(animationFrame != skillAnimamationFramesPerStep * animationSteps) animationFrame++;
        else {
            this.player.animating = false;
            this.player.skill = false;
            animationFrame = 0;
        }

    }

    @Override
    public void charge() {

    }

}
