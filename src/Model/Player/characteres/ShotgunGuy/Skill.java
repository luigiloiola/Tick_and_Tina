package Model.Player.characteres.ShotgunGuy;

import Model.FileManager;
import Model.Model;
import Model.Player.Player;
import Model.Player.ability;

public class Skill extends ability {

    Player player;

    Skill(String skillName, Player player){
        super(skillName);
        this.player = player;

    }

    public void use(){
        int imageWidth = FileManager.getImageWidth(super.imagePathName);
        int distance = imageWidth* Model.getInstance().scale - imageWidth;
        if (player.direction == Player.directions.LEFT) distance = distance*-1;
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
}
