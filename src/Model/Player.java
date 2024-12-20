package Model;

import Controller.KeyHandler;
import view.GamePannel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity{


    public boolean down, left, right, up, basic, charge, skill = false;

    public boolean[] colliding = {false, false, false, false};


    int runningFrame, idleFrame, jumpFrame, jumpToFallFrame, fallFrame, skillFrame = 0;

    BufferedImage[] runningImages,runningImagesReversed, idleImages, jumpImages, jumpToFallImages, fallImages, skillImages;

    public boolean displayHitbox;


    private static int lastPlayerId = 0;
    public int playerId;

    public double glidingVelocity;

    public int jumpForce;

    public int jumpCount = 2;
    public boolean dodgeAvaliable;

    public boolean jumpCooldown = false;
    private final Map<String, Integer> cooldowns = new HashMap<>();

    GamePannel gamePannel;
    KeyHandler keyH;

    Character character;

    //TODO: everything Player class is doing, should be done by Character class

    public Player(Character character) {
        this.character = character;
        this.gamePannel = character.gamePannel;
        this.keyH = character.keyH;
        System.out.println("player created");
        this.playerId = lastPlayerId;
        lastPlayerId +=1;
        this.height = Model.getInstance().size;
        this.width = Model.getInstance().size;
        this.posX = character.posX;
        this.posY = character.posY;
        this.velocityX = 2;
        this.velocityY = -0.1;
        this.cooldowns.put("cooldown", 0);
        this.animating = false;
        this.jumpForce = -6;
        this.glidingVelocity = -0.25;
        this.runningFrame=0;
        this.idleFrame = 0;
        this.dodgeAvaliable = true;
        getEntityImage();

    }


    public void getEntityImage() {

        runningImages = FileManager.getImagesFiles("res/" + character.imageFolderPath + "/run");
        idleImages = FileManager.getImagesFiles("res/" + character.imageFolderPath + "/idle");
        jumpImages = FileManager.getImagesFiles("res/" + character.imageFolderPath + "/jump");
        jumpToFallImages = FileManager.getImagesFiles("res/" + character.imageFolderPath + "/jumpToFall");
        fallImages = FileManager.getImagesFiles("res/" + character.imageFolderPath + "/fall");
        skillImages = FileManager.getImagesFiles("res/" + character.imageFolderPath + "/skill");
    }



//    TODO: this should be updated by the Model not by the View

    BufferedImage animate(BufferedImage[] imageList, int imageFrame) {
        BufferedImage image;
        if(imageFrame ==  (int) (imageList.length * gamePannel.animationFrameDuration)) {
            imageFrame = 0;
        }
        image = imageList[(int) (imageFrame /gamePannel.animationFrameDuration)];
        imageFrame++;
        return image;
    }
    public void draw (Graphics2D g2) {
        BufferedImage image;
        image = idleImages[0];


//        multiplying and then dividing it back after the increment, makes so that the animation spends more time in each frame

        if (keyH.devTools) {
            displayHitbox = true;
        }

        if(skill) {
            if(skillFrame != (int)(skillImages.length* character.skillAnimamationFramesPerStep)){
                image= skillImages[(int) (skillFrame / character.skillAnimamationFramesPerStep)];
                skillFrame ++;
                if(skillFrame == (int) (skillImages.length* character.skillAnimamationFramesPerStep)-2) animating = false;


            }else{
                animating = false;
                skill = false;
                skillFrame = 0;
            }
        }

        else if(velocityY < 0 && !colliding[1]) {
            jumpToFallFrame = 0;
            if(jumpFrame ==  (int) (jumpImages.length * gamePannel.animationFrameDuration)) {
                jumpFrame = 0;
            }
            image = jumpImages[(int) (jumpFrame /gamePannel.animationFrameDuration)];
            jumpFrame++;

        }
        else if(velocityY > 0 && !colliding[1]) {

            if(jumpToFallFrame != (int) (jumpToFallImages.length* gamePannel.animationFrameDuration)-1){
                image=jumpToFallImages[(int)(jumpToFallFrame/ gamePannel.animationFrameDuration)];
                jumpToFallFrame++;

            }else{

                if(fallFrame == (int)(fallImages.length * gamePannel.animationFrameDuration)){
                        fallFrame = 0;
                    }
                    image = fallImages[(int)(fallFrame/gamePannel.animationFrameDuration)];
                    fallFrame++;
            }
        }
        else if(keyH.leftPressed && !keyH.rightPressed) {

            if(runningFrame == (int) (runningImages.length * gamePannel.animationFrameDuration)){
                runningFrame = 0;
            }
            image = runningImagesReversed[(int)(runningFrame / gamePannel.animationFrameDuration)];
            runningFrame++;

        }
        else if(keyH.rightPressed && !keyH.leftPressed) {

            if(runningFrame == (int) (runningImages.length * gamePannel.animationFrameDuration)){
                runningFrame = 0;
            }
            image = runningImages[(int)(runningFrame/ gamePannel.animationFrameDuration)];
            runningFrame++;

        }
        else {
//            if(idleFrame ==  (int) (idleImages.length * gamePannel.animationFrameDuration)) {
//                idleFrame = 0;
//            }
            image = idleImages[(int) (idleFrame /gamePannel.animationFrameDuration)];
//            idleFrame++;
        }
//        TODO: colocar posX e posY em função do image.getWidht() e image.getHieght()
        g2.drawImage(image, tempPosX-image.getWidth(), posY-height, image.getWidth()*Model.getInstance().scale, image.getHeight()*3, null);
    }

    public void routine() {
        if(!animating){
            tempPosX = posX;

            if(!colliding[1]) {
                posY += velocityY;
                velocityY+=0.12;
            }else{
                jumpCount = 2;
                dodgeAvaliable = true;
                velocityY = 0.12;
            }
            if (keyH.upPressed && !jumpCooldown) {
                velocityY = jumpForce;
                if(!colliding[0]){
                    jumpCooldown = true;
                    jumpCount--;
                }

            }
            if(keyH.leftPressed) {
                if(velocityX > 0){
                    velocityX = velocityX * -1;
                }
                if(!colliding[2]){
                    posX += velocityX;
                }
            }
            if(keyH.rightPressed) {
                if(velocityX < 0){
                    velocityX = velocityX * -1;
                }
                if(!colliding[3]){
                    posX += velocityX;
                }
            }
            if(keyH.shiftPressed && dodgeAvaliable) {
                skill = true;
                animating = true;
                character.skill();
            }
            if(keyH.mouseRight){
                basic = true;
            }

        }else{
            if (skill) character.skill();
            if (basic) character.basic();
            if (charge) character.charge();
        }
    }

    public void drawHitbox(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(posX, posY, width,height);
    }


}
