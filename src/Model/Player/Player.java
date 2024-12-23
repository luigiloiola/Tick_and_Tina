package Model.Player;

import Controller.KeyHandler;
import Model.Entity;
import Model.Model;
import Model.Player.Character;
import Model.FileManager;
import view.GamePannel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {


    public boolean down, left, right, up, basic, charge, skill = false;

    public boolean[] colliding = {false, false, false, false};


    int runningFrame, idleFrame, jumpFrame, jumpToFallFrame, fallFrame, skillFrame = 0;



    BufferedImage[] runningImages, idleImages, jumpImages, jumpToFallImages, fallImages, skillImages;
    boolean runningR, runningL;
    Map<String, HashMap<String, BufferedImage[]>> characterImages = new HashMap<>();
    public enum directions {LEFT, RIGHT}
    public directions direction = directions.LEFT;

    public boolean displayHitbox;


    private static int lastPlayerId = 0;
    public int playerId;

    public double glidingVelocity;

    public int jumpForce;

    public int jumpCount = 2;
    public boolean dodgeAvaliable;

    public boolean jumpCooldown = false;
    private final Map<String, Integer> cooldowns = new HashMap<>();
    public boolean gliding;

    GamePannel gamePannel;
    KeyHandler keyH;

    public Character character;

    //TODO: everything Player class is doing, should be done by Character class

    public Player(GamePannel gamePannel, KeyHandler keyH) {
        this.gamePannel = gamePannel;
        this.keyH = keyH;
        System.out.println("player created");
        this.playerId = lastPlayerId;
        lastPlayerId +=1;
        this.height = Model.getInstance().size;
        this.width = Model.getInstance().size;
        this.baseVelocityX = 2;
        this.velocityY = -0.1;
        this.cooldowns.put("cooldown", 0);
        this.animating = false;
        this.jumpForce = -6;
        this.glidingVelocity = -0.25;
        this.runningFrame=0;
        this.idleFrame = 0;
        this.dodgeAvaliable = true;
    }


    public void getEntityImage(String pathName) {

        characterImages.put("left", new HashMap<>());
        characterImages.put("right", new HashMap<>());

        characterImages.get("right").put("run", FileManager.getImagesFiles("res/" + pathName + "/run"));
        characterImages.get("right").put("idle", FileManager.getImagesFiles("res/" + pathName + "/idle"));
        characterImages.get("right").put("jump", FileManager.getImagesFiles("res/" + pathName + "/jump"));
        characterImages.get("right").put("jumpToFall", FileManager.getImagesFiles("res/" + pathName + "/jumpToFall"));
        characterImages.get("right").put("fall", FileManager.getImagesFiles("res/" + pathName + "/fall"));
        characterImages.get("right").put("skill", FileManager.getImagesFiles("res/" + pathName + "/skill"));

        characterImages.get("left").put("run", FileManager.reverseImageArray(characterImages.get("right").get("run")));
        characterImages.get("left").put("idle", FileManager.reverseImageArray(characterImages.get("right").get("idle")));
        characterImages.get("left").put("jump", FileManager.reverseImageArray(characterImages.get("right").get("jump")));
        characterImages.get("left").put("jumpToFall", FileManager.reverseImageArray(characterImages.get("right").get("jumpToFall")));
        characterImages.get("left").put("fall", FileManager.reverseImageArray(characterImages.get("right").get("fall")));
        characterImages.get("left").put("skill", FileManager.reverseImageArray(characterImages.get("right").get("skill")));

        idleImages = characterImages.get("left").get("idle");
        runningImages = characterImages.get("left").get("run");
        jumpImages = characterImages.get("left").get("jump");
        jumpToFallImages = characterImages.get("left").get("jumpToFall");
        fallImages = characterImages.get("left").get("fall");
        skillImages = characterImages.get("left").get("skill");

        System.out.println(characterImages);


    }

    public void selectCharacter(Character character){
        this.character = character;
        getEntityImage(character.imageFolderPath);
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
        image = characterImages.get("right").get("idle")[0];


//        multiplying and then dividing it back after the increment, makes so that the animation spends more time in each frame

        displayHitbox = keyH.devTools;

        if(velocityX > 0) {
            direction = directions.RIGHT;
            runningImages = characterImages.get("right").get("run");
            idleImages = characterImages.get("right").get("idle");
            jumpImages = characterImages.get("right").get("jump");
            jumpToFallImages = characterImages.get("right").get("jumpToFall");
            fallImages = characterImages.get("right").get("fall");
            skillImages = characterImages.get("right").get("skill");
        }
        else if(velocityX < 0) {
            direction = directions.LEFT;
            runningImages = characterImages.get("left").get("run");
            idleImages = characterImages.get("left").get("idle");
            jumpImages = characterImages.get("left").get("jump");
            jumpToFallImages = characterImages.get("left").get("jumpToFall");
            fallImages = characterImages.get("left").get("fall");
            skillImages = characterImages.get("left").get("skill");
        }

        if(skill) {
            if(skillFrame != (skillImages.length * character.skillAnimamationFramesPerStep)){
                image= skillImages[(int) (skillFrame / character.skillAnimamationFramesPerStep)];
                skillFrame ++;
                if(skillFrame == (int) (skillImages.length* character.skillAnimamationFramesPerStep)) animating = false;


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
        else if(velocityX != 0) {

            if(runningFrame == (int) (runningImages.length * gamePannel.animationFrameDuration)){
                runningFrame = 0;
            }
            image = runningImages[(int)(runningFrame / gamePannel.animationFrameDuration)];
            runningFrame++;

        }
        else {
            if(idleFrame ==  (int) (idleImages.length * gamePannel.animationFrameDuration)) {
                idleFrame = 0;
            }
            image = idleImages[(int) (idleFrame /gamePannel.animationFrameDuration)];
            idleFrame++;
        }
//        TODO: colocar posX e posY em função do image.getWidht() e image.getHieght()
        if(direction == directions.RIGHT) g2.drawImage(image, tempPosX-width, posY-height/2, image.getWidth()*Model.getInstance().scale, image.getHeight()*Model.getInstance().scale, null);
        else g2.drawImage(image, (tempPosX-(image.getWidth()*Model.getInstance().scale)+width*Model.getInstance().scale/2), posY-height/2, image.getWidth()*Model.getInstance().scale, image.getHeight()*Model.getInstance().scale, null);
    }

    public void routine() {
        if(keyH.devTools){
            System.out.println(gliding);
            if(!(posX == tempPosX)){
                System.out.println(posX);
            }
        }
        if(!animating){
            tempPosX = posX;

            if(!colliding[3] && velocityX > 0) posX += velocityX;
            if(!colliding[2] && velocityX < 0) posX += velocityX;
            if(gliding  && velocityY > 2) accelerationY = 0;
            else accelerationY = 0.12;
            if(!colliding[1]) {
                posY += (int)velocityY;
                velocityY+=accelerationY;
            }else{
                jumpCount = 2;
                dodgeAvaliable = true;
                velocityY = accelerationY;
            }

            if (keyH.upPressed) {
                gliding = true;
                if(!jumpCooldown && jumpCount > 0){
                    velocityY = jumpForce;
                    jumpCooldown = true;
                    jumpCount--;
                }
            }
            if(!keyH.upPressed){
                gliding = false;
                jumpCooldown = false;
            }

            if(keyH.leftPressed){
                velocityX = -baseVelocityX;
                runningL = true;
            }

            if(!keyH.leftPressed && runningL){
                velocityX = 0;
                runningL = false;
            }

            if(keyH.rightPressed){
                velocityX = baseVelocityX;
                runningR = true;
            }

            if (!keyH.rightPressed && runningR){
                velocityX = 0;
                runningR = false;
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
