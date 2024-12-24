package Model.Player;

import Model.Model;
import Model.FileManager;
public class ability {
    public String imagePathName;
    public double animationFramesPerStep;
    public int animationSteps;
    public double duration;
    double damage;
    String name;
    public int animationStep = 0;


    public ability(String abilityName){
        this.imagePathName = "res/" + imagePathName + "/" + name;
        this.name = abilityName;
        animationSteps = FileManager.getFolderLength(imagePathName);
    }

    double getAnimationFramesPerStep(){
        return (double)Model.getInstance().tickRate/FileManager.getFolderLength(imagePathName);
    };
}
